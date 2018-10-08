package com.heaven.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.heaven.dao.IOrderDetilDao;
import com.heaven.dao.IOrderMasterDao;
import com.heaven.dto.CartDTO;
import com.heaven.dto.OrderDTO;
import com.heaven.eunms.ExceptionEunm;
import com.heaven.eunms.OrderStatusEunm;
import com.heaven.eunms.PayStatusEunm;
import com.heaven.exception.SellException;
import com.heaven.po.OrderDetail;
import com.heaven.po.OrderMaster;
import com.heaven.po.ProductInfo;
import com.heaven.service.IOrderMasterService;
import com.heaven.service.IProductInfoService;
import com.heaven.utils.KeyUtils;

@Service
public class OrderMasterServiceImpl implements IOrderMasterService {
	@Autowired
	private IProductInfoService infoService;
	@Autowired
	private IOrderMasterDao masterDao;
	@Autowired
	private IOrderDetilDao detailDao;

	@Transactional
	@Override
	public OrderDTO create(OrderDTO orderDTO) {
		BigDecimal orderAmount = new BigDecimal(0);
		// 生成订单编号
		String orderId = KeyUtils.getNumber();
		// 查询商品
		for (OrderDetail detail : orderDTO.getOrderDetail()) {
			ProductInfo info = infoService.selectById(detail.getProductId());
			if (info == null) {
				throw new SellException(ExceptionEunm.PRODUCT_NOT_EXIST);
			}
			// 计算总价
			orderAmount = info.getProductPrice().multiply(new BigDecimal(detail.getProductQuantity())).add(orderAmount);
			// 订单详情入库
			BeanUtils.copyProperties(info, detail);
			detail.setOrderId(orderId);
			detail.setDetailId(KeyUtils.getNumber());
			detailDao.save(detail);
		}
		// 写入订单数据库
		OrderMaster orderMaster = new OrderMaster();
		BeanUtils.copyProperties(orderDTO, orderMaster);
		orderMaster.setOrderId(orderId);
		orderMaster.setOrderAmount(orderAmount);
		orderMaster.setOrderStatus(OrderStatusEunm.NEW.getCode());
		orderMaster.setPayStatus(PayStatusEunm.WAIT.getCode());
		masterDao.save(orderMaster);
		// 扣库存
		List<CartDTO> cartDTO = orderDTO.getOrderDetail().stream()
				.map(e -> new CartDTO(e.getProductId(), e.getProductQuantity())).collect(Collectors.toList());
		infoService.decreaseStock(cartDTO);
		return orderDTO;
	}

	@Override
	public OrderDTO findOne(String orderId) {
		Optional<OrderMaster> optional = masterDao.findById(orderId);
		if (!optional.isPresent()) {
			throw new SellException(ExceptionEunm.ORDER_NOT_EXIST);
		}
		List<OrderDetail> details = detailDao.findByOrderId(orderId);
		if (details == null) {
			throw new SellException(ExceptionEunm.ORDERDETAIL_NOT_EXIST);
		}
		OrderMaster orderMaster = optional.get();
		OrderDTO orderDTO = new OrderDTO();
		BeanUtils.copyProperties(orderMaster, orderDTO);
		orderDTO.setOrderDetail(details);
		return orderDTO;
	}

	@Override
	public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
		Page<OrderMaster> masterPage = masterDao.findByBuyerOpenid(buyerOpenid, pageable);
		List<OrderMaster> orderMasterList = masterPage.getContent();
		List<OrderDTO> orderDTOList = new ArrayList<>();
		for (OrderMaster master : orderMasterList) {
			OrderDTO orderDTO = new OrderDTO();
			BeanUtils.copyProperties(master, orderDTO);
			orderDTOList.add(orderDTO);
		}
		Page<OrderDTO> dtoPage = new PageImpl<>(orderDTOList, pageable, masterPage.getTotalElements());
		
		return dtoPage;
	}
	
	@Transactional
	@Override
	public OrderDTO cancel(OrderDTO orderDTO) {
		OrderMaster orderMaster = new OrderMaster();
		BeanUtils.copyProperties(orderDTO, orderMaster);
		// 查看订单状态
		if (!orderDTO.getOrderStatus().equals(OrderStatusEunm.NEW.getCode())) {
			throw new SellException(ExceptionEunm.ORDER_STATUS_ERROR);
		}
		// 修改订单状态
		orderMaster.setOrderStatus(OrderStatusEunm.CANCLE.getCode());
		OrderMaster master = masterDao.save(orderMaster);
		if (master == null) {
			throw new SellException(ExceptionEunm.ORDER_UPDATE_FAIL);
		}
		// 返回库存
		if (orderDTO.getOrderDetail().isEmpty()) {
			throw new SellException(ExceptionEunm.ORDERDETAIL_NOT_EXIST);
		}
		List<CartDTO> cartDTO = orderDTO.getOrderDetail().stream()
				.map(e -> new CartDTO(e.getProductId(), e.getProductQuantity())).collect(Collectors.toList());
		infoService.increaseStock(cartDTO);
		// 退款
		BeanUtils.copyProperties(orderMaster, orderDTO);
		return orderDTO;
	}

	@Transactional
	@Override
	public OrderDTO finish(OrderDTO orderDTO) {
		OrderMaster orderMaster = new OrderMaster();
		BeanUtils.copyProperties(orderDTO, orderMaster);
		if (!orderDTO.getOrderStatus().equals(OrderStatusEunm.NEW.getCode())) {
			throw new SellException(ExceptionEunm.ORDER_STATUS_ERROR);
		}
		orderMaster.setOrderStatus(OrderStatusEunm.FINISHED.getCode());
		OrderMaster master = masterDao.save(orderMaster);
		if (master == null) {
			throw new SellException(ExceptionEunm.ORDER_UPDATE_FAIL);
		}

		BeanUtils.copyProperties(orderMaster, orderDTO);

		return orderDTO;
	}

	@Transactional
	@Override
	public OrderDTO paid(OrderDTO orderDTO) {
		OrderMaster orderMaster = new OrderMaster();
		BeanUtils.copyProperties(orderDTO, orderMaster);
		// 查看订单状态
		if (!orderDTO.getOrderStatus().equals(OrderStatusEunm.NEW.getCode())) {
			throw new SellException(ExceptionEunm.ORDER_STATUS_ERROR);
		}
		// 查看支付状态
		if (!orderDTO.getPayStatus().equals(PayStatusEunm.WAIT.getCode())) {
			throw new SellException(ExceptionEunm.PAY_STATUS_ERROR);
		}
		orderMaster.setPayStatus(PayStatusEunm.SUCCESS.getCode());
		OrderMaster master = masterDao.save(orderMaster);
		if (master == null) {
			throw new SellException(ExceptionEunm.PAY_FAIL);
		}
		BeanUtils.copyProperties(orderMaster, orderDTO);
		
		return orderDTO;
	}

	@Override
	public Page<OrderDTO> findList(Pageable pageable) {
		Page<OrderMaster> masterPage = masterDao.findAll(pageable);
		List<OrderMaster> orderMasterList = masterPage.getContent();
		List<OrderDTO> orderDTOList = new ArrayList<>();
		for(OrderMaster master : orderMasterList){
			OrderDTO orderDTO = new OrderDTO();
			BeanUtils.copyProperties(master, orderDTO);
			orderDTOList.add(orderDTO);
		}
		Page<OrderDTO> dtoPage = new PageImpl<>(orderDTOList, pageable, masterPage.getTotalElements());
		return dtoPage;
	}

}

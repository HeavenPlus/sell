package com.heaven.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import com.heaven.dto.OrderDTO;
import com.heaven.po.OrderDetail;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceTest {
	@Autowired
	private IOrderMasterService masterService;
	
	@Test
	public void create(){
		OrderDTO orderDTO = new OrderDTO();
		orderDTO.setBuyerName("TOM");
		orderDTO.setBuyerPhone("123456");
		orderDTO.setBuyerAddress("上海");
		orderDTO.setBuyerOpenid("abc123");
		List<OrderDetail> orderDetail = new ArrayList<>();
		OrderDetail detail = new OrderDetail();
		detail.setProductId("2");
		detail.setProductQuantity(2);
		orderDetail.add(detail);
		orderDTO.setOrderDetail(orderDetail);
		OrderDTO result = masterService.create(orderDTO);
		log.info("【订单详情】={}",result);
	}
	@Test
	public void findOne(){
		OrderDTO orderDTO = masterService.findOne("123456");
		log.info("【订单信息】={}",orderDTO);
	}
	@Test
	public void findList(){
		Pageable request = PageRequest.of(0, 1);
		Page<OrderDTO> list = masterService.findList("abc123", request );
		List<OrderDTO> content = list.getContent();
		log.info("【用户所有订单】={}",content);
	}
	@Test
	public void cancle(){
		OrderDTO orderDTO = masterService.findOne("123456");
		System.out.println(orderDTO);
		OrderDTO dto = masterService.cancel(orderDTO);
		log.info("【取消订单】={}",dto);
	}
	@Test
	public void finish(){
		OrderDTO orderDTO = masterService.findOne("1538222565984");
		OrderDTO dto = masterService.finish(orderDTO);
		log.info("【完结订单】={}",dto);
	}
	@Test
	public void paid(){
		OrderDTO orderDTO = masterService.findOne("1538222565984");
		OrderDTO dto = masterService.paid(orderDTO);
		log.info("【订单支付】={}",dto);
	}
}

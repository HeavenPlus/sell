package com.heaven.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.heaven.convert.ValidateToDTO;
import com.heaven.dto.OrderDTO;
import com.heaven.eunms.ExceptionEunm;
import com.heaven.exception.SellException;
import com.heaven.service.IBuyerService;
import com.heaven.service.IOrderMasterService;
import com.heaven.utils.ResultUtils;
import com.heaven.validator.OrderValidator;
import com.heaven.vo.OrderMasterVo;
import com.heaven.vo.ResultVo;

@RestController
@RequestMapping("/buyer/order")
public class BuyerOrderMasterController {
	@Autowired
	private IOrderMasterService masterService;
	@Autowired
	private IBuyerService buyerService;
	@PostMapping("/create")
	public ResultVo create(@Valid OrderValidator orderValidator, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			throw new SellException(ExceptionEunm.PARAM_ERROR.getCode(),
					bindingResult.getFieldError().getDefaultMessage());
		}
		OrderDTO orderDTO = ValidateToDTO.convert(orderValidator);
		if (orderDTO.getOrderDetail().isEmpty()) {
			throw new SellException(ExceptionEunm.CART_ISNULL);
		}
		OrderDTO dto = masterService.create(orderDTO);
		OrderMasterVo masterVo = new OrderMasterVo();
		masterVo.setOrderId(dto.getOrderDetail().get(0).getOrderId());
		return ResultUtils.success(masterVo);
	}

	@GetMapping("/list")
	public ResultVo list(@RequestParam("openid") String openid,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "10") Integer size) {
		if(openid.isEmpty()){
			throw new SellException(ExceptionEunm.PARAM_ERROR);
		}
		PageRequest request = PageRequest.of(page, size);
		Page<OrderDTO> orderDTO = masterService.findList(openid, request);
		return ResultUtils.success(orderDTO.getContent());
	}

	@GetMapping("/detail")
	public ResultVo detail(@RequestParam("openid") String openid, @RequestParam("orderId") String orderId) {
		OrderDTO orderDTO = buyerService.findOne(openid, orderId);
		
		return ResultUtils.success(orderDTO);
	}

	@GetMapping("/cancel")
	public ResultVo cancel(@RequestParam("openid") String openid, @RequestParam("orderId") String orderId) {
		buyerService.cancel(openid, orderId);
		return ResultUtils.success();
	}
}

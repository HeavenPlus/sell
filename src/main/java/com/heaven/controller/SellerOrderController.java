package com.heaven.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.heaven.dto.OrderDTO;
import com.heaven.eunms.ExceptionEunm;
import com.heaven.exception.SellException;
import com.heaven.service.IOrderMasterService;

@Controller
@RequestMapping("/seller/order")
public class SellerOrderController {

	@Autowired
	private IOrderMasterService masterService;
	@GetMapping("/list")
	public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "size", defaultValue = "5") Integer size,Map<String,Object> map) {
		PageRequest pageable = PageRequest.of(page-1, size);
		Page<OrderDTO> orderDTO = masterService.findList(pageable);
		map.put("orderDTO", orderDTO);
		map.put("currentPage", page);
		map.put("size", size);
		return new ModelAndView("/list",map);
	}
	@GetMapping("/cancel")
	public ModelAndView cancel(@RequestParam("orderId") String orderId,Map<String,Object> map){
		
		map.put("url","/sell/seller/order/list");
		try{			
			OrderDTO orderDTO = masterService.findOne(orderId);
			masterService.cancel(orderDTO);
		
		}catch(SellException e){
			map.put("msg", e.getMessage());
			return new ModelAndView("/error",map);	
		}
		map.put("msg", ExceptionEunm.CANCEL_SUCCESS.getMsg());
		return new ModelAndView("/success",map);
	}
	
	@GetMapping("/detail")
	public ModelAndView detial(@RequestParam("orderId") String orderId,Map<String,Object> map){
		OrderDTO orderDTO = null;
		try{
			orderDTO = masterService.findOne(orderId);
		}catch(SellException e){
			map.put("msg", e.getMessage());
			map.put("url", "/sell/seller/order/list");
			return new ModelAndView("/error",map);
		}
		map.put("orderDTO", orderDTO);
		return new ModelAndView("/detail",map);
	}
	@GetMapping("/finish")
	public ModelAndView finish(@RequestParam("orderId") String orderId,Map<String,Object> map){
		map.put("url", "/sell/seller/order/list");
		try{
			OrderDTO orderDTO = masterService.findOne(orderId);
			masterService.finish(orderDTO);
		}catch(SellException e){
			map.put("msg", e.getMessage());
			return new ModelAndView("/error",map);
		}
		map.put("msg", ExceptionEunm.FINISH_SUCCESS.getMsg());
		return new ModelAndView("/success",map);
	}
}

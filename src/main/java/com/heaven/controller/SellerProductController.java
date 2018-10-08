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

import com.heaven.eunms.ExceptionEunm;
import com.heaven.exception.SellException;
import com.heaven.po.ProductInfo;
import com.heaven.service.IProductInfoService;

@Controller
@RequestMapping("/seller/product")
public class SellerProductController {
	@Autowired
	private IProductInfoService infoService;
	@GetMapping("/list")
	public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "size", defaultValue = "5") Integer size,Map<String,Object> map){
		PageRequest pageable = PageRequest.of(page-1, size);
		Page<ProductInfo> productInfo = infoService.findAll(pageable);
		map.put("productInfo", productInfo);
		map.put("currentPage", page);
		map.put("size", size);
		
		return new ModelAndView("product/list",map);
	}
	@GetMapping("/onSell")
	public ModelAndView onSell(@RequestParam("productId") String productId,Map<String,Object> map){
		map.put("url", "/sell/seller/product/list");
		try{
			infoService.onSell(productId);
		}catch(SellException e){
			map.put("msg", e.getMessage());
			return new ModelAndView("order/error",map);
		}
		map.put("msg", ExceptionEunm.ON_SELL_SUCCESS.getMsg());
		return new ModelAndView("order/success",map);
	}
	@GetMapping("/offSell")
	public ModelAndView offSell(@RequestParam("productId") String productId,Map<String,Object> map){
		map.put("url", "/sell/seller/product/list");
		try{
			infoService.offSell(productId);
		}catch(SellException e){
			map.put("msg", e.getMessage());
			return new ModelAndView("order/error",map);
		}
		map.put("msg", ExceptionEunm.OFF_SELL_SUCCESS.getMsg());
		return new ModelAndView("order/success",map);
	}
	
}

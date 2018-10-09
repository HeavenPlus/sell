package com.heaven.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.heaven.dto.ProductInfoDTO;
import com.heaven.eunms.ExceptionEunm;
import com.heaven.exception.SellException;
import com.heaven.po.ProductCategory;
import com.heaven.po.ProductInfo;
import com.heaven.service.IProductCategoryService;
import com.heaven.service.IProductInfoService;
import com.heaven.utils.KeyUtils;

@Controller
@RequestMapping("/seller/product")
public class SellerProductController {
	@Autowired
	private IProductInfoService infoService;
	@Autowired
	private IProductCategoryService catagoryService;

	@GetMapping("/list")
	public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "size", defaultValue = "5") Integer size, Map<String, Object> map) {
		PageRequest pageable = PageRequest.of(page - 1, size);
		Page<ProductInfo> productInfo = infoService.findAll(pageable);
		map.put("productInfo", productInfo);
		map.put("currentPage", page);
		map.put("size", size);

		return new ModelAndView("product/list", map);
	}

	@GetMapping("/onSell")
	public ModelAndView onSell(@RequestParam("productId") String productId, Map<String, Object> map) {
		map.put("url", "/sell/seller/product/list");
		try {
			infoService.onSell(productId);
		} catch (SellException e) {
			map.put("msg", e.getMessage());
			return new ModelAndView("order/error", map);
		}
		map.put("msg", ExceptionEunm.ON_SELL_SUCCESS.getMsg());
		return new ModelAndView("order/success", map);
	}

	@GetMapping("/offSell")
	public ModelAndView offSell(@RequestParam("productId") String productId, Map<String, Object> map) {
		map.put("url", "/sell/seller/product/list");
		try {
			infoService.offSell(productId);
		} catch (SellException e) {
			map.put("msg", e.getMessage());
			return new ModelAndView("order/error", map);
		}
		map.put("msg", ExceptionEunm.OFF_SELL_SUCCESS.getMsg());
		return new ModelAndView("order/success", map);
	}

	@GetMapping("/index")
	public ModelAndView index(@RequestParam(value = "productId", required = false) String productId,Map<String, Object> map) {
		if (productId != null) {
			ProductInfo productInfo = infoService.selectById(productId);
			if (productInfo == null) {
				map.put("url", "/sell/seller/product/list");
				map.put("msg", ExceptionEunm.PRODUCT_NOT_EXIST.getMsg());
				return new ModelAndView("order/error", map);
			}
			map.put("productInfo", productInfo);
		}
		List<ProductCategory> productCategory = catagoryService.selectAll();
		map.put("productCategory", productCategory);	
		return new ModelAndView("product/index", map);
	}
	@PostMapping("/save")
	public ModelAndView save(@Valid ProductInfoDTO infoDTO,BindingResult bind,Map<String,Object> map){
		map.put("url","/sell/seller/product/list");
		if(bind.hasErrors()){
			map.put("msg", bind.getFieldError().getDefaultMessage());
			return new ModelAndView("order/error",map);
		}
		ProductInfo productInfo = new ProductInfo();
		if(infoDTO.getProductId()==null){
			infoDTO.setProductId(KeyUtils.getNumber());
		}
		BeanUtils.copyProperties(infoDTO, productInfo);
		infoService.save(productInfo);
		map.put("msg", ExceptionEunm.PRODUCT_UPDATE_SUCCESS.getMsg());
		return new ModelAndView("order/success",map);
	}
	
}

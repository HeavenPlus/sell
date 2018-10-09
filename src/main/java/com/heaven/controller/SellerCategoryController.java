package com.heaven.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.heaven.eunms.ExceptionEunm;
import com.heaven.po.ProductCategory;
import com.heaven.service.IProductCategoryService;

@Controller
@RequestMapping("/seller/category")
public class SellerCategoryController {
	@Autowired
	private IProductCategoryService categoryService;
	@GetMapping("/list")
	public ModelAndView list(Map<String, Object> map){
		List<ProductCategory> productCategory = categoryService.selectAll();
		map.put("productCategory", productCategory);
		return new ModelAndView("category/list",map);
	}
	
	@GetMapping("/index")
	public ModelAndView index(@RequestParam(value="categoryId",required=false) Integer categoryId,Map<String,Object> map){
		if(categoryId!=null){
			ProductCategory productCategory = categoryService.selectById(categoryId);
			if(productCategory==null){
				map.put("url","/sell/seller/category/list");
				map.put("msg", ExceptionEunm.CATEGORY_NOT_EXIST.getMsg());
				return new ModelAndView("order/error",map);
			}
			map.put("productCategory", productCategory);
		}
		return new ModelAndView("category/index",map);
	}
	
	@PostMapping("/save")
	public ModelAndView save(@Valid ProductCategory productCategory,BindingResult bind,Map<String,Object> map){
		map.put("url", "/sell/seller/category/list");
		if(bind.hasErrors()){
			map.put("msg", bind.getFieldError().getDefaultMessage());
			return new ModelAndView("order/error",map);
		}
		categoryService.save(productCategory);
		map.put("msg", ExceptionEunm.CATEGORY_UPDATE_SUCCESS.getMsg());
		return new ModelAndView("order/success",map);
	}
}

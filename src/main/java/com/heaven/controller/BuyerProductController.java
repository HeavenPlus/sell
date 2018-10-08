package com.heaven.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.heaven.po.ProductCategory;
import com.heaven.po.ProductInfo;
import com.heaven.service.IProductCategoryService;
import com.heaven.service.IProductInfoService;
import com.heaven.utils.ResultUtils;
import com.heaven.vo.ProductCategoryVo;
import com.heaven.vo.ProductInfoVo;
import com.heaven.vo.ResultVo;

@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {
	@Autowired
	private IProductCategoryService categoryService;
	@Autowired
	private IProductInfoService infoService;

	@RequestMapping("/list")
	public ResultVo list() {
		// 查询所有上架商品
		List<ProductInfo> findUpAll = infoService.findUpAll();
		// 查询商品类目
		List<Integer> categoryTypeList = findUpAll.stream().map(e -> e.getCategoryType()).collect(Collectors.toList());
		List<ProductCategory> categoryList = categoryService.findByCategoryTypeIn(categoryTypeList);
		// 数据拼装
		//遍历获取类目信息存储到categoryVoList中
		List<ProductCategoryVo> categoryVoList = new ArrayList<>();
		for(ProductCategory category : categoryList){
			ProductCategoryVo categoryVo = new ProductCategoryVo();
			categoryVo.setCatagoryName(category.getCategoryName());
			categoryVo.setCatagoryType(category.getCategoryType());
			//获取上架商品，判断类目状态是否相同
			//类目相同的存储到一个infoVoList中
			List<ProductInfoVo> infoVoList = new ArrayList<>();
			for(ProductInfo info : findUpAll){
				if(info.getCategoryType().equals(categoryVo.getCatagoryType())){
					ProductInfoVo infoVo = new ProductInfoVo();
					BeanUtils.copyProperties(info, infoVo);
					infoVoList.add(infoVo);
				}
			}
			//将infoVoList放入categoryVo中
			categoryVo.setProductInfo(infoVoList);
			categoryVoList.add(categoryVo);
		}

		return ResultUtils.success(categoryVoList);
	}
}

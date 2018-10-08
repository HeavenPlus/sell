package com.heaven.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.heaven.dao.IProductCategoryDao;
import com.heaven.po.ProductCategory;
import com.heaven.service.IProductCategoryService;
@Service
public class ProductCategoryServiceImpl implements IProductCategoryService {
	@Autowired
	private IProductCategoryDao categoryDao;
	@Override
	public ProductCategory selectById(Integer id) {
		return categoryDao.findById(id).get();
	}

	@Override
	public List<ProductCategory> selectAll() {
		return categoryDao.findAll();
	}

	@Override
	public List<ProductCategory> findByCategoryTypeIn(List<Integer> list) {
		return categoryDao.findByCategoryTypeIn(list);
	}

	@Override
	public ProductCategory save(ProductCategory pro) {
		return categoryDao.save(pro);
	}

}

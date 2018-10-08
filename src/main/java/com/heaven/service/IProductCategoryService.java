package com.heaven.service;

import java.util.List;

import com.heaven.po.ProductCategory;

public interface IProductCategoryService {
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	ProductCategory selectById(Integer id);
	/**
	 * 查询所有
	 * @return
	 */
	List<ProductCategory> selectAll();
	/**
	 * 根据商品类目编号查询
	 * @param list
	 * @return
	 */
	List<ProductCategory> findByCategoryTypeIn(List<Integer> list);
	/**
	 * 保存商品类目
	 * @param pro
	 * @return
	 */
	ProductCategory save(ProductCategory pro);
}

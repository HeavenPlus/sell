package com.heaven.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.heaven.po.ProductCategory;

public interface IProductCategoryDao extends JpaRepository<ProductCategory, Integer> {
	/**
	 * 根据类目编号查询商品类目
	 * @param list
	 * @return
	 */
	List<ProductCategory> findByCategoryTypeIn(List<Integer> list);
}

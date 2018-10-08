package com.heaven.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.heaven.po.ProductInfo;

public interface IProductInfoDao extends JpaRepository<ProductInfo, String> {
	/**
	 * 根据商品状态查询商品 0 表示下架，1表示上架
	 * @param status
	 * @return
	 */
	List<ProductInfo> findByProductStatus(Integer status);
}

package com.heaven.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.heaven.dto.CartDTO;
import com.heaven.po.ProductInfo;

public interface IProductInfoService {
	/**
	 * 根据id查询
	 * 
	 * @param id
	 * @return
	 */
	ProductInfo selectById(String id);

	/**
	 * 查询所有上架商品 0表示下架1表示上架
	 * 
	 * @param status
	 * @return
	 */
	List<ProductInfo> findUpAll();

	/**
	 * 查询所有商品（分页）
	 * 
	 * @param pageable
	 * @return
	 */
	Page<ProductInfo> findAll(Pageable pageable);

	/**
	 * 保存商品
	 * 
	 * @param pro
	 * @return
	 */
	ProductInfo save(ProductInfo pro);

	/**
	 * 增加库存
	 * 
	 * @param cartDTO
	 */
	void increaseStock(List<CartDTO> cartDTO);

	/**
	 * 减少库存
	 * 
	 * @param cartDTO
	 */
	void decreaseStock(List<CartDTO> cartDTO);

	/**
	 * 上架
	 * 
	 * @param productId
	 * @return
	 */
	ProductInfo onSell(String productId);

	/**
	 * 下架
	 * 
	 * @param productId
	 * @return
	 */
	ProductInfo offSell(String productId);
}

package com.heaven.dto;

import lombok.Data;

@Data
public class CartDTO {
	//商品id
	private String productId;
	//商品数量
	private Integer productQuentity;
	public CartDTO(String productId, Integer productQuentity) {
		this.productId = productId;
		this.productQuentity = productQuentity;
	}
	public CartDTO() {
	}
	
}

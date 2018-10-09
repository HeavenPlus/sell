package com.heaven.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ProductInfoDTO {
	//商品id
	private String productId;
	//商品名称
	private String productName;
	//商品价格
	private BigDecimal productPrice;
	//商品库存
	private Integer productStock;
	//商品描述
	private String productDescription;
	//商品图片
	private String productIcon;
	//商品类目编号
	private Integer categoryType;
}

package com.heaven.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ProductCategoryVo {
	@JsonProperty("name")
	private String catagoryName;
	@JsonProperty("type")
	private Integer catagoryType;
	@JsonProperty("foods")
	private List<ProductInfoVo>  productInfo;
}

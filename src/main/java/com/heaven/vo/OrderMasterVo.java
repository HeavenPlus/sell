package com.heaven.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class OrderMasterVo {
	@JsonProperty("orderId")
	private String orderId;
}

package com.heaven.eunms;

import lombok.Getter;

@Getter
public enum ExceptionEunm {
	
	PRODUCT_NOT_EXIST(10, "商品不存在"),
	
	STOCK_ERROR(11,"库存不足"),
	
	ORDER_NOT_EXIST(12,"订单不存在"),
	
	ORDERDETAIL_NOT_EXIST(13,"订单详情不存在"),
	
	ORDER_STATUS_ERROR(14,"订单状态不正确"),
	
	ORDER_UPDATE_FAIL(15,"取消订单失败"),
	
	PAY_STATUS_ERROR(16,"支付状态不正确"),
	
	PAY_FAIL(17,"支付失败"),
	
	PARAM_ERROR(18,"参数错误"),
	
	CART_ISNULL(19,"购物车为空"),
	
	ORDER_MASTER_ERROR(20,"不是本人的订单"),
	
	WECHAT_MP_ERROR(21,"微信公众号错误"),
	
	CANCEL_SUCCESS(22,"订单取消成功!"),

	FINISH_SUCCESS(23,"订单完结成功!");
	
	private Integer code;
	private String msg;

	private ExceptionEunm(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}
}

package com.heaven.eunms;

import com.heaven.utils.CodeEnum;

import lombok.Getter;

@Getter
public enum OrderStatusEunm implements CodeEnum{
	NEW(0, "新下单"), FINISHED(1, "已完结"), CANCLE(2, "已取消");
	private Integer code;
	private String msg;

	private OrderStatusEunm(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}
}

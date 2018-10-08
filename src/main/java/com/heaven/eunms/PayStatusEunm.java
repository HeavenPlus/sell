package com.heaven.eunms;

import com.heaven.utils.CodeEnum;

import lombok.Getter;

@Getter
public enum PayStatusEunm implements CodeEnum{
	WAIT(0, "等待支付"), SUCCESS(1, "支付成功");
	private Integer code;
	private String msg;

	private PayStatusEunm(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}
}

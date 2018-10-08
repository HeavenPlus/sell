package com.heaven.eunms;

import lombok.Getter;

@Getter
public enum ProductStatusEunm {
	UP(1,"上架"),
	DONW(0,"下架");
	
	private Integer code;
	private String msg;
	
	private ProductStatusEunm(Integer code,String msg) {
		this.code = code;
		this.msg = msg;
	}
}

package com.heaven.exception;

import com.heaven.eunms.ExceptionEunm;

import lombok.Getter;

@Getter
public class SellException extends RuntimeException{

	private static final long serialVersionUID = 3121359466336721534L;
	
	private Integer code;
	
	public SellException(ExceptionEunm exceptionEunm) {
		super(exceptionEunm.getMsg());
		this.code = exceptionEunm.getCode();
	}
	public SellException(Integer code,String msg) {
		super(msg);
		this.code = code;
	}
	
}

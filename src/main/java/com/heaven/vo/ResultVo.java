package com.heaven.vo;

import lombok.Data;

@Data
public class ResultVo {
	//状态码
	private Integer code;
	//提示信息
	private String msg;
	//返回内容
	private Object data;
}

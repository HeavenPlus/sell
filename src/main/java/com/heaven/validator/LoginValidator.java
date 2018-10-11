package com.heaven.validator;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class LoginValidator {
	//卖家用户名
	@NotEmpty(message="用户名不能为空")
	private String username;
	//卖家密码
	@NotEmpty(message="密码不能为空")
	private String password;
}

package com.heaven.po;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class SellerInfo {
	// 卖家id
	@Id
	private String Id;
	//卖家用户名
	private String username;
	//卖家密码
	private String password;
	//卖家openid
	private String openid;
}

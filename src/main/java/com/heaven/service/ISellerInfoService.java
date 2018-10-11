package com.heaven.service;

import com.heaven.po.SellerInfo;

public interface ISellerInfoService {
	
	SellerInfo login(String username,String password);
}

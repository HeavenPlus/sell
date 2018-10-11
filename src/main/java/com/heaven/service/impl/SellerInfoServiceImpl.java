package com.heaven.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.heaven.dao.ISellerInfoDao;
import com.heaven.eunms.ExceptionEunm;
import com.heaven.exception.SellException;
import com.heaven.po.SellerInfo;
import com.heaven.service.ISellerInfoService;

@Service
public class SellerInfoServiceImpl implements ISellerInfoService {
	
	@Autowired
	private ISellerInfoDao sellerInfoDao;
	@Override
	public SellerInfo login(String username, String password) {
		SellerInfo sellerInfo = sellerInfoDao.findByUsername(username);
		if(sellerInfo==null){
			throw new SellException(ExceptionEunm.USER_NOT_EXIST);
		}
		if(!password.equals(sellerInfo.getPassword())){
			throw new SellException(ExceptionEunm.PASSWORD_ERROR);
		}
		return sellerInfo;
	}

}

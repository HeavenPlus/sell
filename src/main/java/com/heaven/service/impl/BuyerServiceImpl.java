package com.heaven.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.heaven.dto.OrderDTO;
import com.heaven.eunms.ExceptionEunm;
import com.heaven.exception.SellException;
import com.heaven.service.IBuyerService;
import com.heaven.service.IOrderMasterService;

@Service
public class BuyerServiceImpl implements IBuyerService {

	@Autowired
	private IOrderMasterService masterService;
	@Override
	public OrderDTO findOne(String openid, String orderId) {
		
		return chackOpenid(openid, orderId);
	}

	@Override
	public OrderDTO cancel(String openid, String orderId) {
		OrderDTO orderDTO = chackOpenid(openid, orderId);
		if(orderDTO==null){
			throw new SellException(ExceptionEunm.ORDER_NOT_EXIST);
		}
		return masterService.cancel(orderDTO);
	}
	public OrderDTO chackOpenid(String openid,String orderId){
		OrderDTO orderDTO = masterService.findOne(orderId);
		if(orderDTO==null){
			return null;
		}
		if(!orderDTO.getBuyerOpenid().equals(openid)){
			throw new SellException(ExceptionEunm.ORDER_MASTER_ERROR);
		}
		return orderDTO;
	}
}

package com.heaven.service;

import com.heaven.dto.OrderDTO;

public interface IBuyerService {
	
	OrderDTO findOne(String openid,String orderId);
	
	OrderDTO cancel(String openid,String orderId);
}

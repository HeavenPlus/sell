package com.heaven.convert;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.heaven.dto.OrderDTO;
import com.heaven.eunms.ExceptionEunm;
import com.heaven.exception.SellException;
import com.heaven.po.OrderDetail;
import com.heaven.validator.OrderValidator;

public class ValidateToDTO {
	public static OrderDTO convert(OrderValidator orderValidator) {
		Gson gson = new Gson();
		OrderDTO orderDTO = new OrderDTO();
		
		orderDTO.setBuyerName(orderValidator.getName());
		orderDTO.setBuyerPhone(orderValidator.getPhone());
		orderDTO.setBuyerAddress(orderValidator.getAddress());
		orderDTO.setBuyerOpenid(orderValidator.getOpenid());
		List<OrderDetail> OrderDetail = new ArrayList<>();
		try{
			OrderDetail = gson.fromJson(orderValidator.getItems(), new TypeToken<List<OrderDetail>>() {
			}.getType());
		}catch(Exception e){
			throw new SellException(ExceptionEunm.PARAM_ERROR);
		}
		orderDTO.setOrderDetail(OrderDetail);
		
		return orderDTO;
	}
}

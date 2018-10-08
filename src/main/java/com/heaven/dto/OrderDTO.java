package com.heaven.dto;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.heaven.eunms.OrderStatusEunm;
import com.heaven.eunms.PayStatusEunm;
import com.heaven.po.OrderDetail;
import com.heaven.utils.DateToLong;
import com.heaven.utils.EnumUtils;

import lombok.Data;

@Data
public class OrderDTO {
	// 订单id
	private String orderId;
	// 买家姓名
	private String buyerName;
	// 买家电话
	private String buyerPhone;
	// 买家地址
	private String buyerAddress;
	// 买家微信openid
	private String buyerOpenid;
	// 订单总金额
	private BigDecimal orderAmount;
	// 订单状态 默认新下单
	private Integer orderStatus;
	// 支付状态 默认未支付
	private Integer payStatus;
	// 创建时间
	@JsonSerialize(using = DateToLong.class)
	private Date createTime;
	// 更新时间
	@JsonSerialize(using = DateToLong.class)
	private Date updateTime;
	
	private List<OrderDetail> orderDetail;
	
	@JsonIgnore
	public OrderStatusEunm getOrderStatusEunm(){
		return EnumUtils.getByCode(orderStatus,OrderStatusEunm.class);
	}
	@JsonIgnore
	public PayStatusEunm getPayStatusEunm(){
		return EnumUtils.getByCode(payStatus,PayStatusEunm.class);
	}
}

package com.heaven.po;

import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicUpdate;

import com.heaven.eunms.OrderStatusEunm;
import com.heaven.eunms.PayStatusEunm;

import lombok.Data;

@Entity
@Data
@DynamicUpdate
public class OrderMaster {
	//订单id
	@Id
	private String orderId;
	//买家姓名
	private String buyerName;
	//买家电话
	private String buyerPhone;
	//买家地址
	private String buyerAddress;
	//买家微信openid
	private String buyerOpenid;
	//订单总金额
	private BigDecimal orderAmount;
	//订单状态 默认新下单
	private Integer orderStatus = OrderStatusEunm.NEW.getCode();
	//支付状态 默认未支付
	private Integer payStatus = PayStatusEunm.WAIT.getCode();
	//创建时间
	private Date createTime;
	//更新时间
	private Date updateTime;
}

package com.heaven.po;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicUpdate;

import lombok.Data;

@Entity
@Data
@DynamicUpdate
public class OrderDetail {
	//订单详情id
	@Id
	private String detailId;
	//订单id
	private String orderId;
	//商品id
	private String productId;
	//商品名称
	private String productName;
	//商品价格
	private BigDecimal productPrice;
	//商品数量
	private Integer productQuantity;
	//商品图片
	private String productIcon;
}

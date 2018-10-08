package com.heaven.po;

import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicUpdate;

import lombok.Data;

@Entity
@DynamicUpdate
@Data
public class ProductInfo {
	//商品id
	@Id
	private String productId;
	//商品名称
	private String productName;
	//商品价格
	private BigDecimal productPrice;
	//商品库存
	private Integer productStock;
	//商品描述
	private String productDescription;
	//商品图片
	private String productIcon;
	//商品状态
	private Integer productStatus;
	//商品类目编号
	private Integer categoryType;
	// 创建时间
//	@JsonSerialize(using = DateToLong.class)
	private Date createTime;
	// 更新时间
//	@JsonSerialize(using = DateToLong.class)
	private Date updateTime;
}

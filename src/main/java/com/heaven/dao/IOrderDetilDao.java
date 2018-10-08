package com.heaven.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.heaven.po.OrderDetail;

public interface IOrderDetilDao extends JpaRepository<OrderDetail, String>{
	List<OrderDetail> findByOrderId(String orderId);
}

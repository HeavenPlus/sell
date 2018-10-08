package com.heaven.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.heaven.po.OrderMaster;

public interface IOrderMasterDao extends JpaRepository<OrderMaster, String>{
	Page<OrderMaster> findByBuyerOpenid(String buyerOpenid,Pageable pageable);
}

package com.heaven.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.heaven.po.SellerInfo;

public interface ISellerInfoDao extends JpaRepository<SellerInfo, String> {
	SellerInfo findByUsername(String username);
}

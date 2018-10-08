package com.heaven.dao;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.heaven.po.ProductInfo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IProductCategoryDaoTest {
	@Autowired
	private IProductInfoDao pid;
	@Test
	public void findByStatus(){
		List<ProductInfo> list = pid.findByProductStatus(1);
		System.out.println(list);
	}
}

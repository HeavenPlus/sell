package com.heaven.dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.heaven.po.ProductCategory;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IProductInfoDaoTest {
	@Autowired
	private IProductCategoryDao pcd;
	@Test
	public void findAll(){
		List<ProductCategory> list = pcd.findAll();
		for(ProductCategory pc : list){
			System.out.println(pc);
		}
	}
	@Test
	public void save(){
		ProductCategory pc = new ProductCategory();
		pc.setCategoryName("apple");
		pc.setCategoryType(2);
		pcd.save(pc);
	}
	@Test
	public void update(){
		
		ProductCategory pc = pcd.findById(2).get();
		pc.setCategoryName("2");
		pcd.save(pc);
	}
	@Test
	public void findByCategoryTypeIn(){
		List<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(2);
		List<ProductCategory> list2 = pcd.findByCategoryTypeIn(list);
		System.out.println(list2);
	}
}

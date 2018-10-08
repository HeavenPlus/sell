package com.heaven.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.heaven.dao.IProductInfoDao;
import com.heaven.dto.CartDTO;
import com.heaven.eunms.ExceptionEunm;
import com.heaven.eunms.ProductStatusEunm;
import com.heaven.exception.SellException;
import com.heaven.po.ProductInfo;
import com.heaven.service.IProductInfoService;

@Service
public class ProductInfoServiceImpl implements IProductInfoService {
	@Autowired
	private IProductInfoDao infoDao;

	@Override
	public ProductInfo selectById(String id) {
		Optional<ProductInfo> optional = infoDao.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public List<ProductInfo> findUpAll() {
		return infoDao.findByProductStatus(ProductStatusEunm.UP.getCode());
	}

	@Override
	public Page<ProductInfo> findAll(Pageable pageable) {
		return infoDao.findAll(pageable);
	}

	@Override
	public ProductInfo save(ProductInfo pro) {
		return infoDao.save(pro);
	}

	@Override
	public void increaseStock(List<CartDTO> cartDTO) {
		for(CartDTO cart : cartDTO){
			Optional<ProductInfo> optional = infoDao.findById(cart.getProductId());
			if(optional.isPresent()){
				ProductInfo info = optional.get();
				Integer result = info.getProductStock()+cart.getProductQuentity();
				info.setProductStock(result);
				infoDao.save(info);
			}else{
				throw new SellException(ExceptionEunm.PRODUCT_NOT_EXIST);
			}
		}
	}

	@Transactional
	@Override
	public void decreaseStock(List<CartDTO> cartDTO) {
		for (CartDTO cart : cartDTO) {
			Optional<ProductInfo> optional = infoDao.findById(cart.getProductId());
			if (optional.isPresent()) {
				ProductInfo info = optional.get();
				Integer result = info.getProductStock() - cart.getProductQuentity();
				if (result < 0) {
					throw new SellException(ExceptionEunm.STOCK_ERROR);
				}
				info.setProductStock(result);
				infoDao.save(info);
			} else {
				throw new SellException(ExceptionEunm.PRODUCT_NOT_EXIST);
			}
		}
	}

}

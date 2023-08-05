package com.hu.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hu.dao.CartypeDAO;

import com.hu.entity.CartypeEntity;

import com.hu.service.CartypeService;


@Service
public class CartypeImpl extends ServiceImpl<CartypeDAO, CartypeEntity> implements CartypeService {
	
	@Autowired
	private CartypeDAO dao;

	@Override
	public List<CartypeEntity> getByPages(CartypeEntity cartype) {
		
		return this.dao.getByPages(cartype);
	}

	@Override
	public int getCkName(CartypeEntity cartype) {
		
		return this.dao.getCkName(cartype);
	}

	@Override
	public int updName(CartypeEntity cartype) {
		
		return this.dao.updName(cartype);
	}

	

}

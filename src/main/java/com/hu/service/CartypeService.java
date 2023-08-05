package com.hu.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hu.entity.CartypeEntity;


public interface CartypeService extends IService<CartypeEntity> {
	
	//分页查询
	public List<CartypeEntity> getByPages(CartypeEntity cartype);
		
	//查询是否重名
	public int getCkName(CartypeEntity cartype);
	
	//修改产品名称
	public int updName(CartypeEntity cartype);

}

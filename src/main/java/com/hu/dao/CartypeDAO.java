package com.hu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hu.entity.CartypeEntity;

@Mapper
public interface CartypeDAO extends BaseMapper<CartypeEntity>{
	
	//分页查询
	public List<CartypeEntity> getByPages(CartypeEntity cartype);
		
	//查询是否重名
	public int getCkName(CartypeEntity cartype);
	
	//修改产品名称
	public int updName(CartypeEntity cartype);

}

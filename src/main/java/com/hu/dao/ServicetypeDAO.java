package com.hu.dao;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.hu.entity.ServicetypeEntity;

public interface ServicetypeDAO extends BaseMapper<ServicetypeEntity> {
	
	//分页查询
	public List<ServicetypeEntity> getByPages(ServicetypeEntity ste);
	
	//查询是否重名
	public int getCkName(ServicetypeEntity ste);
	
	//修改类型名称
	public int updName(ServicetypeEntity ste);

}

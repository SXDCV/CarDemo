package com.hu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.hu.entity.CarxlEntity;

@Mapper
public interface CarxlDAO extends BaseMapper<CarxlEntity>{

	//分页查询
	public List<CarxlEntity> getByPages(CarxlEntity carxl);
		
	//查询是否重名
	public int getCkName(CarxlEntity carxl);
	
	//查询单个
	public List<CarxlEntity> getOne(CarxlEntity carxl);
	
	//修改系列名称
	public int updName(CarxlEntity carxl);
	
	//修改汽车品牌
	public int updAName(CarxlEntity carxl);
	
	//单个查询
	public CarxlEntity carxlOne(int xid);

}

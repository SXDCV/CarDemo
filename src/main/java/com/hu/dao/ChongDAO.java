package com.hu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.hu.entity.ChongEntity;

@Mapper
public interface ChongDAO extends BaseMapper<ChongEntity> {
	
	//分页查询
	public List<ChongEntity> getByPages(ChongEntity chong);
		
	

}

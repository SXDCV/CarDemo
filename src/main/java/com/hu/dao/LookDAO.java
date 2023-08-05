package com.hu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.hu.entity.LookEntity;

@Mapper
public interface LookDAO extends BaseMapper<LookEntity>{
	
	//统计条数
	public int count(LookEntity look);
	
	//分页查询
	public List<LookEntity> getByPages(LookEntity look);
	
	
}

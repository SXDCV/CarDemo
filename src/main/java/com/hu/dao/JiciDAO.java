package com.hu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hu.entity.JiciEntity;

@Mapper
public interface JiciDAO extends BaseMapper<JiciEntity>{
	
	//分页查询
	public List<JiciEntity> getByPages(JiciEntity jici);

}

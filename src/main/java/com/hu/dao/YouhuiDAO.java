package com.hu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hu.entity.YouhuiEntity;

@Mapper
public interface YouhuiDAO extends BaseMapper<YouhuiEntity>{
	
	//分页查询
	public List<YouhuiEntity>  getByPages(YouhuiEntity youhui);
		
	//查询是否重名
	public int getCkName(YouhuiEntity youhui);
	
	//修改活动名称
	public int updName(YouhuiEntity youhui);

}

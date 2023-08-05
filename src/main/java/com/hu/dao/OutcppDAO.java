package com.hu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hu.entity.OutcppEntity;

@Mapper
public interface OutcppDAO extends BaseMapper<OutcppEntity>{
	
	//统计条数
	public int count(int fid);
	
	//分页查询
	public List<OutcppEntity> getByPages(OutcppEntity outcpp);
	
	//消费查询
	public List<OutcppEntity> getcp(OutcppEntity outcpp);

}

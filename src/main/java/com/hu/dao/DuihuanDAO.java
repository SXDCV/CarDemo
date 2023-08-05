package com.hu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hu.entity.DuihuanEntity;


@Mapper
public interface DuihuanDAO extends BaseMapper<DuihuanEntity>{
	
	//分页查询
	public List<DuihuanEntity> getByPages(DuihuanEntity duihuan);
	
	//礼品兑换记录
	public List<DuihuanEntity>	getAll(DuihuanEntity duihuan);
	
	//统计条数
	public int count(int nid);
	
	

	

}

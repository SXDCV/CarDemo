package com.hu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hu.entity.CptypeEntity;
@Mapper
public interface CptypeDAO extends BaseMapper<CptypeEntity>{
	
	//分页查询
	public List<CptypeEntity> getByPages(CptypeEntity cptype);
		
	//查询是否重名
	public int getCkName(CptypeEntity cptype);
	
	//修改产品名称
	public int updName(CptypeEntity cptype);

}

package com.hu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hu.entity.PzEntity;
@Mapper
public interface PzDAO extends BaseMapper<PzEntity>{
	
	//分页查询
	public List<PzEntity> getByPages(PzEntity pz);
		
	//查询是否重名
	public int getCkName(PzEntity pz);
	
	//修改凭证名称
	public int updName(PzEntity pz);

}

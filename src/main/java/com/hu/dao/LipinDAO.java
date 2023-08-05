package com.hu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hu.entity.LipinEntity;


@Mapper
public interface LipinDAO extends BaseMapper<LipinEntity>{
	
	//分页查询
	public List<LipinEntity> getByPages(LipinEntity lipin);
	
	//查询重复项
	public int getCkName(LipinEntity lipin);
	
	//修改库存总量
	public int  Updcount(LipinEntity lipin);
	
	//单个查询
	public LipinEntity lipinOne(LipinEntity lipin);

	

}

package com.hu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hu.entity.DjEntity;
@Mapper
public interface DjDAO extends BaseMapper<DjEntity>{
	
	//分页查询
	public List<DjEntity> getByPages(DjEntity dj);
	
	//查询是否重名
	public int getCkName(DjEntity dj);
	
	//修改等级名称
	public int updName(DjEntity dj);
	
	//修改等级积分
	public int updDjf(DjEntity dj);
	
	//修改兑换比例
	public int upddMoneyBl(DjEntity dj);
	
	//修改兑换折扣
	public int updDzk(DjEntity dj);

}

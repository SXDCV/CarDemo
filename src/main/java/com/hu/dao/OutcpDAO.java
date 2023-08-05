package com.hu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hu.entity.OutcpEntity;

@Mapper
public interface OutcpDAO extends BaseMapper<OutcpEntity>{
	
	//统计条数
	public int count(int rid);
	
	//分页查询
	public List<OutcpEntity> getByPages(OutcpEntity outcp);
	
	//条件查询
	public List<OutcpEntity> getOutcp(OutcpEntity outcp);
	
	//分页查询
	public List<OutcpEntity> getBylPages(OutcpEntity outcp);
	
	//查询单个
	public OutcpEntity outcpOne(int tid);
	
	//修改购买数量
	public int outcpUpd(OutcpEntity outcp);
	
	//查询重复购买项
	public int getCkName(OutcpEntity outcp);
	
	//增加数量
	public int addOutcp(OutcpEntity outcp);
	
	//获取消费ID
	public OutcpEntity getTid(OutcpEntity outcp);
	
	//修改状态值
	public int updFlag(int tid);

	
}

package com.hu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.hu.entity.GetcpEntity;
import com.hu.entity.OutcpEntity;

@Mapper
public interface GetcpDAO extends BaseMapper<GetcpEntity>{
	
	//统计条数
	public int count(int fid);
	
	//查询入库记录
	public List<GetcpEntity> cpOne(GetcpEntity getcp);
	
	//查询出库记录
	public List<OutcpEntity> getcpOne(int fid);
	
	//统计出库记录条数
	public int getcount(int fid);

}

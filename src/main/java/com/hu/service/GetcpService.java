package com.hu.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hu.entity.GetcpEntity;
import com.hu.entity.OutcpEntity;


public interface GetcpService extends IService<GetcpEntity> {
	
	//统计入库记录条数
	public int count(int fid);
	
	//查询入库记录
	public List<GetcpEntity> cpOne(GetcpEntity getcp);
	
	//查询出库记录
	public List<OutcpEntity> getcpOne(int fid);
	
	//统计出库记录条数
	public int getcount(int fid);
	
	

	

}

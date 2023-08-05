package com.hu.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

import com.hu.entity.OutcppEntity;


public interface OutcppService extends IService<OutcppEntity> {
	
	//统计条数
	public int count(int fid);
	
	//分页查询
	public List<OutcppEntity> getByPages(OutcppEntity outcpp);
	
	//消费查询
	public List<OutcppEntity> getcp(OutcppEntity outcpp);
	
	
		
	

}

package com.hu.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hu.entity.DuihuanEntity;


public interface DuihuanService extends IService<DuihuanEntity> {
	
	//分页查询
	public List<DuihuanEntity> getByPages(DuihuanEntity duihuan);
	
	//礼品兑换记录
	public List<DuihuanEntity>	getAll(DuihuanEntity duihuan);
	
	//统计条数
	public int count(int nid);
	
	
}

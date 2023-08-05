package com.hu.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hu.entity.LookEntity;


public interface LookService extends IService<LookEntity> {
	
	//统计条数
	public int count(LookEntity look);
	
	//分页查询
	public List<LookEntity> getByPages(LookEntity look);

}

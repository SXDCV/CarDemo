package com.hu.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

import com.hu.entity.YouhuiEntity;


public interface YouhuiService extends IService<YouhuiEntity> {
	
	//分页查询
	public List<YouhuiEntity> getByPages(YouhuiEntity youhui);
		
	//查询是否重名
	public int getCkName(YouhuiEntity youhui);
	
	//修改活动名称
	public int updName(YouhuiEntity youhui);

}

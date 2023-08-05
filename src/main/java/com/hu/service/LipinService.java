package com.hu.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hu.entity.LipinEntity;


public interface LipinService extends IService<LipinEntity> {
	
	//分页查询
	public List<LipinEntity> getByPages(LipinEntity lipin);
	
	//查询重复项
	public int getCkName(LipinEntity lipin);
	
	//修改库存总量
	public int  Updcount(LipinEntity lipin);
	
	//单个查询
	public LipinEntity lipinOne(LipinEntity lipin);
}

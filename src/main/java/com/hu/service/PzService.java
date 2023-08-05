package com.hu.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hu.entity.PzEntity;

public interface PzService extends IService<PzEntity> {
	
	//分页查询
	public List<PzEntity> getByPages(PzEntity pz);
		
	//查询是否重名
	public int getCkName(PzEntity pz);
	
	//修改凭证名称
	public int updName(PzEntity pz);

}

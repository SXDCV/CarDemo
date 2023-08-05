package com.hu.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hu.entity.CptypeEntity;


public interface CptypeService extends IService<CptypeEntity> {

	//分页查询
	public List<CptypeEntity> getByPages(CptypeEntity cptype);
		
	//查询是否重名
	public int getCkName(CptypeEntity cptype);
	
	//修改产品名称
	public int updName(CptypeEntity cptype);

}

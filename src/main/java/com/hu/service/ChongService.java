package com.hu.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hu.entity.ChongEntity;


public interface ChongService extends IService<ChongEntity> {
	
	//分页查询
	public List<ChongEntity> getByPages(ChongEntity chong);
		
	

}

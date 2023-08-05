package com.hu.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hu.entity.JiciEntity;


public interface JiciService extends IService<JiciEntity> {
	
	//分页查询
	public List<JiciEntity> getByPages(JiciEntity jici);

}

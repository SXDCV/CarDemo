package com.hu.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hu.dao.ChongDAO;
import com.hu.entity.ChongEntity;
import com.hu.service.ChongService;
@Service
public class ChongImpl extends ServiceImpl<ChongDAO, ChongEntity> implements ChongService {

	@Autowired
	private ChongDAO dao;
	
	@Override
	public List<ChongEntity> getByPages(ChongEntity chong) {
		
		return this.dao.getByPages(chong);
	}

}

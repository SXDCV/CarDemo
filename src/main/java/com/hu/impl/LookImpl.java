package com.hu.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hu.dao.LookDAO;
import com.hu.entity.LookEntity;
import com.hu.service.LookService;
@Service
public class LookImpl extends ServiceImpl<LookDAO, LookEntity> implements LookService {

	
	@Autowired
	private LookDAO dao;
	
	@Override
	public List<LookEntity> getByPages(LookEntity look) {
		
		return this.dao.getByPages(look);
	}

	@Override
	public int count(LookEntity look) {
		
		return this.dao.count(look);
	}

}

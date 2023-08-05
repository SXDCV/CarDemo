package com.hu.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.hu.dao.PzDAO;
import com.hu.entity.PzEntity;
import com.hu.service.PzService;

@Service
public class PzImpl extends ServiceImpl<PzDAO, PzEntity> implements PzService {
	
	@Autowired
	private PzDAO dao;

	@Override
	public List<PzEntity> getByPages(PzEntity pz) {
		
		return this.dao.getByPages(pz);
	}

	@Override
	public int getCkName(PzEntity pz) {
		
		return this.dao.getCkName(pz);
	}

	@Override
	public int updName(PzEntity pz) {
		
		return this.dao.updName(pz);
	}

	

}

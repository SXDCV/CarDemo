package com.hu.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hu.dao.CptypeDAO;
import com.hu.entity.CptypeEntity;
import com.hu.service.CptypeService;

@Service
public class CptypeImpl extends ServiceImpl<CptypeDAO, CptypeEntity> implements CptypeService {
	
	@Autowired
	private CptypeDAO dao;

	@Override
	public List<CptypeEntity> getByPages(CptypeEntity cptype) {
		
		return this.dao.getByPages(cptype);
	}

	@Override
	public int getCkName(CptypeEntity cptype) {
		
		return this.dao.getCkName(cptype);
	}

	@Override
	public int updName(CptypeEntity cptype) {
		
		return this.dao.updName(cptype);
	}

	

}

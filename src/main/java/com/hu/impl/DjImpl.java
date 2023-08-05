package com.hu.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hu.dao.DjDAO;
import com.hu.entity.DjEntity;
import com.hu.service.DjService;
@Service
public class DjImpl extends ServiceImpl<DjDAO,DjEntity> implements DjService{
	
	@Autowired
	private DjDAO dao;

	@Override
	public int getCkName(DjEntity dj) {
		
		return this.dao.getCkName(dj);
	}

	@Override
	public List<DjEntity> getByPages(DjEntity dj) {
		
		return this.dao.getByPages(dj);
	}

	@Override
	public int updName(DjEntity dj) {
		
		return this.dao.updName(dj);
	}

	@Override
	public int updDjf(DjEntity dj) {
		
		return this.dao.updDjf(dj);
	}

	@Override
	public int upddMoneyBl(DjEntity dj) {
		
		return this.dao.upddMoneyBl(dj);
	}

	@Override
	public int updDzk(DjEntity dj) {
		
		return this.dao.updDzk(dj);
	}

}

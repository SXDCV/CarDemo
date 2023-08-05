package com.hu.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hu.dao.OutcppDAO;
import com.hu.entity.OutcppEntity;
import com.hu.service.OutcppService;
@Service
public class OutcppImpl extends ServiceImpl<OutcppDAO, OutcppEntity> implements OutcppService {
	
	@Autowired
	private OutcppDAO dao;


	@Override
	public List<OutcppEntity> getByPages(OutcppEntity outcpp) {
		
		return this.dao.getByPages(outcpp);
	}


	@Override
	public List<OutcppEntity> getcp(OutcppEntity outcpp) {
		
		return this.dao.getcp(outcpp);
	}


	@Override
	public int count(int fid) {
		
		return this.dao.count(fid);
	}

}

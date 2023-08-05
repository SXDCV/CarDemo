package com.hu.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hu.dao.DuihuanDAO;
import com.hu.entity.DuihuanEntity;
import com.hu.service.DuihuanService;
@Service
public class DuihuanImpl extends ServiceImpl<DuihuanDAO, DuihuanEntity> implements DuihuanService {

	@Autowired
	private DuihuanDAO dao;

	@Override
	public List<DuihuanEntity> getByPages(DuihuanEntity duihuan) {
	
		return this.dao.getByPages(duihuan);
	}

	@Override
	public List<DuihuanEntity> getAll(DuihuanEntity duihuan) {
		
		return this.dao.getAll(duihuan);
	}

	@Override
	public int count(int nid) {
		
		return this.dao.count(nid);
	}

	

}

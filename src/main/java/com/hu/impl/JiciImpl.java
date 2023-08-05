package com.hu.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hu.dao.JiciDAO;
import com.hu.entity.JiciEntity;
import com.hu.service.JiciService;
@Service
public class JiciImpl extends ServiceImpl<JiciDAO, JiciEntity> implements JiciService {

	@Autowired
	private JiciDAO dao;

	@Override
	public List<JiciEntity> getByPages(JiciEntity jici) {
		
		return this.dao.getByPages(jici);
	}

}

package com.hu.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hu.dao.LipinDAO;
import com.hu.entity.LipinEntity;
import com.hu.service.LipinService;
@Service
public class LipinImpl extends ServiceImpl<LipinDAO, LipinEntity> implements LipinService {

	@Autowired
	private LipinDAO dao;

	@Override
	public List<LipinEntity> getByPages(LipinEntity lipin) {
		
		return this.dao.getByPages(lipin);
	}

	@Override
	public int getCkName(LipinEntity lipin) {
		
		return this.dao.getCkName(lipin);
	}

	@Override
	public int Updcount(LipinEntity lipin) {
		
		return this.dao.Updcount(lipin);
	}

	@Override
	public LipinEntity lipinOne(LipinEntity lipin) {
		
		return this.dao.lipinOne(lipin);
	}

}

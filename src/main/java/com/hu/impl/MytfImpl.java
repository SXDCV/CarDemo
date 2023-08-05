package com.hu.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hu.dao.MytfDAO;
import com.hu.entity.MytfEntity;
import com.hu.service.MytfService;
@Service
public class MytfImpl extends ServiceImpl<MytfDAO, MytfEntity> implements MytfService {
	
	@Autowired
	private MytfDAO dao;

	
	@Override
	public List<MytfEntity> getByPages(MytfEntity mytf) {
		
		return this.dao.getByPages(mytf);
	}

	@Override
	public int getCkName(MytfEntity mytf) {
		
		return this.dao.getCkName(mytf);
	}

	@Override
	public MytfEntity mytfAll(int fid) {
		
		return this.dao.mytfAll(fid);
	}

	@Override
	public int updFcount(MytfEntity mytf) {
		
		return this.dao.updFcount(mytf);
	}

	@Override
	public int mytfUpd(MytfEntity mytf) {
		
		return this.dao.mytfUpd(mytf);
	}

	@Override
	public List<MytfEntity> mytfOne(int cid) {
		
		return this.dao.mytfOne(cid);
	}

	@Override
	public void FcountUpd(MytfEntity mytf) {
		
		this.dao.FcountUpd(mytf);
	}

}

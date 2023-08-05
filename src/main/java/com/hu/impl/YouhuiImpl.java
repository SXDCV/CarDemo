package com.hu.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hu.dao.YouhuiDAO;
import com.hu.entity.YouhuiEntity;
import com.hu.service.YouhuiService;
@Service
public class YouhuiImpl extends ServiceImpl<YouhuiDAO, YouhuiEntity> implements YouhuiService {

	@Autowired
	private YouhuiDAO dao;

	@Override
	public List<YouhuiEntity>  getByPages(YouhuiEntity youhui) {
		
		return this.dao.getByPages(youhui);
	}

	@Override
	public int getCkName(YouhuiEntity youhui) {
		
		return this.dao.getCkName(youhui);
	}

	@Override
	public int updName(YouhuiEntity youhui) {
		
		return this.dao.updName(youhui);
	}

}

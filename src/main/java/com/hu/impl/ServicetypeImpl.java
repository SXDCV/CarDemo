package com.hu.impl;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hu.dao.ServicetypeDAO;
import com.hu.entity.ServicetypeEntity;
import com.hu.service.ServicetypeService;
@Service
public class ServicetypeImpl extends ServiceImpl<ServicetypeDAO,ServicetypeEntity > implements ServicetypeService {

	@Autowired
	private ServicetypeDAO dao;
	@Override
	public List<ServicetypeEntity> getByPages(ServicetypeEntity ste) {
		
		return this.dao.getByPages(ste);
	}

	@Override
	public int getCkName(ServicetypeEntity ste) {
		
		return this.dao.getCkName(ste);
	}

	@Override
	public int updName(ServicetypeEntity ste) {
		
		return this.dao.updName(ste);
	}


}

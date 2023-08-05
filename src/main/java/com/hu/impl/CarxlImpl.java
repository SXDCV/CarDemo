package com.hu.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hu.dao.CarxlDAO;
import com.hu.entity.CarxlEntity;
import com.hu.service.CarxlService;
@Service
public class CarxlImpl extends ServiceImpl<CarxlDAO, CarxlEntity> implements CarxlService {
	
	@Autowired
	private CarxlDAO dao;

	@Override
	public List<CarxlEntity> getByPages(CarxlEntity carxl) {
		
		return this.dao.getByPages(carxl);
	}

	@Override
	public int getCkName(CarxlEntity carxl) {
		
		return this.dao.getCkName(carxl);
	}

	@Override
	public int updName(CarxlEntity carxl) {
		
		return this.dao.updName(carxl);
	}

	@Override
	public CarxlEntity carxlOne(int xid) {
		
		return this.dao.carxlOne(xid);
	}

	@Override
	public int updAName(CarxlEntity carxl) {
		
		return this.dao.updAName(carxl);
	}

	@Override
	public List<CarxlEntity> getOne(CarxlEntity carxl) {
		
		return this.dao.getOne(carxl);
	}

	

}

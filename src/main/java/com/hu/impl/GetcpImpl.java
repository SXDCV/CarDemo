package com.hu.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hu.dao.GetcpDAO;
import com.hu.entity.GetcpEntity;
import com.hu.entity.OutcpEntity;
import com.hu.service.GetcpService;
@Service
public class GetcpImpl extends ServiceImpl<GetcpDAO, GetcpEntity> implements GetcpService {
	
	@Autowired
	private GetcpDAO dao;

	
	@Override
	public int count(int fid) {
		
		return this.dao.count(fid);
	}
	
	
	@Override
	public List<GetcpEntity> cpOne(GetcpEntity getcp) {
		
		return this.dao.cpOne(getcp);
	}


	@Override
	public List<OutcpEntity> getcpOne(int fid) {
		
		return this.dao.getcpOne(fid);
	}


	@Override
	public int getcount(int fid) {
		
		return this.dao.getcount(fid);
	}

	
	
	
}

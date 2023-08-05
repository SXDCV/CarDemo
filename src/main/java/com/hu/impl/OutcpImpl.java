package com.hu.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hu.dao.OutcpDAO;
import com.hu.entity.OutcpEntity;
import com.hu.service.OutcpService;
@Service
public class OutcpImpl extends ServiceImpl<OutcpDAO, OutcpEntity> implements OutcpService {

	@Autowired
	private OutcpDAO dao;
	
	@Override
	public int count(int rid) {
		
		return this.dao.count(rid);
	}
	
	@Override
	public List<OutcpEntity> getByPages(OutcpEntity outcp) {
		
		return this.dao.getByPages(outcp);
	}

	@Override
	public List<OutcpEntity> getOutcp(OutcpEntity outcp) {
		
		return this.dao.getOutcp(outcp);
	}

	@Override
	public List<OutcpEntity> getBylPages(OutcpEntity outcp) {
		
		return this.dao.getBylPages(outcp);
	}

	@Override
	public OutcpEntity outcpOne(int tid) {
		
		return this.dao.outcpOne(tid);
	}

	@Override
	public int outcpUpd(OutcpEntity outcp) {
		
		return this.dao.outcpUpd(outcp);
	}

	@Override
	public int getCkName(OutcpEntity outcp) {
		
		return this.dao.getCkName(outcp);
	}

	@Override
	public int addOutcp(OutcpEntity outcp) {
		
		return this.dao.addOutcp(outcp);
	}

	@Override
	public OutcpEntity getTid(OutcpEntity outcp) {
		
		return this.dao.getTid(outcp);
	}

	@Override
	public int updFlag(int tid) {
		
		return this.dao.updFlag(tid);
	}

	

}

package com.hu.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hu.dao.MemberDAO;
import com.hu.entity.MemberEntity;
import com.hu.service.MemberService;
@Service
public class MemberImpl extends ServiceImpl<MemberDAO, MemberEntity> implements MemberService {
	
	@Autowired
	private MemberDAO dao;

	public List<MemberEntity> getByPages(MemberEntity member) {
		
		return this.dao.getByPages(member);
	}

	@Override
	public int getCkName(MemberEntity member) {
		
		return this.dao.getCkName(member);
	}

	@Override
	public MemberEntity getOne(int rid) {
		
		return this.dao.getOne(rid);
	}

	@Override
	public int updMoney(MemberEntity member) {
		
		return this.dao.updMoney(member);
	}

	@Override
	public int updName(MemberEntity member) {
		
		return this.dao.updName(member);
	}

	@Override
	public MemberEntity getMemberOne(MemberEntity member) {
		
		return this.dao.getMemberOne(member);
	}

	@Override
	public void MoneyUpd(MemberEntity member) {
		
		this.dao.MoneyUpd(member);
		
	}

	@Override
	public void UpdJf(MemberEntity member) {
		
		this.dao.UpdJf(member);
	}

	

	@Override
	public MemberEntity getAll(MemberEntity member) {
		
		return this.dao.getAll(member);
	}

	@Override
	public void JfUpd(MemberEntity member) {
		
		this.dao.JfUpd(member);
		
	}

	@Override
	public List<MemberEntity> getchart() {
		
		return this.dao.getchart();
	}

	@Override
	public List<MemberEntity> djall(MemberEntity member) {
		
		return this.dao.djall(member);
	}

	@Override
	public int djcount(MemberEntity member) {
		
		return this.dao.djcount(member);
	}

}

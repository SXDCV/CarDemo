package com.hu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.hu.entity.MemberEntity;

@Mapper
public interface MemberDAO extends BaseMapper<MemberEntity>{
	

	//分页查询
	public List<MemberEntity> getByPages(MemberEntity member);
		
	//查询是否重名
	public int getCkName(MemberEntity member);
	
	//单个查询
	public MemberEntity getOne(int rid);
	
	//修改余额
	public int updMoney(MemberEntity member);

	//修改会员姓名
	public int updName(MemberEntity member);

	//查询会员
	public MemberEntity getMemberOne(MemberEntity member);
	
	//修改余额
	public void MoneyUpd(MemberEntity member);
	
	//修改积分
	public void UpdJf(MemberEntity member);
	
	//根据卡号获取信息
	public MemberEntity getAll(MemberEntity member);
	
	//修改积分
	public void JfUpd(MemberEntity member);

	//根据会员等级查询人数
	public List<MemberEntity> getchart();
	
	public List<MemberEntity> djall(MemberEntity member);
	
	public int djcount(MemberEntity member);
}

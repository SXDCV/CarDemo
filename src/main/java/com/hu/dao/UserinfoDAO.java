package com.hu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hu.entity.UserinfoEntity;
@Mapper
public interface UserinfoDAO extends BaseMapper<UserinfoEntity> {
	
	//查询账号是否存在
	public int getName(UserinfoEntity user);
	
	//登录
	public int login(UserinfoEntity user);
	
	//分页查询
	public List<UserinfoEntity> getByPages(UserinfoEntity user);
		
	//查询是否重名
	public int getCkName(UserinfoEntity user);
	
	//单个查询
	public UserinfoEntity getOne(String uname);
	
	

}

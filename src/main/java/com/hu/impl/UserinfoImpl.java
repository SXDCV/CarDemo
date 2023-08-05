package com.hu.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hu.dao.UserinfoDAO;
import com.hu.entity.UserinfoEntity;
import com.hu.service.UserinfoService;

@Service
public class UserinfoImpl extends ServiceImpl<UserinfoDAO, UserinfoEntity> implements UserinfoService{
	
	@Autowired
	private UserinfoDAO dao;

	@Override
	public int getName(UserinfoEntity user) {
		
		return this.dao.getName(user);
	}

	@Override
	public int login(UserinfoEntity user) {
		
		return this.dao.login(user);
	}

	@Override
	public List<UserinfoEntity> getByPages(UserinfoEntity user) {
		List<UserinfoEntity> userlist = dao.getByPages(user);
		for (int i = 0; i < userlist.size(); i++){
			UserinfoEntity e=(UserinfoEntity) userlist.get(i);
			
			if (e.getUpwd().length()>0) {
				e.setPwd("******");
			}
		
			if(e.getUsex()==1){
				e.setSex("男");
			}
			else{
				e.setSex("女");
			}
		}
		return userlist;
	}

	@Override
	public int getCkName(UserinfoEntity user) {
		
		return this.dao.getCkName(user);
	}

	@Override
	public UserinfoEntity getOne(String uname) {
		
		return this.dao.getOne(uname);
	}

}

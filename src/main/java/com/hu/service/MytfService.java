package com.hu.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

import com.hu.entity.MytfEntity;


public interface MytfService extends IService<MytfEntity> {
	
	//分页查询
	public List<MytfEntity> getByPages(MytfEntity mytf);
		
	//查询是否重名
	public int getCkName(MytfEntity mytf);
	
	//条件全查询
	public MytfEntity mytfAll(int fid);
	
	//修改
	public int mytfUpd(MytfEntity mytf);
	
	//修改产品数量
	public int updFcount(MytfEntity mytf);
	
	//根据类型查询
	public List<MytfEntity> mytfOne(int cid);
	
	//修改产品数量
	public void FcountUpd(MytfEntity mytf);


}

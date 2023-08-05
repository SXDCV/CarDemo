package com.hu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hu.entity.PzEntity;
import com.hu.service.PzService;


import net.sf.json.JSONObject;

@Controller
@RequestMapping("pz.do")
public class PzController {
	
	@Autowired
	private PzService pzservice;
	
	//跳转客户凭证
	@RequestMapping(params="method=toall")
	public String toall() {
		return "pz/pzAll";
	}
	
	//分页查询
	@RequestMapping(params="method=getByPages")
	@ResponseBody
	public JSONObject getByPages(PzEntity pz) {
		int count = pzservice.count();
		List<PzEntity> pzlist = pzservice.getByPages(pz);
		
		JSONObject obj = new JSONObject();
		obj.put("rows", pzlist);
		obj.put("total", count);
		return obj;
	}
	
	//全查询
	@RequestMapping(params="method=pzAll")
	@ResponseBody
	public List<PzEntity> pzAll(){
		List<PzEntity> pzlist = pzservice.list();
		return pzlist;
	}
	
	//跳转新增页面
	@RequestMapping(params="method=toadd")
	public String toadd() {
		return "pz/pzAdd";
	}
	
	//查询是否重名
	@RequestMapping(params="method=getCkName")
	@ResponseBody
	public int getCkName(PzEntity pz) {
		int count = pzservice.getCkName(pz);
		return count;
	}
	
	//新增客户凭证
	@RequestMapping(params="method=pzAdd")
	@ResponseBody
	public boolean pzAdd(PzEntity pz) {
		boolean count = pzservice.save(pz);
		return count;
	}
	
	
	//查询单个客户凭证
	@RequestMapping(params="method=pzOne")
	public String pzOne(int zid,Model model) {
		PzEntity pz = pzservice.getById(zid);
		model.addAttribute("pz", pz);
		return "pz/pzUpd";
	}
	
	//修改单个客户凭证
	@RequestMapping(params="method=pzUpd")
	@ResponseBody
	public boolean pzUpd(PzEntity pz) {

		boolean count = pzservice.updateById(pz);
		return count;
	}
	
	//修改凭证名称
	@RequestMapping(params="method=updName")
	@ResponseBody
	public int updName(PzEntity pz) {
		int count = pzservice.updName(pz);
		return count;
	}
	
	

}

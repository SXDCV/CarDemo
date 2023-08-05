package com.hu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hu.entity.CptypeEntity;
import com.hu.service.CptypeService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("cptype.do")
public class CptypeController {
	
	@Autowired
	private CptypeService cptypeservice;
	
	//跳转产品类别
	@RequestMapping(params="method=toall")
	public String toall() {
		return "cptype/cptypeAll";
	}
	
	//分页查询
	@RequestMapping(params="method=getByPages")
	@ResponseBody
	public JSONObject getByPages(CptypeEntity cptype) {
		int count = cptypeservice.count();
		List<CptypeEntity> cptypelist = cptypeservice.getByPages(cptype);
		
		JSONObject obj = new JSONObject();
		obj.put("rows", cptypelist);
		obj.put("total", count);
		return obj;
	}
	
	//全查询
	@RequestMapping(params="method=cptypeAll")
	@ResponseBody
	public List<CptypeEntity> cptypeAll(){
		List<CptypeEntity> cptypelist = cptypeservice.list();
		return cptypelist;
	}
	
	//跳转新增页面
	@RequestMapping(params="method=toadd")
	public String toadd() {
		return "cptype/cptypeAdd";
	}
	
	//查询是否重名
	@RequestMapping(params="method=getCkName")
	@ResponseBody
	public int getCkName(CptypeEntity cptype) {
		
		int count = cptypeservice.getCkName(cptype);
		
		return count;
	}
	
	//新增产品类别
	@RequestMapping(params="method=cptypeAdd")
	@ResponseBody
	public boolean cptypeAdd(CptypeEntity cptype) {
		boolean count = cptypeservice.save(cptype);
		return count;
	}
	
	
	//查询单个产品类别
	@RequestMapping(params="method=cptypeOne")
	public String cptypeOne(int cid,Model model) {
		CptypeEntity cptype = cptypeservice.getById(cid);
		model.addAttribute("cptype", cptype);
		return "cptype/cptypeUpd";
	}
	
	//修改单个产品类别
	@RequestMapping(params="method=cptypeUpd")
	@ResponseBody
	public boolean cptypeUpd(CptypeEntity cptype) {

		boolean count = cptypeservice.updateById(cptype);
		return count;
	}
	
	//修改产品名称
	@RequestMapping(params="method=updName")
	@ResponseBody
	public int updName(CptypeEntity cptype) {
		int count = cptypeservice.updName(cptype);
		return count;
	}
	
	

}

package com.hu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hu.entity.ServicetypeEntity;

import com.hu.service.ServicetypeService;


import net.sf.json.JSONObject;

@Controller
@RequestMapping("servicetype.do")
public class ServicetypeController {
	
	@Autowired
	private ServicetypeService servicetypeService;
	
	//跳转服务类型
	@RequestMapping(params="method=toall")
	public String toall() {
		return "servicetype/servicetypeAll";
	}
	
	//分页查询
	@RequestMapping(params="method=getByPages")
	@ResponseBody
	public JSONObject getByPages(ServicetypeEntity ste) {
		int count = servicetypeService.count();
		List<ServicetypeEntity> servicetypelist = servicetypeService.getByPages(ste);
		
		JSONObject obj = new JSONObject();
		obj.put("rows", servicetypelist);
		obj.put("total", count);
		return obj;
	}
	
	//全查询
	@RequestMapping(params="method=getOne")
	@ResponseBody
	public List<ServicetypeEntity> getOne() {
		List<ServicetypeEntity> list = servicetypeService.list();
		return list;
	}
	
	//跳转新增页面
	@RequestMapping(params="method=toadd")
	public String toadd() {
		return "servicetype/servicetypeAdd";
	}
	
	//查询是否重名
	@RequestMapping(params="method=getCkName")
	@ResponseBody
	public int getCkName(ServicetypeEntity ste) {
		int count = servicetypeService.getCkName(ste);
		return count;
	}
	
	//新增服务类型
	@RequestMapping(params="method=servicetypeAdd")
	@ResponseBody
	public boolean servicetypeAdd(ServicetypeEntity ste) {
		boolean count = servicetypeService.save(ste);
		return count;
	}
	
	
	//查询单个服务类型
	@RequestMapping(params="method=servicetypeOne")
	public String servicetypeOne(int sid,Model model) {
		ServicetypeEntity servicetype = servicetypeService.getById(sid);
		model.addAttribute("servicetype", servicetype);
		return "servicetype/servicetypeUpd";
	}
	
	//修改单个服务类型
	@RequestMapping(params="method=servicetypeUpd")
	@ResponseBody
	public boolean servicetypeUpd(ServicetypeEntity ste) {

		boolean count = servicetypeService.updateById(ste);
		return count;
	}
	
	//修改类型名称
	@RequestMapping(params="method=updName")
	@ResponseBody
	public int updName(ServicetypeEntity ste) {
		int count = servicetypeService.updName(ste);
		return count;
	}
	
	

}

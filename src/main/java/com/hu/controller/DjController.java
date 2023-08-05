package com.hu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hu.entity.DjEntity;
import com.hu.service.DjService;
import com.hu.util.Mytwo;

import org.springframework.ui.Model;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("dj.do")
public class DjController {
	
	@Autowired
	private DjService djService;
	
	@RequestMapping(params="method=toall")
	public String toall() {
		return "dj/djAll";
	}
	
	//分页查询
	@RequestMapping(params="method=getByPages")
	@ResponseBody
	public JSONObject getByPages(DjEntity dj) {
		int count = djService.count();
		List<DjEntity> djlist = djService.getByPages(dj);
		for (DjEntity d : djlist) {
			d.setJf(Mytwo.two(d.getDjf()));
			d.setMoneyBl(Mytwo.two(d.getDmoneyBl()));
			d.setZk(Mytwo.two(d.getDzk()));
		}
		
		
		JSONObject obj = new JSONObject();
		obj.put("rows", djlist);
		obj.put("total", count);
		return obj;
	}
	
	//查询所有会员等级
	@RequestMapping(params="method=djAll")
	@ResponseBody
	public List<DjEntity> djAll() {
		List<DjEntity> djlist = djService.list();
		return djlist;
	}
	
	//跳转新增页面
	@RequestMapping(params="method=toadd")
	public String toadd() {
		return "dj/djAdd";
	}
	
	//查询是否重名
	@RequestMapping(params="method=getCkName")
	@ResponseBody
	public int getCkName(DjEntity dj) {
		int count = djService.getCkName(dj);
		return count;
	}
	
	//新增会员等级
	@RequestMapping(params="method=djAdd")
	@ResponseBody
	public boolean djAdd(DjEntity dj) {
		boolean count = djService.save(dj);
		return count;
	}
	
	
	//查询单个会员等级
	@RequestMapping(params="method=djOne")
	public String djOne(int did,Model model) {
		DjEntity dj = djService.getById(did);
		model.addAttribute("dj", dj);
		return "dj/djUpd";
	}
	
	//修改单个会员等级
	@RequestMapping(params="method=djUpd")
	@ResponseBody
	public boolean djUpd(DjEntity dj) {
		boolean count = djService.updateById(dj);
		return count;
	}
	
	//修改会员等级
	@RequestMapping(params="method=updName")
	@ResponseBody
	public int updName(DjEntity dj) {
		int count = djService.updName(dj);
		return count;
	}
	
	//修改等级积分
	@RequestMapping(params="method=updDjf")
	@ResponseBody
	public int updDjf(DjEntity dj) {
		int count = djService.updDjf(dj);
		return count;
	}
	
	//修改兑换比例
	@RequestMapping(params="method=upddMoneyBl")
	@ResponseBody
	public int upddMoneyBl(DjEntity dj) {
		int count = djService.upddMoneyBl(dj);
		return count;
	}
	
	//修改兑换折扣
	@RequestMapping(params="method=updDzk")
	@ResponseBody
	public int updDzk(DjEntity dj) {
		int count = djService.updDzk(dj);
		return count;
	}
	
	
	

}

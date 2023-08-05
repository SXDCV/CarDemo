package com.hu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hu.entity.YouhuiEntity;
import com.hu.service.YouhuiService;
import com.hu.util.Mytwo;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("youhui.do")
public class YouhuiController {
	
	@Autowired
	private YouhuiService youhuiService;
	
	//跳转优惠活动
	@RequestMapping(params="method=toall")
	public String toall() {
		return "youhui/youhuiAll";
	}
	
	//分页查询
	@RequestMapping(params="method=getByPages")
	@ResponseBody
	public JSONObject getByPages(YouhuiEntity youhui) {
		int count = youhuiService.count();
		List<YouhuiEntity> youhuilist = youhuiService.getByPages(youhui);
		for (YouhuiEntity y : youhuilist) {
			y.setMoney(Mytwo.two(y.getYmoney()));
			y.setLessmoney(Mytwo.two(y.getYlessmoney()));
		}
		
		JSONObject obj = new JSONObject();
		obj.put("rows", youhuilist);
		obj.put("total", count);
		return obj;
	}
	
	//查询全部
	@RequestMapping(params="method=youhuiAll")
	@ResponseBody
	public List<YouhuiEntity> youhuiAll(){
		List<YouhuiEntity> youhuilist = youhuiService.list();
		return youhuilist;
	}
	
	//跳转新增页面
	@RequestMapping(params="method=toadd")
	public String toadd() {
		return "youhui/youhuiAdd";
	}
	
	//查询是否重名
	@RequestMapping(params="method=getCkName")
	@ResponseBody
	public int getCkName(YouhuiEntity youhui) {
		int count = youhuiService.getCkName(youhui);
		return count;
	}
	
	//新增优惠活动
	@RequestMapping(params="method=youhuiAdd")
	@ResponseBody
	public boolean youhuiAdd(YouhuiEntity youhui) {
		boolean count = youhuiService.save(youhui);
		return count;
	}
	
	//查询单个优惠活动
	@RequestMapping(params="method=getOne")
	@ResponseBody
	public YouhuiEntity getOne(int yid) {
		YouhuiEntity youhui = youhuiService.getById(yid);
		return youhui;
	}
	
	
	//查询单个优惠活动
	@RequestMapping(params="method=youhuiOne")
	public String youhuiOne(int yid,Model model) {
		YouhuiEntity youhui = youhuiService.getById(yid);
		model.addAttribute("youhui", youhui);
		return "youhui/youhuiUpd";
	}
	
	//修改单个优惠活动
	@RequestMapping(params="method=youhuiUpd")
	@ResponseBody
	public boolean youhuiUpd(YouhuiEntity youhui) {
		boolean count = youhuiService.updateById(youhui);
		return count;
	}
	
	

}

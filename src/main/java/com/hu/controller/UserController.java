package com.hu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hu.entity.UserinfoEntity;
import com.hu.service.UserinfoService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("User.do")
public class UserController {

	@Autowired
	private UserinfoService userinfoService;
	

	//跳转优惠活动
	@RequestMapping(params="method=toall")
	public String toall() {
		return "user/userAll";
	}
	
	//分页查询
	@RequestMapping(params="method=getByPages")
	@ResponseBody
	public JSONObject getByPages(UserinfoEntity user) {
		int count = userinfoService.count();
		List<UserinfoEntity> userlist = userinfoService.getByPages(user);
		
		
		JSONObject obj = new JSONObject();
		obj.put("rows", userlist);
		obj.put("total", count);
		return obj;
	}
	
	//跳转新增页面
	@RequestMapping(params="method=toadd")
	public String toadd() {
		return "user/userAdd";
	}
	
	//查询是否重名
	@RequestMapping(params="method=getCkName")
	@ResponseBody
	public int getCkName(UserinfoEntity user) {
		int count = userinfoService.getCkName(user);
		return count;
	}
	
	//新增会员等级
	@RequestMapping(params="method=UserAdd")
	@ResponseBody
	public boolean UserAdd(UserinfoEntity user) {
		boolean count = userinfoService.save(user);
		return count;
	}
	
	
	//查询单个会员等级
	@RequestMapping(params="method=UserOne")
	public String UserOne(int uid,Model model) {
		UserinfoEntity user = userinfoService.getById(uid);
		model.addAttribute("user", user);
		return "user/userUpd";
	}
	
	//修改单个会员等级
	@RequestMapping(params="method=UserUpd")
	@ResponseBody
	public boolean UserUpd(UserinfoEntity user) {
		boolean count = userinfoService.updateById(user);
		return count;
	}
}

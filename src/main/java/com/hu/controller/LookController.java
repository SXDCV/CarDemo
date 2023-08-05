package com.hu.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hu.entity.LookEntity;
import com.hu.entity.UserinfoEntity;
import com.hu.service.LookService;
import com.hu.service.UserinfoService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("look.do")
public class LookController {
	
	@Autowired
	private LookService lookservice;
	
	@Autowired
	private UserinfoService userinfoservice;
	
	//跳转查询页面
	@RequestMapping(params="method=toall")
	public String toall() {
		return "look/lookAll";
	}
	
	//分页查询
	@RequestMapping(params="method=getByPages")
	@ResponseBody
	public JSONObject getByPages(LookEntity look) {
		String rcard=look.getRcard();
		String ktime=look.getKtime();
		
		if(rcard==null||rcard.length()==0)
		{
			look.setRcard(null);
		}
		if(ktime==null||ktime.length()==0)
		{
			look.setKtime(null);
		}
		
		
		int count = lookservice.count(look);
		List<LookEntity> looklist = lookservice.getByPages(look);
		JSONObject obj = new JSONObject();
		obj.put("rows", looklist);
		obj.put("total", count);
		return obj;
		
	}
	
	
	//跳转查询页面
	@RequestMapping(params="method=toadd")
	public String toadd() {
		return "look/lookAdd";
	}
	
	//新增关怀记录
	@RequestMapping(params="method=lookAdd")
	@ResponseBody
	public boolean lookAdd(LookEntity look,HttpSession session) {
		Date d = new Date();
		SimpleDateFormat ff = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String time = ff.format(d);
		String uname=(String) session.getAttribute("myuser");
		UserinfoEntity userinfo = userinfoservice.getOne(uname);
		look.setUid(userinfo.getUid());
		look.setKtime(time);
		
		boolean count = lookservice.save(look);
		return count;
	}

}

package com.hu.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hu.entity.JiciEntity;
import com.hu.entity.MemberEntity;
import com.hu.entity.UserinfoEntity;
import com.hu.service.JiciService;
import com.hu.service.MemberService;
import com.hu.service.UserinfoService;
import com.hu.util.Mytwo;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("jici.do")
public class JiciController {
	
	@Autowired
	private JiciService jiciService;
	
	@Autowired
	private UserinfoService userinfoService;
	
	@Autowired
	private MemberService memberservice;
	
	//跳转查询页面
	@RequestMapping(params="method=toall")
	public String toall() {
		return "jici/jiciAll";
	}
	
	
	//分页查询
	@RequestMapping(params="method=getByPages")
	@ResponseBody
	public JSONObject getByPages(JiciEntity jici) {
		int count = jiciService.count();
		List<JiciEntity> jicilist = jiciService.getByPages(jici);
		for (JiciEntity j : jicilist) {
			j.setMoney(Mytwo.two(j.getJmoney()));
		}
		
		JSONObject obj = new JSONObject();
		obj.put("rows", jicilist);
		obj.put("total", count);
		return obj;
	}
	
	//跳转新增页面
	@RequestMapping(params="method=toadd")
	public String toadd() {
		return "jici/jiciAdd";
	}
	
	//新增购买商品信息
	@RequestMapping(params="method=jiciAdd")
	@ResponseBody
	public boolean jiciAdd(JiciEntity jici,HttpSession session) {
		Date d = new Date();
		SimpleDateFormat ff = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String time = ff.format(d);
		String uname=(String) session.getAttribute("myuser");
		UserinfoEntity userinfo = userinfoService.getOne(uname);
		jici.setUid(userinfo.getUid());
		jici.setJtime(time);
		MemberEntity member=new MemberEntity();
		member.setRmoney(jici.getJmoney());
		member.setRid(jici.getRid());
		memberservice.MoneyUpd(member);
		boolean count = jiciService.save(jici);
		return count;
	}
	
	

}

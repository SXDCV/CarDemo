package com.hu.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hu.entity.DuihuanEntity;
import com.hu.entity.LipinEntity;
import com.hu.entity.MemberEntity;
import com.hu.entity.UserinfoEntity;
import com.hu.service.DuihuanService;
import com.hu.service.LipinService;
import com.hu.service.MemberService;
import com.hu.service.UserinfoService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("duihuan")
public class DuihuanController {
	
	@Autowired
	private DuihuanService duihuanService;
	
	@Autowired
	private UserinfoService userinfoservice;
	
	@Autowired
	private MemberService memberservice;
	
	@Autowired
	private LipinService lipinservice;
	
	//跳转查询页面
	@RequestMapping(params="method=toall")
	public String toall() {
		return "duihuan/duihuanAll";
	}
	
	//分页查询
	@RequestMapping(params="method=getByPages")
	@ResponseBody
	public JSONObject getByPages(DuihuanEntity duihuan) {
		int count = duihuanService.count();
		List<DuihuanEntity> duihuanlist = duihuanService.getByPages(duihuan);
		
		JSONObject obj = new JSONObject();
		obj.put("rows", duihuanlist);
		obj.put("total", count);
		return obj;
	}
	
	
	//跳转礼品兑换页面
	@RequestMapping(params="method=toadd")
	public String toadd() {
		return "duihuan/duihuanAdd";
	}
	
	//礼品兑换
	@RequestMapping(params="method=duihuanAdd")
	@ResponseBody
	public boolean duihuanAdd(DuihuanEntity duihuan,HttpSession session) {
		Date d = new Date();
		SimpleDateFormat ff = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String time = ff.format(d);
		String uname=(String) session.getAttribute("myuser");
		UserinfoEntity userinfo = userinfoservice.getOne(uname);
		duihuan.setUid(userinfo.getUid());
		duihuan.setHtime(time);
		
		MemberEntity member=new MemberEntity();
		member.setRjf(duihuan.getSjf());
		member.setRid(duihuan.getRid());
		memberservice.JfUpd(member);
		
		LipinEntity lipin=new LipinEntity();
		lipin.setNncount(duihuan.getHcount());
		lipin.setNid(duihuan.getNid());
		lipinservice.Updcount(lipin);
		
		boolean count = duihuanService.save(duihuan);
		return count;
		
	}
}

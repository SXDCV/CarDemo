package com.hu.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hu.entity.MytfEntity;
import com.hu.entity.OutcppEntity;
import com.hu.entity.UserinfoEntity;
import com.hu.service.MytfService;
import com.hu.service.OutcppService;
import com.hu.service.UserinfoService;
import com.hu.util.Mytwo;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("outcpp.do")
public class OutcppController {
	
	@Autowired
	private OutcppService outcppService;
	
	@Autowired
	private UserinfoService userinfoservice;
	
	@Autowired
	private MytfService mytfservice;
	
	//跳转查询页面
	@RequestMapping(params="method=toall")
	public String toall() {
		return "outcpp/outcppAll";
	}
	
	
	//分页查询
	@RequestMapping(params="method=getByPages")
	@ResponseBody
	public JSONObject getByPages(OutcppEntity outcpp) {
		int count = outcppService.count();
		List<OutcppEntity> outcpplist = outcppService.getByPages(outcpp);
		for (OutcppEntity o : outcpplist) {
			o.setTxj(o.getFoutprice()*o.getWcount());
			o.setXj(Mytwo.two(o.getTxj()));
			o.setOutprice(Mytwo.two(o.getFoutprice()));
		}
		
		JSONObject obj = new JSONObject();
		obj.put("rows", outcpplist);
		obj.put("total", count);
		return obj;
	}
	
	
	//出库查询
	@RequestMapping(params="method=getcp")
	@ResponseBody
	public JSONObject getcp(OutcppEntity outcpp) {
		int count = outcppService.count(outcpp.getFid());
		List<OutcppEntity> outcpplist = outcppService.getcp(outcpp);
		for (OutcppEntity o : outcpplist) {
			o.setTxj(o.getFoutprice()*o.getWcount());
			o.setXj(Mytwo.two(o.getTxj()));
			o.setOutprice(Mytwo.two(o.getFoutprice()));
		}
		
		JSONObject obj = new JSONObject();
		obj.put("rows", outcpplist);
		obj.put("total", count);
		return obj;
	}
	
	//跳转新增页面
	@RequestMapping(params="method=toadd")
	public String toadd() {
		return "outcpp/outcppAdd";
	}
	
	//新增购买商品信息
	@RequestMapping(params="method=outcppAdd")
	@ResponseBody
	public boolean outcppAdd(OutcppEntity outcpp,HttpSession session) {
		Date d = new Date();
		SimpleDateFormat ff = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String time = ff.format(d);
		String uname=(String) session.getAttribute("myuser");
		UserinfoEntity userinfo = userinfoservice.getOne(uname);
		outcpp.setUid(userinfo.getUid());
		outcpp.setWtime(time);
		MytfEntity mytf=new MytfEntity();
		mytf.setFcount(outcpp.getWcount());
		mytf.setFid(outcpp.getFid());
		mytfservice.FcountUpd(mytf);
		boolean count = outcppService.save(outcpp);
		return count;
	}
}

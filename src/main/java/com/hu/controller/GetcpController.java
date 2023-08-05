package com.hu.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hu.entity.GetcpEntity;
import com.hu.entity.OutcpEntity;
import com.hu.entity.UserinfoEntity;
import com.hu.service.GetcpService;
import com.hu.service.UserinfoService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("getcp.do")
public class GetcpController {
	
	@Autowired
	private GetcpService getcpService;
	
	@Autowired
	private UserinfoService userinfoService;
	
	
	//跳转查询页面
	@RequestMapping(params="method=toall")
	public String toall() {
		return "getcp/getcpAll";
	}
	
	//跳转查询页面
	@RequestMapping(params="method=tolist")
	public String toadd(int fid,Model model) {
		model.addAttribute("fid", fid);
		return "getcp/addAll";
		
	}
	
	//跳转查询页面
		@RequestMapping(params="method=tocp")
		public String tocp(int fid,Model model) {
			model.addAttribute("fid", fid);
			return "getcp/ckAll";
			
		}
	
	//分页查询
	@RequestMapping(params="method=getByPages")
	@ResponseBody
	public JSONObject getByPages(GetcpEntity getcp) {
		int count = getcpService.count(getcp.getFid());
		List<GetcpEntity> cpOne = getcpService.cpOne(getcp);
		JSONArray arr = new JSONArray();
		for (GetcpEntity o : cpOne) {
			JSONObject s = new JSONObject();
			s.put("gid", o.getGid());
			s.put("fname", o.getFname());
			s.put("cname", o.getCname());
			s.put("gcount", o.getGcount());
			s.put("gtime", o.getGtime());
			s.put("uname", o.getUname());
			arr.add(s);
		}
		
		JSONObject obj = new JSONObject();
		obj.put("rows", arr);
		obj.put("total", count);
		return obj;
	}
	
	//跳转查询页面
	@RequestMapping(params="method=add")
	public String add(int fid,Model model) {
		model.addAttribute("fid", fid);
		return "getcp/getcpAdd";
		
	}
	
	//新增入库信息
	@RequestMapping(params="method=getcpAdd")
	@ResponseBody
	public boolean getcpAdd(GetcpEntity getcp,HttpSession session) {
		Date d = new Date();
		SimpleDateFormat ff = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String time = ff.format(d);
		String uname=(String) session.getAttribute("myuser");
		UserinfoEntity userinfo = userinfoService.getOne(uname);
		getcp.setUid(userinfo.getUid());
		getcp.setGtime(time);
		boolean count = getcpService.save(getcp);
		return count;
	}
	
	//分页查询出库信息
	@RequestMapping(params="method=getcpOne")
	@ResponseBody
	public JSONObject getcpOne(int fid) {
		int count = getcpService.getcount(fid);
		List<OutcpEntity> cpOne = getcpService.getcpOne(fid);
		JSONArray arr = new JSONArray();
		for (OutcpEntity o : cpOne) {
			JSONObject s = new JSONObject();
			s.put("tid", o.getTid());
			s.put("rcard", o.getRcard());
			s.put("rname", o.getRname());
			s.put("cname", o.getCname());
			s.put("fname", o.getFname());
			s.put("fdw", o.getTcount()+o.getFdw());
			s.put("ttime", o.getTtime());
			s.put("uname", o.getUname());
			arr.add(s);
		}
		
		JSONObject obj = new JSONObject();
		obj.put("rows", arr);
		obj.put("total", count);
		return obj;
	}

}

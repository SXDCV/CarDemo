package com.hu.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hu.entity.MemberEntity;
import com.hu.service.MemberService;
import com.hu.util.Mytwo;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


@Controller
@RequestMapping("graph.do")
public class GraphController {
	
	@Autowired
	private MemberService memberservice;
	
	
	//加载页
	@RequestMapping(params = "method=toall")
	public String toall(Model model) {
		List<MemberEntity> ar = memberservice.getchart();
		model.addAttribute("ar", ar);
		return "graph/graphAll";
	}
	

	//按会员等级查询
	@RequestMapping(params = "method=djall")
	@ResponseBody
	public JSONObject djall(int pages, int begin,int did) {
		
		MemberEntity m = new MemberEntity();
		m.setPages(pages);
		m.setBegin(begin);
		m.setDid(did);
		
		
		
		int count = memberservice.djcount(m);
		List<MemberEntity> memberlist = memberservice.djall(m);
		for (MemberEntity e : memberlist) {
			e.setMoney(Mytwo.two(e.getRmoney()));
			e.setJf(Mytwo.two(e.getRjf()));
		}
		JSONArray arr = new JSONArray();
		for (MemberEntity e : memberlist) {
			if (e.getRsex() == 1) {
				e.setSex("男");
			}else {
				e.setSex("女");
			}
			if(e.getRstatus()==1) {
				e.setFlag("正常");
			}else {
				e.setFlag("失效");
			}
			JSONObject s = new JSONObject();
			s.put("rid", e.getRid());
			s.put("rcard", e.getRcard());
			s.put("rname", e.getRname());
			s.put("raddress", e.getRaddress());
			s.put("dname", e.getDname());
			s.put("sex", e.getSex());
			s.put("rmoney", e.getMoney());
			s.put("rjf", e.getJf());
			arr.add(s);
		}
		JSONObject obj = new JSONObject();
		obj.put("rows", arr);
		obj.put("total", count);		
		return obj;
	}
	
	
	
	@RequestMapping(params = "method=chart")
	@ResponseBody
	public List<MemberEntity> chart(){
		List<MemberEntity> memberlist = memberservice.getchart();
		return memberlist;
	}

}

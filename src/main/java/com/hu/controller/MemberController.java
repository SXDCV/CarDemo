package com.hu.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hu.entity.CarxlEntity;
import com.hu.entity.MemberEntity;
import com.hu.service.CarxlService;
import com.hu.service.MemberService;
import com.hu.util.MyUpload;
import com.hu.util.Mytwo;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("member.do")
public class MemberController {
	
	@Value("${prop.upload-folder}")
    private String myupload;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private CarxlService carxlService;
	

	//跳转会员信息
	@RequestMapping(params="method=toall")
	public String toall() {
		return "member/memberAll";
	}
	
	//分页查询
	@RequestMapping(params="method=getByPages")
	@ResponseBody
	public JSONObject getByPages(MemberEntity member) {
		int count = memberService.count();
		List<MemberEntity> memberlist = memberService.getByPages(member);
		for (MemberEntity m : memberlist) {
			m.setMoney(Mytwo.two(m.getRmoney()));
			m.setJf(Mytwo.two(m.getRjf()));
		}
		JSONArray arr = new JSONArray();
		for (MemberEntity m : memberlist) {
			if (m.getRsex() == 1) {
				m.setSex("男");
			}else {
				m.setSex("女");
			}
			if(m.getRstatus()==1) {
				m.setFlag("正常");
			}else {
				m.setFlag("失效");
			}
			JSONObject s = new JSONObject();
			s.put("rid", m.getRid());
			s.put("rcard", m.getRcard());
			s.put("rname", m.getRname());
			s.put("rimg", m.getRimg());
			s.put("rtel", m.getRtel());
			s.put("raddress", m.getRaddress());
			s.put("dname", m.getDname());
			s.put("sex", m.getSex());
			s.put("flag", m.getFlag());
			s.put("rmoney", m.getMoney());
			s.put("rjf", m.getJf());
			s.put("aname", m.getAname());
			s.put("xname", m.getXname());
			arr.add(s);
		}
		
		JSONObject obj = new JSONObject();
		obj.put("rows", arr);
		obj.put("total", count);
		return obj;
	}
	
	//跳转新增页面
	@RequestMapping(params="method=toadd")
	public String toadd() {
		return "member/memberAdd";
	}
	
	//查询是否重名
	@RequestMapping(params="method=getCkName")
	@ResponseBody
	public int getCkName(MemberEntity member) {
		
		int count = memberService.getCkName(member);
		return count;
	}
	
	//新增会员信息
	@RequestMapping(params="method=memberAdd")
	@ResponseBody
	public boolean memberAdd(MemberEntity member) {
		String newname="";
		String oldname=member.getXimg().getOriginalFilename();
		if (oldname.length()==0) {
			member.setRimg("a.jpg");
		}else {
			
			try {
				newname=MyUpload.getNewName(oldname);
				Path path=Paths.get(myupload+newname);
				Files.write(path, member.getXimg().getBytes());
				
				member.setRimg(newname);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		member.setRstatus(1);
		boolean count = memberService.save(member);
		return count;
	}
	
	
	//查询单个会员信息
	@RequestMapping(params="method=memberOne")
	public String memberOne(int rid,Model model) {
		MemberEntity member = memberService.getById(rid);
		CarxlEntity carxl = carxlService.getById(member.getXid());
		
			member.setAid(carxl.getAid());
		model.addAttribute("member", member);
		return "member/memberUpd";
	}
	
	//修改单个会员信息
	@RequestMapping(params="method=memberUpd")
	@ResponseBody
	public boolean memberUpd(MemberEntity member) {
		
		String oldname=member.getXimg().getOriginalFilename();
		MemberEntity delmember=memberService.getById(member.getRid());
		String rimg=delmember.getRimg();
		if (oldname.length()>0) {
			if (member.getXimg().getOriginalFilename()!=oldname) {
				String delpath=myupload+rimg;
				File f=new File(delpath);
				f.delete();
			}
			
			String newmame=MyUpload.getNewName(oldname);
			MyUpload.upImg(myupload, newmame, member.getXimg());
			member.setRimg(newmame);
		}else {
			member.setRimg(rimg);
		}
		member.setRstatus(1);
		boolean count = memberService.updateById(member);
		return count;
	}
	
	//修改余额
	@RequestMapping(params="method=updMoney")
	@ResponseBody
	public int updMoney(MemberEntity member) {
		int count = memberService.updMoney(member);
		return count;
	}
	

	
	
	//查询单个会员信息
	@RequestMapping(params="method=getOne")
	public String getOne(int rid,Model model) {
		MemberEntity member = memberService.getOne(rid);
		if (member.getRsex()==1) {
			member.setSex("男");
		}else {
			member.setSex("女");
		}
		model.addAttribute("member", member);
		return "member/memberLook";
	}
	
	//根据卡号获取信息
	@RequestMapping(params="method=getAll")
	@ResponseBody
	public MemberEntity getAll(MemberEntity member) {
		MemberEntity member1 = memberService.getAll(member);
		return member1;
		
		
	}
	

}

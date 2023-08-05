package com.hu.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import com.hu.entity.ChongEntity;
import com.hu.entity.MemberEntity;
import com.hu.entity.UserinfoEntity;
import com.hu.service.ChongService;
import com.hu.service.MemberService;
import com.hu.service.UserinfoService;
import com.hu.util.Mytwo;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("chong.do")
public class ChongController {
	
	@Autowired
	private ChongService chongService;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private UserinfoService userinfoService;
	
	//跳转会员充值
	@RequestMapping(params="method=toall")
	public String toall() {
		return "chong/chongAll";
	}
		
	//分页查询
	@RequestMapping(params="method=getByPages")
	@ResponseBody
	public JSONObject getByPages(ChongEntity chong) {
		int count = chongService.count();
		List<ChongEntity> chonglist = chongService.getByPages(chong);
		for (ChongEntity c : chonglist) {
			c.setMoney(Mytwo.two(c.getOmoney()));
			c.setSmoney(Mytwo.two(c.getOsmoney()));
			c.setLastmoney(Mytwo.two(c.getOlastmoney()));
		}
		
		JSONObject obj = new JSONObject();
		obj.put("rows", chonglist);
		obj.put("total", count);
		return obj;
	}
	
	//跳转新增页面
	@RequestMapping(params="method=toadd")
	public String toadd() {
		return "chong/chongAdd";
	}
	
	
	//查询会员
	@RequestMapping(params="method=getOne")
	@ResponseBody
	public MemberEntity getOne(MemberEntity member){
		try {
			MemberEntity m = memberService.getMemberOne(member);
			if(m.getRstatus()==1) {
				m.setFlag("正常");
			}else {
				m.setFlag("失效");
			}
			if(m.getRsex()==1) {
				m.setSex("男");
			}else {
				m.setSex("女");	
			}
			return m;
			
		} catch (Exception e) {
			MemberEntity m = new MemberEntity();
			m.setRid(-1);
			return m;
		}	
	}
	
	//充值金额
	@RequestMapping(params="method=chongAdd")
	@ResponseBody
	public boolean chongAdd(ChongEntity chong,HttpSession session) {
		Date d = new Date();
		SimpleDateFormat ff = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String time = ff.format(d);
		String uname=(String) session.getAttribute("myuser");
		UserinfoEntity userinfo = userinfoService.getOne(uname);
		chong.setUid(userinfo.getUid());
		chong.setOtime(time);
		boolean count = chongService.save(chong);
		return count;
	}

}

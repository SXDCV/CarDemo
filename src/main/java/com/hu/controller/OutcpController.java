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

import com.hu.entity.MemberEntity;
import com.hu.entity.MytfEntity;
import com.hu.entity.OutcpEntity;
import com.hu.entity.UserinfoEntity;
import com.hu.service.MemberService;
import com.hu.service.MytfService;
import com.hu.service.OutcpService;
import com.hu.service.UserinfoService;
import com.hu.util.Mytwo;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("outcp.do")
public class OutcpController {
	
	@Autowired
	private OutcpService outcpService;
	
	@Autowired
	private UserinfoService userinfoService;
	
	@Autowired
	private MytfService mytfservice;
	
	@Autowired
	private MemberService memberservice;
	
	
	//跳转查询页面
	@RequestMapping(params="method=toall")
	public String toall() {
		return "outcp/outcpAll";
	}
	
	//分页查询
	@RequestMapping(params="method=getByPages")
	@ResponseBody
	public JSONObject getByPages(OutcpEntity outcp) {
		int count = outcpService.count();
		List<OutcpEntity> outcplist = outcpService.getByPages(outcp);
		
		for (OutcpEntity o : outcplist) {
			o.setFdw(o.getTcount()+o.getFdw());
			o.setTzk(o.getDzk() * o.getFoutprice());
			o.setTxj(o.getTzk()*o.getTcount());
			o.setOutprice(Mytwo.two(o.getFoutprice()));
			o.setZk(Mytwo.two(o.getTzk()));
			o.setXj(Mytwo.two(o.getTxj()));
		}
		
		JSONObject obj = new JSONObject();
		obj.put("rows", outcplist);
		obj.put("total", count);
		return obj;
	}
	
	//跳转消费页面
	@RequestMapping(params="method=toshop")
	public String toshop() {
		return "outcp/outcpShop";
	}
	
	//条件查询
	@RequestMapping(params="method=getOutcp")
	@ResponseBody
	public JSONObject getOutcp(OutcpEntity outcp){
		int count = outcpService.count(outcp.getRid());
		List<OutcpEntity> outcplist = outcpService.getOutcp(outcp);
		for (OutcpEntity o : outcplist) {
			o.setFdw(o.getTcount()+o.getFdw());
			o.setTzk(o.getDzk() * o.getFoutprice());
			o.setTxj(o.getTzk()*o.getTcount());
		}
		
		JSONObject obj = new JSONObject();
		obj.put("rows", outcplist);
		obj.put("total", count);
		return obj;
	}
	
	//跳转购物页面
	@RequestMapping(params="method=toadd")
	public String toadd(int rid,Model model) {
		model.addAttribute("rid",rid);
		return "outcp/outcpAdd";
	}
	
	//新增购物信息
	@RequestMapping(params="method=outcpAdd")
	@ResponseBody
	public boolean outcpAdd(OutcpEntity outcp,HttpSession session) {
		Date d = new Date();
		SimpleDateFormat ff = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String time = ff.format(d);
		String uname=(String) session.getAttribute("myuser");
		UserinfoEntity userinfo = userinfoService.getOne(uname);
		outcp.setUid(userinfo.getUid());
		outcp.setTtime(time);
		boolean count = outcpService.save(outcp);
		return count;
	}
	
	
	//跳转结账页面
	@RequestMapping(params="method=toexit")
	public String toexit(OutcpEntity outcp,Model model) {
		model.addAttribute("rid", outcp.getRid());
		double handlemoney=0;
		double txjmoney=0;
		List<OutcpEntity> outcplist = outcpService.getOutcp(outcp);
		for (OutcpEntity o : outcplist) {
			 model.addAttribute("dname", o.getDname());
			 model.addAttribute("dzk", o.getDzk());
			 model.addAttribute("rmoney", o.getRmoney());
			 o.setTzk(o.getDzk() * o.getFoutprice());
			 handlemoney+=o.getTcount()*o.getFoutprice();
				txjmoney+=o.getTzk()*o.getTcount();
			o.setHandle(handlemoney);
			o.setTxj(txjmoney);
		}
		
		model.addAttribute("handle",handlemoney);
		model.addAttribute("txj", txjmoney);
		return "outcp/outcpExit";
	}
	
	//分页查询
	@RequestMapping(params="method=getBylPages")
	@ResponseBody
	public JSONObject getBylPages(OutcpEntity outcp) {
		int count = outcpService.count(outcp.getRid());
		List<OutcpEntity> outcplist = outcpService.getBylPages(outcp);
		
		for (OutcpEntity o : outcplist) {
			o.setFdw(o.getTcount()+o.getFdw());
			o.setTzk(o.getDzk() * o.getFoutprice());
			o.setTxj(o.getTzk()*o.getTcount());
		}
		
		JSONObject obj = new JSONObject();
		obj.put("rows", outcplist);
		obj.put("total", count);
		return obj;
	}
	
	//单个查询购物信息
	@RequestMapping(params="method=outcpOne")
	public String outcpOne(int tid,Model model) {
		OutcpEntity outcp = outcpService.outcpOne(tid);
		model.addAttribute("outcp", outcp);
		return "outcp/outcpUpd";
	}
	
	//修改购买数量
	@RequestMapping(params="method=outcpUpd")
	@ResponseBody
	public int outcpUpd(OutcpEntity outcp) {
		int count = outcpService.outcpUpd(outcp);
		return count;
		
	}
	
	//查询重复购买项
	@RequestMapping(params="method=getCkName")
	@ResponseBody
	public int getCkName(OutcpEntity outcp) {
		int count = outcpService.getCkName(outcp);
		return count;
	}
	
	//获取Tid
	@RequestMapping(params="method=gettid")
	@ResponseBody
	public OutcpEntity gettid(OutcpEntity outcp) {
		OutcpEntity outcpOne = outcpService.getTid(outcp);
		return outcpOne;
	}
	
	//增加购买数量
	@RequestMapping(params="method=addOutcp")
	@ResponseBody
	public int addOutcp(OutcpEntity outcp) {
		int count = outcpService.addOutcp(outcp);
		return count;
		
	}
	
	//判断是否购买成功
	@RequestMapping(params="method=toAlltid")
	@ResponseBody
	public JSONObject toAlltid(String alltid,Float txj) {
		int rid=0;
		int tcount=0;
		String x[]=alltid.split("、");
		for (int i = 0; i < x.length; i++){
			int tid=Integer.parseInt(x[i]);
			OutcpEntity out = outcpService.outcpOne(tid);
			rid=out.getRid();
			tcount=out.getTcount();
			if (out.getTcount()>out.getFcount()) {
				
				JSONObject obj = new JSONObject();
				String name=out.getFname()+"库存不足";
				System.out.println(name);
				obj.put("rows", name);
				return obj;
			}
		}
		for (int i = 0; i < x.length; i++) {
			int tid=Integer.parseInt(x[i]);
			outcpService.updFlag(tid);
			OutcpEntity ou = outcpService.getById(tid);
			
			//修改库存
			MytfEntity mytf=new MytfEntity();
			mytf.setFid(ou.getFid());
			mytf.setFcount(tcount);
			mytfservice.FcountUpd(mytf);
		}
		//修改余额
		MemberEntity member=new MemberEntity();
		member.setRmoney((float) txj);
		member.setRid(rid);
		memberservice.MoneyUpd(member);
		
		
		if (txj>=0&&txj<100) {
			member.setRjf(txj*0);
		}else if (txj>=100&&txj<500) {
			member.setRjf(txj*0.01);
		}else if (txj>=500&&txj<1000) {
			member.setRjf(txj*0.05);
		}else if (txj>=1000&&txj<1500) {
			member.setRjf(txj*0.1);
		}else if (txj>=1500) {
			member.setRjf(txj*0.5);
		}
		memberservice.UpdJf(member);
		
		JSONObject obj = new JSONObject();
		
		obj.put("rows","1");
		return obj;
	}
}

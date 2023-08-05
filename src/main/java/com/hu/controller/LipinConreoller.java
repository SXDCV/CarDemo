package com.hu.controller;


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

import com.hu.entity.DuihuanEntity;
import com.hu.entity.LipinEntity;
import com.hu.service.DuihuanService;
import com.hu.service.LipinService;
import com.hu.util.MyUpload;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("lipin.do")
public class LipinConreoller {
	
	@Value("${prop.upload-folder}")
    private String myupload;
	
	@Autowired
	private LipinService lipinService;
	
	@Autowired
	private DuihuanService duihuanservice;
	
	//跳转会员信息
	@RequestMapping(params="method=toall")
	public String toall() {
		return "lipin/lipinAll";
	}
	
	//分页查询
	@RequestMapping(params="method=getByPages")
	@ResponseBody
	public JSONObject getByPages(LipinEntity lipin) {
		int count = lipinService.count();
		List<LipinEntity> lipinlist = lipinService.getByPages(lipin);
		JSONArray arr = new JSONArray();
		for (LipinEntity m : lipinlist) {
			
			JSONObject s = new JSONObject();
			s.put("nid", m.getNid());
			s.put("nname", m.getNname());
			s.put("nimg", m.getNimg());
			s.put("njf", m.getNjf());
			s.put("ncount", m.getNcount());
			s.put("nncount", m.getNncount());
			arr.add(s);
			}
		
		JSONObject obj = new JSONObject();
		obj.put("rows", arr);
		obj.put("total", count);
		return obj;
	}
	
	//查询所有
	@RequestMapping(params="method=lipinAll")
	@ResponseBody
	public List<LipinEntity> lipinAll() {
		List<LipinEntity> lipinlist = lipinService.list();
		return lipinlist;
	}
	
	//查询单个
	@RequestMapping(params="method=lipinOne")
	@ResponseBody
	public LipinEntity lipinOne(LipinEntity lipin) {
		LipinEntity lipinOne = lipinService.lipinOne(lipin);
		return lipinOne;
	}
	
	//跳转新增页面
	@RequestMapping(params="method=toadd")
	public String toadd() {
		return "lipin/lipinAdd";
	}
	
	//查询是否重名
	@RequestMapping(params="method=getCkName")
	@ResponseBody
	public int getCkName(LipinEntity lipin) {
		
		int count = lipinService.getCkName(lipin);
		return count;
	}
	
	//新增礼品信息
	@RequestMapping(params="method=lipinAdd")
	@ResponseBody
	public boolean lipinAdd(LipinEntity lipin) {
		String newname="";
		String oldname=lipin.getBimg().getOriginalFilename();
		if (oldname.length()==0) {
			lipin.setNimg("a.jpg");
		}else {
			
			try {
				newname=MyUpload.getNewName(oldname);
				Path path=Paths.get(myupload+newname);
				Files.write(path, lipin.getBimg().getBytes());
				
				lipin.setNimg(newname);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		lipin.setNncount(lipin.getNcount());
		boolean count = lipinService.save(lipin);
		return count;
	}
	
	//跳转兑换记录
	@RequestMapping(params="method=lipinDuihuan")
	public String lipinDuihuan(Model model,int nid) {
		model.addAttribute("nid", nid);
		return "lipin/lipinLook";
	}
	
	//礼品兑换记录
	@RequestMapping(params="method=getAll")
	@ResponseBody
	public JSONObject getAll(DuihuanEntity duihuan){
		int count = duihuanservice.count(duihuan.getNid());
		List<DuihuanEntity> duihuanlist = duihuanservice.getAll(duihuan);
		
		JSONObject obj = new JSONObject();
		obj.put("rows", duihuanlist);
		obj.put("total", count);
		return obj;
		
	}
	
	
	
	
	
	
	

}

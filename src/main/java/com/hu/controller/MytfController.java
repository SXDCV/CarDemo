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

import com.hu.entity.MytfEntity;
import com.hu.service.MytfService;
import com.hu.util.MyUpload;
import com.hu.util.Mytwo;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("mytf.do")
public class MytfController {
	
	@Value("${prop.upload-folder}")
    private String myupload;
	
	@Autowired
	private MytfService mytfService;
	
	//跳转产品类别
	@RequestMapping(params="method=toall")
	public String toall() {
		return "mytf/mytfAll";
	}
		
	//分页查询
	@RequestMapping(params="method=getByPages")
	@ResponseBody
	public JSONObject getByPages(MytfEntity mytf) {
		int count = mytfService.count();
		List<MytfEntity> mytflist = mytfService.getByPages(mytf);
		for (MytfEntity m : mytflist) {
			m.setOutprice(Mytwo.two(m.getFoutprice()));
			m.setInprice(Mytwo.two(m.getFinprice()));
		}
		JSONArray arr=new JSONArray();
		for (MytfEntity m : mytflist) {
			
			JSONObject s=new JSONObject();
			s.put("fid", m.getFid());
			s.put("fname", m.getFname());
			s.put("cname", m.getCname());
			s.put("fimg", m.getFimg());
			s.put("fdw", m.getFcount()+m.getFdw());
			s.put("faddress", m.getFaddress());
			s.put("foutprice", m.getOutprice());
			s.put("finprice", m.getInprice());
			s.put("fcount", m.getFcount());
			arr.add(s);
		}
		
		JSONObject obj = new JSONObject();
		obj.put("rows", arr);
		obj.put("total", count);
		return obj;
	}
	
	//查询所有
	@RequestMapping(params="method=mytfAll")
	@ResponseBody
	public MytfEntity mytfAll(int fid){
		MytfEntity mytflist = mytfService.mytfAll(fid);
		
		return mytflist;
	}
	
	//跳转新增页面
	@RequestMapping(params="method=toadd")
	public String toadd() {
		return "mytf/mytfAdd";
	}
	
	//查询是否重名
	@RequestMapping(params="method=getCkName")
	@ResponseBody
	public int getCkName(MytfEntity mytf) {
		int count = mytfService.getCkName(mytf);
		return count;
	}
	
	//新增库存信息
	@RequestMapping(params="method=mytfAdd")
	@ResponseBody
	public boolean mytfAdd(MytfEntity mytf) {
		String newname="";
		String oldname=mytf.getCimg().getOriginalFilename();
		if (oldname.length()==0) {
			mytf.setFimg("a.jpg");
		}else {
			
			try {
				newname=MyUpload.getNewName(oldname);
				Path path=Paths.get(myupload+newname);
				Files.write(path, mytf.getCimg().getBytes());
				mytf.setFimg(newname);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		mytf.setFcount(0);
		boolean count = mytfService.save(mytf);
		return count;
	}
	
	
	//查询单个库存信息
	@RequestMapping(params="method=mytfOne")
	public String mytfOne(int fid,Model model) {
		MytfEntity mytf = mytfService.getById(fid);
		model.addAttribute("mytf", mytf);
		return "mytf/mytfUpd";
	}
	
	//修改单个库存信息
	@RequestMapping(params="method=mytfUpd")
	@ResponseBody
	public int mytfUpd(MytfEntity mytf) {
		
		String oldname=mytf.getCimg().getOriginalFilename();
		MytfEntity delmytf=mytfService.getById(mytf.getFid());
		String rimg=delmytf.getFimg();
		if (oldname.length()>0) {
			if (mytf.getCimg().getOriginalFilename()!=oldname) {
				String delpath=myupload+rimg;
				File f=new File(delpath);
				f.delete();
			}
			
			String newmame=MyUpload.getNewName(oldname);
			MyUpload.upImg(myupload, newmame, mytf.getCimg());
			mytf.setFimg(newmame);
		}else {
			mytf.setFimg(rimg);
		}

		int count = mytfService.mytfUpd(mytf);
		return count;
	}
	
	//修改数量
	@RequestMapping(params="method=updFcount")
	@ResponseBody
	public int updFcount(MytfEntity mytf) {
		int count = mytfService.updFcount(mytf);
		return count;
	}
	
	
	//根据类型查询库存信息
	@RequestMapping(params="method=mytfFname")
	@ResponseBody
	public List<MytfEntity> mytfFname(int cid) {
		
		List<MytfEntity> mytflist = mytfService.mytfOne(cid);
		
		
		return mytflist;
	}
	

}

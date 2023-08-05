package com.hu.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hu.entity.CartypeEntity;
import com.hu.entity.CarxlEntity;
import com.hu.service.CartypeService;
import com.hu.service.CarxlService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("carxl.do")
public class CarxlController {
	
	@Autowired
	private CarxlService carxlService;
	
	@Autowired
	private CartypeService cartypeService;
	
	//跳转查询页面
	@RequestMapping(params="method=toall")
	public String toall() {
		return "carxl/carxlAll";
	}
	
	//分页查询
	@RequestMapping(params="method=getByPages")
	@ResponseBody
	public JSONObject getByPages(CarxlEntity carxl) {
		int count = carxlService.count();
		List<CarxlEntity> carxlList = carxlService.getByPages(carxl);
		
		JSONObject obj = new JSONObject();
		obj.put("rows", carxlList);
		obj.put("total", count);
		return obj;
	}
	
	//跳转查询页面
	@RequestMapping(params="method=toadd")
	public String toadd() {
		
		return "carxl/carxlAdd";
	}
	
	//查询是否重名
	@RequestMapping(params="method=getCkName")
	@ResponseBody
	public int getCkName(CarxlEntity carxl) {
		
		int count = carxlService.getCkName(carxl);
		
		return count;
	}

	
	//新增汽车系列
	@RequestMapping(params="method=carxlAdd")
	@ResponseBody
	public boolean carxlAdd(CarxlEntity carxl) {
		boolean count = carxlService.save(carxl);
		return count;
	}
	
	//查询单个
	@RequestMapping(params="method=getOne")
	@ResponseBody
	public List<CarxlEntity> getOne(CarxlEntity carxl) {
		List<CarxlEntity> carxllist = carxlService.getOne(carxl);
		return carxllist;
	}
	
	//查询单个汽车系列
	@RequestMapping(params="method=carxlOne")
	public String carxlOne(int xid,Model model) {
		CarxlEntity carxl = carxlService.carxlOne(xid);
		int aid=carxl.getAid();
		List<CartypeEntity> cartypelist = cartypeService.list();
		List<CartypeEntity> cartype=new ArrayList<CartypeEntity>();
		for (CartypeEntity c : cartypelist) {
			if (c.getAid()==aid) {
				cartype.add(0,c);
			}else {
				cartype.add(c);
			}
			model.addAttribute("carxl", carxl);
			model.addAttribute("cartype", cartype);
			
		}
		return "carxl/carxlUpd";
	}
	
	//修改名称
	@RequestMapping(params="method=updName")
	@ResponseBody
	public int updName(CarxlEntity carxl) {
		int count = carxlService.updName(carxl);
		return count;
	}
	
	//修改汽车品牌
	@RequestMapping(params="method=updAName")
	@ResponseBody
	public int updAName(CarxlEntity carxl) {
		int count = carxlService.updAName(carxl);
		return count;
	}
	
	//修改汽车系列
	@RequestMapping(params="method=carxlUpd")
	@ResponseBody
	public boolean carxlUpd(CarxlEntity carxl) {
		boolean count = carxlService.updateById(carxl);
		return count;
		
	}

}

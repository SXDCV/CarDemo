package com.hu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hu.entity.CartypeEntity;
import com.hu.service.CartypeService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("cartype.do")
public class CartypeController {
	
	@Autowired
	private CartypeService cartypeservice;
	
	//跳转产品类别
	@RequestMapping(params="method=toall")
	public String toall() {
		return "cartype/cartypeAll";
	}
	
	//分页查询
	@RequestMapping(params="method=getByPages")
	@ResponseBody
	public JSONObject getByPages(CartypeEntity cartype) {
		int count = cartypeservice.count();
		List<CartypeEntity> cartypelist = cartypeservice.getByPages(cartype);
		
		JSONObject obj = new JSONObject();
		obj.put("rows", cartypelist);
		obj.put("total", count);
		return obj;
	}
	
	//全查询
	@RequestMapping(params="method=cartypeAll")
	@ResponseBody
	public List<CartypeEntity> cartypeAll(){
		List<CartypeEntity> cartypelist = cartypeservice.list();
		return cartypelist;
	}
	
	//跳转新增页面
	@RequestMapping(params="method=toadd")
	public String toadd() {
		return "cartype/cartypeAdd";
	}
	
	//查询是否重名
	@RequestMapping(params="method=getCkName")
	@ResponseBody
	public int getCkName(CartypeEntity cartype) {
		int count = cartypeservice.getCkName(cartype);
		return count;
	}
	
	//新增产品类别
	@RequestMapping(params="method=cartypeAdd")
	@ResponseBody
	public boolean cartypeAdd(CartypeEntity cartype) {
		boolean count = cartypeservice.save(cartype);
		return count;
	}
	
	
	//查询单个产品类别
	@RequestMapping(params="method=cartypeOne")
	public String cartypeOne(int aid,Model model) {
		CartypeEntity cartype = cartypeservice.getById(aid);
		model.addAttribute("cartype", cartype);
		return "cartype/cartypeUpd";
	}
	
	//修改单个产品类别
	@RequestMapping(params="method=cartypeUpd")
	@ResponseBody
	public boolean cartypeUpd(CartypeEntity cartype) {

		boolean count = cartypeservice.updateById(cartype);
		return count;
	}
	
	//修改产品名称
	@RequestMapping(params="method=updName")
	@ResponseBody
	public int updName(CartypeEntity cartype) {
		int count = cartypeservice.updName(cartype);
		return count;
	}
	
	

}

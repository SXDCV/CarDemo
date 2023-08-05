package com.hu.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hu.entity.UserinfoEntity;
import com.hu.service.UserinfoService;

@Controller
@RequestMapping("user.do")
public class UserinfoController {
	
	@Autowired
	private UserinfoService userinfoService;
	
	//跳转登录页面
	@RequestMapping(params = "method=index")
	public String index(){
		return "index";
	}
	
	@RequestMapping(params = "method=tomain")
	public String tomain(Model model,HttpServletRequest request){
		HttpSession session =request.getSession();
		model.addAttribute("uname", session.getAttribute("myuser"));
		
		return "main/main";
	}
	
	@RequestMapping(params = "method=main")
	public String main(Model model,HttpServletRequest request){
		HttpSession session =request.getSession();
		model.addAttribute("uname", session.getAttribute("myuser"));
		return "main/home";
	}
	
	//判断账号是否存在
	@RequestMapping(params="method=getName")
	@ResponseBody
	public int getName(UserinfoEntity user) {
		
		
		int count = userinfoService.getName(user);
		
		return count;
		
	}
	
	//判断是否登录
	@RequestMapping(params="method=login")
	@ResponseBody
	public int login(UserinfoEntity  user,HttpServletRequest request) {
		int result=0;
		int count = userinfoService.login(user);
		
		 if (count>0) {
			// 把这个登陆者的信息查询出来 --session中
			HttpSession session = request.getSession();
			session.setAttribute("myuser", user.getUname());
			result=count;
		}
			return result;
		
	}
	
	//
	

}

package com.hu.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class MyInterceptor extends HandlerInterceptorAdapter
{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
	{
		// 得到Session
		HttpSession session = request.getSession();
		Object obj = null;
		obj = session.getAttribute("myuser");
		if(obj==null)
		{
			response.sendRedirect("/user.do?method=index");
			return false;
		}
		else
		{
		    //放到
			return true;
		}
		
		
	}
}

package com.hu.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WebAppConfig extends WebMvcConfigurationSupport
{
	@Autowired
	private MyInterceptor myInterceptor;

	@Override
	protected void addInterceptors(InterceptorRegistry registry)
	{
		// 要放行的内容
		String xx[] = new String[]{ "/", "/user.do" };
		registry.addInterceptor(myInterceptor).addPathPatterns("/**").excludePathPatterns(xx);
		super.addInterceptors(registry);
	}

	// 释放资源
	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry)
	{
		
		/*registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
		// 放行动态内容
		registry.addResourceHandler("/Path/**").addResourceLocations("file:/c:/upload/");*/
		// 放行静态内容
		registry.addResourceHandler("/**").addResourceLocations("classpath:/static/").addResourceLocations("file:/D:/upload/");
		registry.addResourceHandler("/Path/**").addResourceLocations("/**");
		super.addResourceHandlers(registry);
	}

}

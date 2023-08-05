package com.hu.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;


public class MyUpload{
	/********************* 改名  **********************/
	public static String getNewName(String oldname){

		String newname = "";
		// 第一小步：得到原文件名的后缀（.txt .jpg .png……）
		String lastname = oldname.substring(oldname.lastIndexOf("."));
		// 第二小步：得到一个日期时间对象
		Date d = new Date();
		SimpleDateFormat ff = new SimpleDateFormat("yyyyMMddHHmmss");
		String time = ff.format(d);
		// 第三小步：得到一个随机数
		Random rad = new Random();
		int num = rad.nextInt(9999999);
		// 第四小步：拼扪一个新名
		newname = time + num + lastname;
		return newname;
	}
	/********************上传*********************/
	public static void upImg(String myupload,String newmame,MultipartFile in){
		try
		{
			File f = new File(myupload + newmame);
			FileUtils.copyInputStreamToFile(in.getInputStream(), f);
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}

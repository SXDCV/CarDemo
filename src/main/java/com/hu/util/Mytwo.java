package com.hu.util;

public class Mytwo {
	
	public static String two(double number) {
		String strnum =String.valueOf(number);
		int ch=strnum.indexOf(".");
		if ((strnum.length()-ch)==2) {
			strnum=strnum+"0";
		}
		if ((strnum.length()-ch)==1) {
			strnum=strnum+"00";
		}
		return strnum;
	}

}

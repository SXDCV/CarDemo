package com.hu.util;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;

@SuppressWarnings("serial")
public class MyPageVO implements Serializable {

		@TableField(exist = false)
		private int begin;
		@TableField(exist = false)
		private int pages;
		
		public int getBegin() {
			return begin;
		}
		public void setBegin(int begin) {
			this.begin = begin;
		}
		public int getPages() {
			return pages;
		}
		public void setPages(int pages) {
			this.pages = pages;
		}
		
		
		
		

}

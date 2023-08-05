package com.hu.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hu.util.MyPageVO;
@TableName("carxl")
@SuppressWarnings("serial")
public class CarxlEntity extends MyPageVO implements Serializable {
	@TableId(type=IdType.AUTO)
	private int xid;
	@TableField("aid")
	private int aid;
	@TableField("xname")
	private String xname;
	
	@TableField(exist = false)
	private String aname;
	
	
	public int getXid() {
		return xid;
	}
	public void setXid(int xid) {
		this.xid = xid;
	}
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	public String getXname() {
		return xname;
	}
	public void setXname(String xname) {
		this.xname = xname;
	}
	public String getAname() {
		return aname;
	}
	public void setAname(String aname) {
		this.aname = aname;
	}
	
	

}

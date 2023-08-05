package com.hu.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hu.util.MyPageVO;
@TableName("duihuan")
@SuppressWarnings("serial")
public class DuihuanEntity extends MyPageVO implements Serializable {
	@TableId(type=IdType.AUTO)
	private int hid;
	
	@TableField("nid")
	private int nid;
	
	@TableField("rid")
	private int rid;
	
	@TableField("hcount")
	private int hcount;
	
	@TableField("htime")
	private String htime;
	
	@TableField("uid")
	private int uid;
	
	@TableField(exist = false)
	private String nname;
	@TableField(exist = false)
	private String rcard;
	@TableField(exist = false)
	private String rname;
	@TableField(exist = false)
	private String uname;
	@TableField(exist = false)
	private float sjf;
	
	public int getHid() {
		return hid;
	}
	public void setHid(int hid) {
		this.hid = hid;
	}
	public int getNid() {
		return nid;
	}
	public void setNid(int nid) {
		this.nid = nid;
	}
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	public int getHcount() {
		return hcount;
	}
	public void setHcount(int hcount) {
		this.hcount = hcount;
	}
	public String getHtime() {
		return htime;
	}
	public void setHtime(String htime) {
		this.htime = htime;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getNname() {
		return nname;
	}
	public void setNname(String nname) {
		this.nname = nname;
	}
	public String getRname() {
		return rname;
	}
	public void setRname(String rname) {
		this.rname = rname;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getRcard() {
		return rcard;
	}
	public void setRcard(String rcard) {
		this.rcard = rcard;
	}
	public float getSjf() {
		return sjf;
	}
	public void setSjf(float sjf) {
		this.sjf = sjf;
	}
	

}

package com.hu.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hu.util.MyPageVO;
@TableName("look")
@SuppressWarnings("serial")
public class LookEntity extends MyPageVO implements Serializable {
	@TableId(type=IdType.AUTO)
	private int kid;
	
	@TableField("rid")
	private int rid;
	
	@TableField("ktime")
	private String ktime;
	
	@TableField("kremark")
	private String kremark;
	
	@TableField("uid")
	private int uid;
	
	@TableField(exist = false)
	private String rname;
	@TableField(exist = false)
	private String rtel;
	@TableField(exist = false)
	private String rcard;
	@TableField(exist = false)
	private String uname;
	
	public int getKid() {
		return kid;
	}
	public void setKid(int kid) {
		this.kid = kid;
	}
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	public String getKtime() {
		return ktime;
	}
	public void setKtime(String ktime) {
		this.ktime = ktime;
	}
	public String getKremark() {
		return kremark;
	}
	public void setKremark(String kremark) {
		this.kremark = kremark;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
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
	public String getRtel() {
		return rtel;
	}
	public void setRtel(String rtel) {
		this.rtel = rtel;
	}
	public String getRcard() {
		return rcard;
	}
	public void setRcard(String rcard) {
		this.rcard = rcard;
	}
	
	
	

}

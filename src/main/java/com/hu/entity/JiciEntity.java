package com.hu.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hu.util.MyPageVO;
@TableName("jici")
@SuppressWarnings("serial")
public class JiciEntity extends MyPageVO implements Serializable {
	@TableId(type=IdType.AUTO)
	private int jid;
	
	@TableField("rid")
	private int rid;
	
	@TableField("jtime")
	private String jtime;
	
	@TableField("sid")
	private int sid;
	
	@TableField("jmoney")
	private float jmoney;
	
	@TableField("uid")
	private int uid;
	
	@TableField(exist = false)
	private String rcard;
	@TableField(exist = false)
	private String rname;
	@TableField(exist = false)
	private String sname;
	@TableField(exist = false)
	private String uname;
	
	@TableField(exist = false)
	private String money;
	
	public int getJid() {
		return jid;
	}
	public void setJid(int jid) {
		this.jid = jid;
	}
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	public String getJtime() {
		return jtime;
	}
	public void setJtime(String jtime) {
		this.jtime = jtime;
	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public float getJmoney() {
		return jmoney;
	}
	public void setJmoney(float jmoney) {
		this.jmoney = jmoney;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getRcard() {
		return rcard;
	}
	public void setRcard(String rcard) {
		this.rcard = rcard;
	}
	public String getRname() {
		return rname;
	}
	public void setRname(String rname) {
		this.rname = rname;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	
	
}

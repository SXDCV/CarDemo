package com.hu.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hu.util.MyPageVO;
@TableName("chong")
@SuppressWarnings("serial")
public class ChongEntity extends MyPageVO implements Serializable {
	@TableId(type=IdType.AUTO)
	private int oid;
	@TableField("rid")
	private int rid;
	
	@TableField("omoney")
	private float omoney;
	
	@TableField("yid")
	private int yid;
	
	@TableField("osmoney")
	private float osmoney;
	
	@TableField("olastmoney")
	private float olastmoney;
	
	@TableField("uid")
	private int uid;
	
	@TableField("oremark")
	private String oremark;
	
	@TableField("otime")
	private String otime;
	
	@TableField(exist = false)
	private String rname;
	@TableField(exist = false)
	private String rcard;
	@TableField(exist = false)
	private String ytitle;
	@TableField(exist = false)
	private String uname;
	
	@TableField(exist = false)
	private String money;
	@TableField(exist = false)
	private String smoney;
	@TableField(exist = false)
	private String lastmoney;
	
	public int getOid() {
		return oid;
	}
	public void setOid(int oid) {
		this.oid = oid;
	}
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	public float getOmoney() {
		return omoney;
	}
	public void setOmoney(float omoney) {
		this.omoney = omoney;
	}
	public int getYid() {
		return yid;
	}
	public void setYid(int yid) {
		this.yid = yid;
	}
	public float getOsmoney() {
		return osmoney;
	}
	public void setOsmoney(float osmoney) {
		this.osmoney = osmoney;
	}
	public float getOlastmoney() {
		return olastmoney;
	}
	public void setOlastmoney(float olastmoney) {
		this.olastmoney = olastmoney;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getOremark() {
		return oremark;
	}
	public void setOremark(String oremark) {
		this.oremark = oremark;
	}
	public String getOtime() {
		return otime;
	}
	public void setOtime(String otime) {
		this.otime = otime;
	}
	public String getRname() {
		return rname;
	}
	public void setRname(String rname) {
		this.rname = rname;
	}
	
	public String getRcard() {
		return rcard;
	}
	public void setRcard(String rcard) {
		this.rcard = rcard;
	}
	public String getYtitle() {
		return ytitle;
	}
	public void setYtitle(String ytitle) {
		this.ytitle = ytitle;
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
	public String getSmoney() {
		return smoney;
	}
	public void setSmoney(String smoney) {
		this.smoney = smoney;
	}
	public String getLastmoney() {
		return lastmoney;
	}
	public void setLastmoney(String lastmoney) {
		this.lastmoney = lastmoney;
	}
	
	
	
	

}

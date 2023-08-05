package com.hu.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hu.util.MyPageVO;
@TableName("outcpp")
@SuppressWarnings("serial")
public class OutcppEntity extends MyPageVO implements Serializable {
	@TableId(type=IdType.AUTO)
	private int wid;
	
	@TableField("fid")
	private int fid;
	
	@TableField("wcount")
	private int wcount;
	
	@TableField("wname")
	private String wname;
	
	@TableField("wtel")
	private String wtel;
	
	@TableField("uid")
	private int uid;
	
	@TableField("wtime")
	private String wtime;
	
	@TableField(exist = false)
	private String fname;
	@TableField(exist = false)
	private String uname;
	@TableField(exist = false)
	private int cid;
	@TableField(exist = false)
	private String cname;
	@TableField(exist = false)
	private float foutprice;
	@TableField(exist = false)
	private float txj;
	
	@TableField(exist = false)
	private String outprice;
	@TableField(exist = false)
	private String xj;
	
	public int getWid() {
		return wid;
	}
	public void setWid(int wid) {
		this.wid = wid;
	}
	public int getFid() {
		return fid;
	}
	public void setFid(int fid) {
		this.fid = fid;
	}
	public int getWcount() {
		return wcount;
	}
	public void setWcount(int wcount) {
		this.wcount = wcount;
	}
	public String getWname() {
		return wname;
	}
	public void setWname(String wname) {
		this.wname = wname;
	}
	public String getWtel() {
		return wtel;
	}
	public void setWtel(String wtel) {
		this.wtel = wtel;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getWtime() {
		return wtime;
	}
	public void setWtime(String wtime) {
		this.wtime = wtime;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public float getFoutprice() {
		return foutprice;
	}
	public void setFoutprice(float foutprice) {
		this.foutprice = foutprice;
	}
	public float getTxj() {
		return txj;
	}
	public void setTxj(float txj) {
		this.txj = txj;
	}
	public String getOutprice() {
		return outprice;
	}
	public void setOutprice(String outprice) {
		this.outprice = outprice;
	}
	public String getXj() {
		return xj;
	}
	public void setXj(String xj) {
		this.xj = xj;
	}
	
	
	

}

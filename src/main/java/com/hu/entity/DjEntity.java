package com.hu.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hu.util.MyPageVO;
@TableName("dj")
@SuppressWarnings("serial")
public class DjEntity extends MyPageVO implements Serializable {
	@TableId(type=IdType.AUTO)
	private int did;
	
	@TableField("dname")
	private String dname;
	
	@TableField("djf")
	private int djf;
	
	@TableField("dmoneyBl")
	private double dmoneyBl;
	
	@TableField("dzk")
	private double dzk;
	
	
	@TableField(exist = false)
	private String jf;
	
	@TableField(exist = false)
	private String moneyBl;
	
	@TableField(exist = false)
	private String zk;
	
	public String getJf() {
		return jf;
	}
	public void setJf(String jf) {
		this.jf = jf;
	}
	public String getMoneyBl() {
		return moneyBl;
	}
	public void setMoneyBl(String moneyBl) {
		this.moneyBl = moneyBl;
	}
	public String getZk() {
		return zk;
	}
	public void setZk(String zk) {
		this.zk = zk;
	}
	
	
	public int getDid() {
		return did;
	}
	public void setDid(int did) {
		this.did = did;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public int getDjf() {
		return djf;
	}
	public void setDjf(int djf) {
		this.djf = djf;
	}
	public double getDmoneyBl() {
		return dmoneyBl;
	}
	public void setDmoneyBl(double dmoneyBl) {
		this.dmoneyBl = dmoneyBl;
	}
	public double getDzk() {
		return dzk;
	}
	public void setDzk(double dzk) {
		this.dzk = dzk;
	}
	
	

}

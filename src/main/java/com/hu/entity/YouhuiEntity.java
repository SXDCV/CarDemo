package com.hu.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hu.util.MyPageVO;
@TableName("youhui")
@SuppressWarnings("serial")
public class YouhuiEntity extends MyPageVO implements Serializable {
	@TableId(type=IdType.AUTO)
	private int yid;
	
	@TableField("ytitle")
	private String ytitle;
	
	@TableField("ybegintime")
	private String ybegintime;
	
	@TableField("yendtime")
	private String yendtime;
	
	@TableField("ymoney")
	private float ymoney;
	
	@TableField("ylessmoney")
	private float ylessmoney;
	
	
	@TableField(exist = false)
	private String money;
	
	@TableField(exist = false)
	private String lessmoney;
	
	public int getYid() {
		return yid;
	}
	public void setYid(int yid) {
		this.yid = yid;
	}
	public String getYtitle() {
		return ytitle;
	}
	public void setYtitle(String ytitle) {
		this.ytitle = ytitle;
	}
	public String getYbegintime() {
		return ybegintime;
	}
	public void setYbegintime(String ybegintime) {
		this.ybegintime = ybegintime;
	}
	public String getYendtime() {
		return yendtime;
	}
	public void setYendtime(String yendtime) {
		this.yendtime = yendtime;
	}
	public float getYmoney() {
		return ymoney;
	}
	public void setYmoney(float ymoney) {
		this.ymoney = ymoney;
	}
	public float getYlessmoney() {
		return ylessmoney;
	}
	public void setYlessmoney(float ylessmoney) {
		this.ylessmoney = ylessmoney;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public String getLessmoney() {
		return lessmoney;
	}
	public void setLessmoney(String lessmoney) {
		this.lessmoney = lessmoney;
	}
	
	
	
	

}

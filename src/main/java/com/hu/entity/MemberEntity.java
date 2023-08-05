package com.hu.entity;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hu.util.MyPageVO;
@TableName("member")
@SuppressWarnings("serial")
public class MemberEntity extends MyPageVO implements Serializable {
	@TableId(type=IdType.AUTO)
	private int rid;
	@TableField("rcard")
	private String rcard;
	@TableField("rname")
	private String rname;
	@TableField("rpwd")
	private String rpwd;
	@TableField("rimg")
	private String rimg;
	@TableField("rtel")
	private String rtel;
	@TableField("rsex")
	private int rsex;
	@TableField("did")
	private int did;
	@TableField("rbirthday")
	private String rbirthday;
	@TableField("rstatus")
	private int rstatus;
	@TableField("rjf")
	private double rjf;
	@TableField("rcarnum")
	private String rcarnum;
	@TableField("xid")
	private int  xid;
	@TableField("rcolor")
	private String rcolor;
	@TableField("rway")
	private float rway;
	@TableField("zid")
	private int zid;
	@TableField("rnum")
	private String rnum;
	@TableField("raddress")
	private String raddress;
	@TableField("rremark")
	private String rremark;
	@TableField("rtime")
	private String rtime;
	@TableField("rmoney")
	private float rmoney;
	
	@TableField(exist = false)
	private int aid;
	@TableField(exist = false)
	private String dname;
	@TableField(exist = false)
	private String xname;
	@TableField(exist = false)
	private String aname;
	@TableField(exist = false)
	private String zname;
	@TableField(exist = false)
	private MultipartFile ximg;
	@TableField(exist = false)
	private String sex;
	@TableField(exist = false)
	private String flag;
	
	@TableField(exist = false)
	private String money;
	@TableField(exist = false)
	private String jf;
	
	@TableField(exist = false)
	private int number;

	public int getRid() {
		return rid;
	}

	public void setRid(int rid) {
		this.rid = rid;
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

	public String getRpwd() {
		return rpwd;
	}

	public void setRpwd(String rpwd) {
		this.rpwd = rpwd;
	}

	public String getRimg() {
		return rimg;
	}

	public void setRimg(String rimg) {
		this.rimg = rimg;
	}

	public String getRtel() {
		return rtel;
	}

	public void setRtel(String rtel) {
		this.rtel = rtel;
	}

	public int getRsex() {
		return rsex;
	}

	public void setRsex(int rsex) {
		this.rsex = rsex;
	}

	public int getDid() {
		return did;
	}

	public void setDid(int did) {
		this.did = did;
	}

	public String getRbirthday() {
		return rbirthday;
	}

	public void setRbirthday(String rbirthday) {
		this.rbirthday = rbirthday;
	}

	public int getRstatus() {
		return rstatus;
	}

	public void setRstatus(int rstatus) {
		this.rstatus = rstatus;
	}

	public double getRjf() {
		return rjf;
	}

	public void setRjf(double rjf) {
		this.rjf = rjf;
	}

	public String getRcarnum() {
		return rcarnum;
	}

	public void setRcarnum(String rcarnum) {
		this.rcarnum = rcarnum;
	}

	public int getXid() {
		return xid;
	}

	public void setXid(int xid) {
		this.xid = xid;
	}

	public String getRcolor() {
		return rcolor;
	}

	public void setRcolor(String rcolor) {
		this.rcolor = rcolor;
	}

	public float getRway() {
		return rway;
	}

	public void setRway(float rway) {
		this.rway = rway;
	}

	public int getZid() {
		return zid;
	}

	public void setZid(int zid) {
		this.zid = zid;
	}

	public String getRnum() {
		return rnum;
	}

	public void setRnum(String rnum) {
		this.rnum = rnum;
	}

	public String getRaddress() {
		return raddress;
	}

	public void setRaddress(String raddress) {
		this.raddress = raddress;
	}

	public String getRremark() {
		return rremark;
	}

	public void setRremark(String rremark) {
		this.rremark = rremark;
	}

	public String getRtime() {
		return rtime;
	}

	public void setRtime(String rtime) {
		this.rtime = rtime;
	}

	public float getRmoney() {
		return rmoney;
	}

	public void setRmoney(float rmoney) {
		this.rmoney = rmoney;
	}

	public String getDname() {
		return dname;
	}
	

	public int getAid() {
		return aid;
	}

	public void setAid(int aid) {
		this.aid = aid;
	}

	public void setDname(String dname) {
		this.dname = dname;
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

	public String getZname() {
		return zname;
	}

	public void setZname(String zname) {
		this.zname = zname;
	}

	public MultipartFile getXimg() {
		return ximg;
	}

	public void setXimg(MultipartFile ximg) {
		this.ximg = ximg;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getJf() {
		return jf;
	}

	public void setJf(String jf) {
		this.jf = jf;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	
	
	
	

}

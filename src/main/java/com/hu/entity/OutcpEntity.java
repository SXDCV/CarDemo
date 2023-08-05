package com.hu.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hu.util.MyPageVO;
@TableName("outcp")
@SuppressWarnings("serial")
public class OutcpEntity extends MyPageVO implements Serializable {
	@TableId(type=IdType.AUTO)
	private int tid;
	
	@TableField("rid")
	private int rid;
	
	@TableField("fid")
	private int fid;
	
	@TableField("tcount")
	private int tcount;
	
	@TableField("uid")
	private int uid;
	
	@TableField("ttime")
	private String ttime;
	
	@TableField("tflag")
	private int tflag;
	
	@TableField(exist = false)
	private String rcard;
	@TableField(exist = false)
	private String rname;
	@TableField(exist = false)
	private int cid;
	@TableField(exist = false)
	private String cname;
	@TableField(exist = false)
	private int did;
	@TableField(exist = false)
	private double dzk;
	@TableField(exist = false)
	private String fname;
	@TableField(exist = false)
	private String fdw;
	@TableField(exist = false)
	private float foutprice;
	@TableField(exist = false)
	private double tzk;
	@TableField(exist = false)
	private double txj;
	@TableField(exist = false)
	private String uname;
	@TableField(exist = false)
	private String dname;
	@TableField(exist = false)
	private double handle;
	@TableField(exist = false)
	private double rmoney;
	@TableField(exist = false)
	private int fcount;
	
	@TableField(exist = false)
	private String outprice;
	@TableField(exist = false)
	private String zk;
	@TableField(exist = false)
	private String xj;
	
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	public int getFid() {
		return fid;
	}
	public void setFid(int fid) {
		this.fid = fid;
	}
	public int getTcount() {
		return tcount;
	}
	public void setTcount(int tcount) {
		this.tcount = tcount;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getTtime() {
		return ttime;
	}
	public void setTtime(String ttime) {
		this.ttime = ttime;
	}
	public int getTflag() {
		return tflag;
	}
	public void setTflag(int tflag) {
		this.tflag = tflag;
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
	public int getDid() {
		return did;
	}
	public void setDid(int did) {
		this.did = did;
	}
	public double getDzk() {
		return dzk;
	}
	public void setDzk(double dzk) {
		this.dzk = dzk;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getFdw() {
		return fdw;
	}
	public void setFdw(String fdw) {
		this.fdw = fdw;
	}
	public float getFoutprice() {
		return foutprice;
	}
	public void setFoutprice(float foutprice) {
		this.foutprice = foutprice;
	}
	public double getTzk() {
		return tzk;
	}
	public void setTzk(double tzk) {
		this.tzk = tzk;
	}
	public double getTxj() {
		return txj;
	}
	public void setTxj(double txj) {
		this.txj = txj;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public double getHandle() {
		return handle;
	}
	public void setHandle(double handle) {
		this.handle = handle;
	}
	public double getRmoney() {
		return rmoney;
	}
	public void setRmoney(double rmoney) {
		this.rmoney = rmoney;
	}
	public int getFcount() {
		return fcount;
	}
	public void setFcount(int fcount) {
		this.fcount = fcount;
	}
	public String getOutprice() {
		return outprice;
	}
	public void setOutprice(String outprice) {
		this.outprice = outprice;
	}
	public String getZk() {
		return zk;
	}
	public void setZk(String zk) {
		this.zk = zk;
	}
	public String getXj() {
		return xj;
	}
	public void setXj(String xj) {
		this.xj = xj;
	}
	
	
}

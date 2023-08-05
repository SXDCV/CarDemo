package com.hu.entity;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hu.util.MyPageVO;
@TableName("mytf")
@SuppressWarnings("serial")
public class MytfEntity extends MyPageVO implements Serializable {
	@TableId(type=IdType.AUTO)
	private int fid;
	
	@TableField("fname")
	private String fname;
	
	@TableField("cid")
	private int cid;
	
	@TableField("fdw")
	private String fdw;
	
	@TableField("faddress")
	private String faddress;
	
	@TableField("foutprice")
	private float foutprice;
	
	@TableField("finprice")
	private float finprice;
	
	@TableField("fimg")
	private String fimg;
	
	@TableField("fcount")
	private int fcount;
	
	@TableField(exist = false)
	private MultipartFile cimg;
	@TableField(exist = false)
	private String cname;
	
	@TableField(exist = false)
	private String outprice;
	@TableField(exist = false)
	private String inprice;
	
	public int getFid() {
		return fid;
	}
	public void setFid(int fid) {
		this.fid = fid;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getFdw() {
		return fdw;
	}
	public void setFdw(String fdw) {
		this.fdw = fdw;
	}
	public String getFaddress() {
		return faddress;
	}
	public void setFaddress(String faddress) {
		this.faddress = faddress;
	}
	public float getFoutprice() {
		return foutprice;
	}
	public void setFoutprice(float foutprice) {
		this.foutprice = foutprice;
	}
	public float getFinprice() {
		return finprice;
	}
	public void setFinprice(float finprice) {
		this.finprice = finprice;
	}
	public String getFimg() {
		return fimg;
	}
	public void setFimg(String fimg) {
		this.fimg = fimg;
	}
	public int getFcount() {
		return fcount;
	}
	public void setFcount(int fcount) {
		this.fcount = fcount;
	}
	public MultipartFile getCimg() {
		return cimg;
	}
	public void setCimg(MultipartFile cimg) {
		this.cimg = cimg;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getOutprice() {
		return outprice;
	}
	public void setOutprice(String outprice) {
		this.outprice = outprice;
	}
	public String getInprice() {
		return inprice;
	}
	public void setInprice(String inprice) {
		this.inprice = inprice;
	}
	
	

}

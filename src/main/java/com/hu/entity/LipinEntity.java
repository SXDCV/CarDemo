package com.hu.entity;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hu.util.MyPageVO;
@TableName("lipin")
@SuppressWarnings("serial")
public class LipinEntity extends MyPageVO implements Serializable {
	@TableId(type=IdType.AUTO)
	private int nid;
	
	@TableField("nname")
	private String nname;
	
	@TableField("nimg")
	private String nimg;
	
	@TableField("njf")
	private int njf;
	
	@TableField("ncount")
	private int ncount;
	
	@TableField("nncount")
	private int nncount;
	
	@TableField(exist = false)
	private MultipartFile bimg;

	public int getNid() {
		return nid;
	}

	public void setNid(int nid) {
		this.nid = nid;
	}

	public String getNname() {
		return nname;
	}

	public void setNname(String nname) {
		this.nname = nname;
	}

	public String getNimg() {
		return nimg;
	}

	public void setNimg(String nimg) {
		this.nimg = nimg;
	}

	public int getNjf() {
		return njf;
	}

	public void setNjf(int njf) {
		this.njf = njf;
	}

	public int getNcount() {
		return ncount;
	}

	public void setNcount(int ncount) {
		this.ncount = ncount;
	}

	public int getNncount() {
		return nncount;
	}

	public void setNncount(int nncount) {
		this.nncount = nncount;
	}

	public MultipartFile getBimg() {
		return bimg;
	}

	public void setBimg(MultipartFile bimg) {
		this.bimg = bimg;
	}
	
	

}

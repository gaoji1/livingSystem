package com.bjut.s14024205.entity;

import java.util.Date;

public class PlayBack {

	int id;
	String uName;
	Date liveTime;
	String fileName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getuName() {
		return uName;
	}

	public void setuName(String uName) {
		this.uName = uName;
	}

	public Date getLiveTime() {
		return liveTime;
	}

	public void setLiveTime(Date liveTime) {
		this.liveTime = liveTime;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public String toString() {
		return "PlayBack [uName=" + uName + ", liveTime=" + liveTime + ", fileName=" + fileName + "]";
	}
	
	
}

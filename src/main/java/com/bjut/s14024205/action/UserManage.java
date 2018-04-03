package com.bjut.s14024205.action;

import com.opensymphony.xwork2.ActionSupport;

public class UserManage extends ActionSupport {
	
	//用户名
	private String uName;

	public String getuName() {
		return uName;
	}

	public void setuName(String uName) {
		this.uName = uName;
	}
	
	
	
	
	
	//获取指定用户的信息
	/**
	 * 通过用户名来查找数据库取得相关用户的信息，以及该用户直播间的信息
	 */
	public void getUserInfo() {
		System.out.println("233");
	}
}

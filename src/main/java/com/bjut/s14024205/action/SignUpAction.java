package com.bjut.s14024205.action;

import com.bjut.s14024205.dao.impl.UserDaoImpl;
import com.opensymphony.xwork2.ActionSupport;

public class SignUpAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//注入userdaoimpl
	private UserDaoImpl u;
	public UserDaoImpl getU() {
		return u;
	}
	public void setU(UserDaoImpl u) {
		this.u = u;
	}
	//验证注册函数(测试)
	public void verify() {
		System.out.println("成功进入");
	}

}

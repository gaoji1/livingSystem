package com.bjut.s14024205.action;

import com.bjut.s14024205.dao.impl.UserDaoImpl;
import com.opensymphony.xwork2.ActionSupport;

public class SignUpAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//ע��userdaoimpl
	private UserDaoImpl u;
	public UserDaoImpl getU() {
		return u;
	}
	public void setU(UserDaoImpl u) {
		this.u = u;
	}
	//��֤ע�ắ��(����)
	public void verify() {
		System.out.println("�ɹ�����");
	}

}

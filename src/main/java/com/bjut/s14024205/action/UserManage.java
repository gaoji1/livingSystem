package com.bjut.s14024205.action;

import com.opensymphony.xwork2.ActionSupport;

public class UserManage extends ActionSupport {
	
	//�û���
	private String uName;

	public String getuName() {
		return uName;
	}

	public void setuName(String uName) {
		this.uName = uName;
	}
	
	
	
	
	
	//��ȡָ���û�����Ϣ
	/**
	 * ͨ���û������������ݿ�ȡ������û�����Ϣ���Լ����û�ֱ�������Ϣ
	 */
	public void getUserInfo() {
		System.out.println("233");
	}
}

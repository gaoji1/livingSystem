package com.bjut.s14024205.action;

import com.bjut.s14024205.dao.impl.UserDaoImpl;
import com.bjut.s14024205.entity.User;
import com.opensymphony.xwork2.ActionSupport;

public class LogIn extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// dao
	private UserDaoImpl u;

	public UserDaoImpl getU() {
		return u;
	}

	public void setU(UserDaoImpl u) {
		this.u = u;
	}

	// ��֤�û�(����)
	public void verify() {

		User result = u.find("С��");
		if (result.getuName() != "С��") {
			System.out.println("�ɹ��{��dao");
		}
	}

	// ɾ���û�(����)
	public void delUser() {
		boolean result = u.delete("С��");
		if (result) {
			System.out.println("�ɹ�ɾ��С������Ϣ");
		} else {
			System.out.println("û��С������û�");
		}
	}

	// ����û�(����)
	public void add() {
		String uName = "С��";
		String passWord = "323";
		boolean result = u.add(uName, passWord);
		if (result) {
			System.out.println("��ӳɹ�");
		} else {
			System.out.println("���ʧ�ܣ��û����ظ�");
		}
	}
	// �޸�����(����)
	public void updatePassWord() {
		String uName = "С��";
		String oldPass = "323";
		String newPass = "233";
		int result = u.update(uName, oldPass, newPass);
		if(result == 1) {
			System.out.println("�����޸ĳɹ�");
		}else if(result == -1) {
			System.out.println("�Բ���û��"+uName+"����û�");
		}else {
			System.out.println("�Բ�������ԭ�����������");
		}
	}
}

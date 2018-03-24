package com.bjut.s14024205.action;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.bjut.s14024205.dao.impl.UserDaoImpl;
import com.bjut.s14024205.entity.User;
import com.opensymphony.xwork2.ActionSupport;

//LogIn action �Ѿ�ע��
public class LogIn extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// dao
	private UserDaoImpl u;
	//�û���
	private String uName;
	//�û�����
	private String passWord;

	public UserDaoImpl getU() {
		return u;
	}

	public void setU(UserDaoImpl u) {
		this.u = u;
	}
	

	public String getuName() {
		return uName;
	}

	public void setuName(String uName) {
		this.uName = uName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	// ��֤�û�(����)
	public void verify() throws IOException {
		System.out.println(this.uName);
		System.out.println(this.passWord);
		HttpServletResponse resp = ServletActionContext.getResponse();
		resp.getWriter().print("SUCCESS");
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

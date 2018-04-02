package com.bjut.s14024205.action;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.bjut.s14024205.dao.impl.UserDaoImpl;
import com.bjut.s14024205.entity.User;
import com.opensymphony.xwork2.ActionSupport;

public class SignUpAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//ע��userdaoimpl
	private UserDaoImpl u;
	private String uName;
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
	//��֤ע�ắ��(����)
	/**
	 * ע����֤���������ȸ����û�����ѯ�Ƿ���û����Ѿ�����
	 * �����ڣ�����û����ظ���������existing username 
	 * �������ڣ����û���������д�룬���ҷ���success
	 * @throws IOException 
	 */
	public void verify() throws IOException {
//		System.out.println("�ɹ�����");
//		System.out.println(this.uName);
//		System.out.println(this.passWord);
		HttpServletResponse resp = ServletActionContext.getResponse();
		User result = this.u.find(this.uName);
//		System.out.println(result);
		if(result != null) {
			resp.getWriter().print("existing username");
		}else {
			this.u.add(this.uName, this.passWord);
			resp.getWriter().print("success");
		}
		
	}

}

package com.bjut.s14024205.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;
import com.bjut.s14024205.dao.impl.LiveDaoImpl;
import com.bjut.s14024205.dao.impl.UserDaoImpl;
import com.bjut.s14024205.entity.Live;
import com.bjut.s14024205.entity.User;
import com.opensymphony.xwork2.ActionSupport;

public class UserManage extends ActionSupport {

	// �û���
	private String uName;
	private UserDaoImpl u;
	private LiveDaoImpl l;

	public String getuName() {
		return uName;
	}

	public void setuName(String uName) {
		this.uName = uName;
	}

	public UserDaoImpl getU() {
		return u;
	}

	public void setU(UserDaoImpl u) {
		this.u = u;
	}

	public LiveDaoImpl getL() {
		return l;
	}

	public void setL(LiveDaoImpl l) {
		this.l = l;
	}

	// ��ȡָ���û�����Ϣ
	/**
	 * ͨ���û������������ݿ�ȡ������û�����Ϣ���Լ����û�ֱ�������Ϣ
	 * @throws IOException 
	 */
	public void getUserInfo() throws IOException {
		HttpServletRequest req = ServletActionContext.getRequest();
		HttpSession session = req.getSession();
		HttpServletResponse resp = ServletActionContext.getResponse();
		//�û���ֱ�Ӵ�session����ȡ������
		this.setuName((String) session.getAttribute("uName"));
		Live temp_live = l.findByName(this.uName);
		String output = JSON.toJSONString(temp_live);
		System.out.println(this.uName);
		System.out.println(output);
		resp.getWriter().print(output);
		
	}
}

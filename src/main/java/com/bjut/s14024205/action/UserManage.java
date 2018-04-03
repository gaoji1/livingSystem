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

	// 用户名
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

	// 获取指定用户的信息
	/**
	 * 通过用户名来查找数据库取得相关用户的信息，以及该用户直播间的信息
	 * @throws IOException 
	 */
	public void getUserInfo() throws IOException {
		HttpServletRequest req = ServletActionContext.getRequest();
		HttpSession session = req.getSession();
		HttpServletResponse resp = ServletActionContext.getResponse();
		//用户名直接从session里面取就行了
		this.setuName((String) session.getAttribute("uName"));
		Live temp_live = l.findByName(this.uName);
		String output = JSON.toJSONString(temp_live);
		System.out.println(this.uName);
		System.out.println(output);
		resp.getWriter().print(output);
		
	}
}

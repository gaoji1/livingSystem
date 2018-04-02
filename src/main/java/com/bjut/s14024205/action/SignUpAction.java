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
	
	//注入userdaoimpl
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
	//验证注册函数(测试)
	/**
	 * 注册验证函数，首先根据用户名查询是否该用户名已经存在
	 * 若存在，则该用户名重复，并返回existing username 
	 * 若不存在，则将用户名，密码写入，并且返回success
	 * @throws IOException 
	 */
	public void verify() throws IOException {
//		System.out.println("成功进入");
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

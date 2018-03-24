package com.bjut.s14024205.action;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.bjut.s14024205.dao.impl.UserDaoImpl;
import com.bjut.s14024205.entity.User;
import com.opensymphony.xwork2.ActionSupport;

//LogIn action 已经注册
public class LogIn extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// dao
	private UserDaoImpl u;
	//用户名
	private String uName;
	//用户密码
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

	// 验证用户(测试)
	public void verify() throws IOException {
		System.out.println(this.uName);
		System.out.println(this.passWord);
		HttpServletResponse resp = ServletActionContext.getResponse();
		resp.getWriter().print("SUCCESS");
	}

	// 删除用户(测试)
	public void delUser() {
		boolean result = u.delete("小明");
		if (result) {
			System.out.println("成功删除小明的信息");
		} else {
			System.out.println("没用小明这个用户");
		}
	}

	// 添加用户(测试)
	public void add() {
		String uName = "小刚";
		String passWord = "323";
		boolean result = u.add(uName, passWord);
		if (result) {
			System.out.println("添加成功");
		} else {
			System.out.println("添加失败，用户名重复");
		}
	}
	// 修改密码(测试)
	public void updatePassWord() {
		String uName = "小刚";
		String oldPass = "323";
		String newPass = "233";
		int result = u.update(uName, oldPass, newPass);
		if(result == 1) {
			System.out.println("密码修改成功");
		}else if(result == -1) {
			System.out.println("对不起没有"+uName+"这个用户");
		}else {
			System.out.println("对不起，您的原密码输入错误");
		}
	}
}

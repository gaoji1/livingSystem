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

	// 验证用户(测试)
	public void verify() {

		User result = u.find("小明");
		if (result.getuName() != "小明") {
			System.out.println("成功調用dao");
		}
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

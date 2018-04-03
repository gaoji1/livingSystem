package com.bjut.s14024205.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
	// 用户名
	private String uName;
	// 用户密码
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

	// 验证用户
	/**
	 * 接收用户提交的用户名以及密码， 首先查看当前用户名是否存在 若不存在该用户则返回"username does not exist"
	 * 若存在，进一步判断密码是否相同 若相同则返回"success"并设置cookies和向session中写入当前登陆的用户名及密码
	 * 若不相同则返回"wrong password"
	 * 
	 * @throws IOException
	 */
	public void verify() throws IOException {
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		HttpSession session = req.getSession();
		User result = u.find(this.uName);
		if (result == null) {
			resp.getWriter().print("username does not exist");
		}else {
			if(result.getPassWord().equals(this.passWord)) {
				
				session.setAttribute("uName", this.uName);
				session.setAttribute("passWord", this.passWord);
				resp.getWriter().print("success");
			}else {
				resp.getWriter().print("wrong password");
			}
		}

	}
//	*-----------------------------------------*//
	//判断登陆状态
	/**
	 * 在进入login页面时候做一次判断，session中是否已经有了正在使用的用户信息
	 * 如果有，那么说明用户已经登陆，并且并未主动注销，此时引导用户回到index页面
	 * 如果没有，那么这是一个新用户，引导用户登陆
	 * @throws IOException 
	 * 
	 */
	public void judge() throws IOException {
		HttpServletRequest req = ServletActionContext.getRequest();
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpSession session = req.getSession();
		String tempName = (String) session.getAttribute("uName");
		String tempPsword = (String) session.getAttribute("passWord");
//		System.out.println(tempName);
//		System.out.println(tempPsword);
		if(tempName == null || tempPsword==null) {
			resp.getWriter().print("no existing account");
		}else {
			resp.getWriter().print(tempName);
		}
	}
	//**------------------------------**//
	//解除登陆状态
	/**
	 * 删除存放在session里的用户信息，系统恢复成未登录的状态
	 */
	public void signout() {
		HttpServletRequest req = ServletActionContext.getRequest();
		HttpSession session = req.getSession();
		session.removeAttribute("uName");
		session.removeAttribute("passWord");
		System.out.println("解除登陆");
		return;
	}
	//*---------------------------------------*//
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
		if (result == 1) {
			System.out.println("密码修改成功");
		} else if (result == -1) {
			System.out.println("对不起没有" + uName + "这个用户");
		} else {
			System.out.println("对不起，您的原密码输入错误");
		}
	}
}

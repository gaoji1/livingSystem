package com.bjut.s14024205.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
	// �û���
	private String uName;
	// �û�����
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

	// ��֤�û�
	/**
	 * �����û��ύ���û����Լ����룬 ���Ȳ鿴��ǰ�û����Ƿ���� �������ڸ��û��򷵻�"username does not exist"
	 * �����ڣ���һ���ж������Ƿ���ͬ ����ͬ�򷵻�"success"������cookies����session��д�뵱ǰ��½���û���������
	 * ������ͬ�򷵻�"wrong password"
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
	//�жϵ�½״̬
	/**
	 * �ڽ���loginҳ��ʱ����һ���жϣ�session���Ƿ��Ѿ���������ʹ�õ��û���Ϣ
	 * ����У���ô˵���û��Ѿ���½�����Ҳ�δ����ע������ʱ�����û��ص�indexҳ��
	 * ���û�У���ô����һ�����û��������û���½
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
	//�����½״̬
	/**
	 * ɾ�������session����û���Ϣ��ϵͳ�ָ���δ��¼��״̬
	 */
	public void signout() {
		HttpServletRequest req = ServletActionContext.getRequest();
		HttpSession session = req.getSession();
		session.removeAttribute("uName");
		session.removeAttribute("passWord");
		System.out.println("�����½");
		return;
	}
	//*---------------------------------------*//
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
		if (result == 1) {
			System.out.println("�����޸ĳɹ�");
		} else if (result == -1) {
			System.out.println("�Բ���û��" + uName + "����û�");
		} else {
			System.out.println("�Բ�������ԭ�����������");
		}
	}
}

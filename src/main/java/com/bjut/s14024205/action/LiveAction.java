package com.bjut.s14024205.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;
import com.bjut.s14024205.dao.impl.LiveDaoImpl;
import com.bjut.s14024205.entity.Live;
import com.opensymphony.xwork2.ActionSupport;

/**
 * ֱ��action
 * ��ȡֱ����Ϣ�뷢��ֱ����������
 * @author gaoji
 *
 */
public class LiveAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	LiveDaoImpl l;

	public LiveDaoImpl getL() {
		return l;
	}

	public void setL(LiveDaoImpl l) {
		this.l = l;
	}
	//����ֱ��
	/**
	 * ������ֱ����
	 * ֱ����Ĵ���ʵ����ʱͨ��red5��ֱ��Ӧ�ô����ģ��յ�ֱ������ͻ��open��־�򿪣�
	 * ���service�������ǣ���ǰ��ָ���û�һ��һ�������������
	 * �û���ɺ�����ʼֱ���İ�ť�����뵽���service�󣬲�ѯ��ǰ�û���ֱ�����ݣ����open��־�ǲ��Ǵ򿪵ģ�
	 * ����ǣ���˵���û��Ѿ���ʼ�������򷵻�ֵ�����û�ֱ���俪���ɹ������open��־��Ȼ�ǹرյģ����û�û��������
	 * ǰ����Ҫ���������û�����
	 * @throws IOException 
	 */
	public void beginLive() throws IOException {
		HttpServletRequest req = ServletActionContext.getRequest();
		HttpSession session = req.getSession();
		HttpServletResponse resp = ServletActionContext.getResponse();
		String uName = (String) session.getAttribute("uName");
		Live live = l.findByName(uName);
		if(live.getIsOpen() == false) {
			resp.getWriter().print("false");
		}else {
			resp.getWriter().println("success");
		}
		return;
	}
	
	//��ѯֱ������Ϣ
	/**
	 * ��ѯ����ֱ����ֱ���䲢����ǰ��
	 * @throws IOException 
	 */
	public void getLiveInfo() throws IOException {
		HttpServletResponse resp = ServletActionContext.getResponse();
		resp.setCharacterEncoding("utf-8");
		List<Live> output = l.listLiveRoom();
		resp.getWriter().print(JSON.toJSONString(output));
	}

	// ��ѯֱ����Ϣ(����)
	public void find() {
		Live result = l.findByName("С��");
		if (result == null) {
			System.out.println("û�ҵ���Ӧ��ֱ����Ϣ");
		} else {
			System.out.println("�ҵ���");
		}
	}

	// ���ֱ����Ϣ(����)
	public void add() {
		boolean result = l.add("С��", "lv_2");
		if (result) {
			System.out.println("ֱ����Ϣ������");
		} else {
			System.out.println("ֱ����Ϣ���ʧ��,��Ӧ�������Ѿ���ռ��");
		}
	}

	// ɾ��ֱ����Ϣ(����)
	public void delete() {
		String uName = "С��";
		String streamName = "lv_2";
		boolean result = l.delete(uName, streamName);
		if (result) {
			System.out.println("ɾ���ɹ�");
		} else {
			System.out.println("û�ж�Ӧ��ֱ����Ϣ");
		}
	}

	

}

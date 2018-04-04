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
	private String streamName;
	private String roomName;
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

	public String getStreamName() {
		return streamName;
	}

	public void setStreamName(String streamName) {
		this.streamName = streamName;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	// ��ȡָ���û�����Ϣ
	/**
	 * ͨ���û������������ݿ�ȡ������û�����Ϣ���Լ����û�ֱ�������Ϣ
	 * 
	 * @throws IOException
	 */
	public void getUserInfo() throws IOException {
		HttpServletRequest req = ServletActionContext.getRequest();
		HttpSession session = req.getSession();
		HttpServletResponse resp = ServletActionContext.getResponse();
		// �û���ֱ�Ӵ�session����ȡ������
		this.setuName((String) session.getAttribute("uName"));
		Live temp_live = l.findByName(this.uName);
		String output = JSON.toJSONString(temp_live);
//		System.out.println(this.uName);
//		System.out.println(output);
		resp.getWriter().print(output);

	}
	
	//�����û���Ϣ
	/**
	 * �ڴ˴������û�����Ϣ�����ݴ�������˽��ܵ���������
	 * ����Ӧ�ô�session��ȡ����ǰ�û����������ݴ��û�����������û�����Ϣ
	 * ����ж�uName,streamName,roomName��������û���ĸ�����ǰ��Ϣ��ͬ����ͬ�Ž��и���
	 * ��ͬ�����Բ����и���
	 * roomName��ͬ����ֱ�Ӹ��ģ�����Ҫ����
	 * uName��streamName��Ҫ�Ȳ��أ�ȷ�����ظ�����Ը��ģ������ظ���˴��޸���Ч�����ض�Ӧ������Ϣ
	 * 
	 * ��ǰ���ݿ��������û����ɣ��޸��û������ۺܴ�������ʱ���ṩ�޸��û������ܣ��Ժ��п��ܻ����
	 * @throws IOException 
	 */
	public void changeUserInfo() throws IOException {
		HttpServletRequest req = ServletActionContext.getRequest();
		HttpSession session = req.getSession();
		HttpServletResponse resp = ServletActionContext.getResponse();
		User user = u.find((String) session.getAttribute("uName"));
		ArrayList<Object> output = new ArrayList<Object>();
		boolean result_stream = l.update(user.getuName(), this.streamName);
		if(result_stream == false) {
			String stream_out = "Stream already exists";
			output.add(stream_out);
		}else {
			String stream_out = "success";
			output.add(stream_out);
		}
		
		boolean result_room = l.changeRoomName(user.getuName(), this.roomName);
		if(result_room == false) {
			String out_room = "Update roomname failed";
			output.add(out_room);
		}else {
			String out_room = "success";
			output.add(out_room);
		}
		resp.getWriter().print(JSON.toJSONString(output));
		return;
		
		
	}
}

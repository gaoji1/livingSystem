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

	// 获取指定用户的信息
	/**
	 * 通过用户名来查找数据库取得相关用户的信息，以及该用户直播间的信息
	 * 
	 * @throws IOException
	 */
	public void getUserInfo() throws IOException {
		HttpServletRequest req = ServletActionContext.getRequest();
		HttpSession session = req.getSession();
		HttpServletResponse resp = ServletActionContext.getResponse();
		// 用户名直接从session里面取就行了
		this.setuName((String) session.getAttribute("uName"));
		Live temp_live = l.findByName(this.uName);
		String output = JSON.toJSONString(temp_live);
//		System.out.println(this.uName);
//		System.out.println(output);
		resp.getWriter().print(output);

	}
	
	//更改用户信息
	/**
	 * 在此处更改用户的信息，根据从浏览器端接受的三个参数
	 * 首先应该从session中取出当前用户名，并根据此用户名查出这名用户的信息
	 * 随后判断uName,streamName,roomName三者中有没有哪个跟当前信息不同，不同才进行更改
	 * 相同的属性不进行更改
	 * roomName不同可以直接更改，不需要查重
	 * uName跟streamName都要先查重，确认无重复后可以更改，若有重复则此次修改无效，返回对应错误信息
	 * 
	 * 当前数据库外键依赖没有完成，修改用户名代价很大，所以暂时不提供修改用户名功能，以后有可能会添加
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

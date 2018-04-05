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
 * 直播action
 * 获取直播信息与发布直播都在这里
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
	//发起直播
	/**
	 * 负责发起直播的
	 * 直播间的创建实际上时通过red5的直播应用创建的（收到直播流后就会把open标志打开）
	 * 这个service的作用是：在前端指导用户一步一步完成推流操作
	 * 用户完成后点击开始直播的按钮，进入到这个service后，查询当前用户的直播数据，检查open标志是不是打开的，
	 * 如果是，则说明用户已经开始推流，则返回值告诉用户直播间开启成功，如果open标志依然是关闭的，那用户没有推流，
	 * 前端需要继续引导用户推流
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
	
	//查询直播间信息
	/**
	 * 查询正在直播的直播间并发给前端
	 * @throws IOException 
	 */
	public void getLiveInfo() throws IOException {
		HttpServletResponse resp = ServletActionContext.getResponse();
		resp.setCharacterEncoding("utf-8");
		List<Live> output = l.listLiveRoom();
		resp.getWriter().print(JSON.toJSONString(output));
	}

	// 查询直播信息(测试)
	public void find() {
		Live result = l.findByName("小明");
		if (result == null) {
			System.out.println("没找到对应的直播信息");
		} else {
			System.out.println("找到了");
		}
	}

	// 添加直播信息(测试)
	public void add() {
		boolean result = l.add("小明", "lv_2");
		if (result) {
			System.out.println("直播信息添加完成");
		} else {
			System.out.println("直播信息添加失败,对应流名称已经被占用");
		}
	}

	// 删除直播信息(测试)
	public void delete() {
		String uName = "小刚";
		String streamName = "lv_2";
		boolean result = l.delete(uName, streamName);
		if (result) {
			System.out.println("删除成功");
		} else {
			System.out.println("没有对应的直播信息");
		}
	}

	

}

package com.bjut.s14024205.action;

import com.bjut.s14024205.dao.impl.LiveDaoImpl;
import com.bjut.s14024205.entity.Live;
import com.opensymphony.xwork2.ActionSupport;

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

	// 查询直播信息(测试)
	public void find() {
		Live result = l.findByStreamName("lv_1");
		if (result == null) {
			System.out.println("没找到对应的直播信息");
		} else {
			System.out.println("找到了");
		}
	}
//	添加直播信息(测试)
	public void add() {
		boolean result = l.add("小明", "lv_2");
		if(result) {
			System.out.println("直播信息添加完成");
		}else {
			System.out.println("直播信息添加失败,对应流名称已经被占用");
		}
	}
//	删除直播信息(测试)
	public void delete() {
		String uName = "小刚";
		String streamName = "lv_2";
		boolean result = l.delete(uName, streamName);
		if(result) {
			System.out.println("删除成功");
		}else {
			System.out.println("没有对应的直播信息");
		}
	}

}

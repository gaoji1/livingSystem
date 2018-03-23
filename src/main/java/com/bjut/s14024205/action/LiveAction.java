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

	// ��ѯֱ����Ϣ(����)
	public void find() {
		Live result = l.findByStreamName("lv_1");
		if (result == null) {
			System.out.println("û�ҵ���Ӧ��ֱ����Ϣ");
		} else {
			System.out.println("�ҵ���");
		}
	}
//	���ֱ����Ϣ(����)
	public void add() {
		boolean result = l.add("С��", "lv_2");
		if(result) {
			System.out.println("ֱ����Ϣ������");
		}else {
			System.out.println("ֱ����Ϣ���ʧ��,��Ӧ�������Ѿ���ռ��");
		}
	}
//	ɾ��ֱ����Ϣ(����)
	public void delete() {
		String uName = "С��";
		String streamName = "lv_2";
		boolean result = l.delete(uName, streamName);
		if(result) {
			System.out.println("ɾ���ɹ�");
		}else {
			System.out.println("û�ж�Ӧ��ֱ����Ϣ");
		}
	}

}

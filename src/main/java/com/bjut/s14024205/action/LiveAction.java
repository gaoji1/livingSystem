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

	// ����ֱ����Ϣ(����)
	public void update() {
		String uName = "С��";
		String oldStream = "lv_12";
		String newStream = "lv_5";
		int result = l.update(uName, oldStream, newStream);
		if (result == 1) {
			System.out.println("�޸ĳɹ�");
		}else if(result == -1) {
			System.out.println("û���ҵ�Ҫ�޸ĵ���Ϣ");
		}else {
			System.out.println("��Ҫ�޸ĵ��������Ѿ���ռ��");
		}
	}

}

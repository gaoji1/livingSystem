package com.bjut.s14024205.action;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;
import com.bjut.s14024205.dao.impl.PlaybackDaoImpl;
import com.bjut.s14024205.entity.PlayBack;
import com.opensymphony.xwork2.ActionSupport;

public class PlaybackAction extends ActionSupport {
	private PlaybackDaoImpl p;

	public PlaybackDaoImpl getP() {
		return p;
	}

	public void setP(PlaybackDaoImpl p) {
		this.p = p;
	}
	
	//�������лط�
	/**
	 * �����ִ��ڵ�����ֱ��¼��
	 * @throws IOException 
	 */
	public void getAllPlayBack() throws IOException {
		HttpServletResponse resp = ServletActionContext.getResponse();
		resp.setCharacterEncoding("utf-8");
		List<PlayBack> output = p.findAll();
		resp.getWriter().print(JSON.toJSONString(output));
		
	}

	// �����û�������(����)
	public void find() {
		String uName = "liming";
		List<PlayBack> result = p.findByName(uName);
		for (int i = 0; i < result.size(); i++) {
			System.out.println(result.get(i).toString());
		}
	}
	//����ֱ�����ڲ���
	public void findByDate() {
		DateFormat dateFormate = DateFormat.getDateInstance();
		try {
			Date liveDate = dateFormate.parse("2018-3-13");
			List<PlayBack> result = p.findByDate(liveDate);
			if(result != null) {
				for(int i=0;i<result.size();i++) {
					System.out.println(result.get(i).toString());
				}
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}

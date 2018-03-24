package com.bjut.s14024205.action;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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

	// 根据用户名查找(测试)
	public void find() {
		String uName = "liming";
		List<PlayBack> result = p.findByName(uName);
		for (int i = 0; i < result.size(); i++) {
			System.out.println(result.get(i).toString());
		}
	}
	//根据直播日期查找
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

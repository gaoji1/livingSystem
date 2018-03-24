package com.bjut.s14024205.action;

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

	// ≤È’“(≤‚ ‘)
	public void find() {
		String uName = "liming";
		List<PlayBack> result = p.findByName(uName);
		for (int i = 0; i < result.size(); i++) {
			System.out.println(result.get(i).toString());
		}
	}
}

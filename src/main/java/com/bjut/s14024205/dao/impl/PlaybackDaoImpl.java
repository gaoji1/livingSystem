package com.bjut.s14024205.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.bjut.s14024205.dao.PlayBackDao;
import com.bjut.s14024205.entity.PlayBack;

@Transactional
public class PlaybackDaoImpl implements PlayBackDao {
//	ע��hibernateģ��
	private HibernateTemplate hibernateTemplete;

	public HibernateTemplate getHibernateTemplete() {
		return hibernateTemplete;
	}

	public void setHibernateTemplete(HibernateTemplate hibernateTemplete) {
		this.hibernateTemplete = hibernateTemplete;
	}
	
//	�������лط���Ϣ
	/**
	 * ��������ֱ����Ϣa
	 */
	public List<PlayBack> findAll() {
		DetachedCriteria criteria = DetachedCriteria.forClass(PlayBack.class);
		List<PlayBack> output = (List<PlayBack>) hibernateTemplete.findByCriteria(criteria);
		if(output.isEmpty()) {
			return null;
		}else {
			return output;
		}
	}
	
// �����û�������
	public List<PlayBack> findByName(String uName){
		DetachedCriteria PlaybackCriteria =DetachedCriteria.forClass(PlayBack.class);
		PlaybackCriteria.add(Restrictions.eq("uName", uName));
		List<PlayBack> result = (List<PlayBack>) hibernateTemplete.findByCriteria(PlaybackCriteria);
		if(result.size() == 0) {
			return null;
		}else {
			return result;
		}
	}
//	�������ڲ�ѯ
	public List<PlayBack> findByDate(Date liveDate) {
		DetachedCriteria PlaybackCriteria =DetachedCriteria.forClass(PlayBack.class);
		PlaybackCriteria.add(Restrictions.eq("liveTime", liveDate));
		List<PlayBack> result = (List<PlayBack>) hibernateTemplete.findByCriteria(PlaybackCriteria);
		if(result.size() == 0) {
			return null;
		}else {
			return result;
		}
	}
	
}

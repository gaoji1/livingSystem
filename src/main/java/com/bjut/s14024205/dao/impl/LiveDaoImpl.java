package com.bjut.s14024205.dao.impl;

import org.springframework.orm.hibernate5.HibernateTemplate;

import com.bjut.s14024205.dao.LiveDao;

public class LiveDaoImpl implements LiveDao {
	HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
}

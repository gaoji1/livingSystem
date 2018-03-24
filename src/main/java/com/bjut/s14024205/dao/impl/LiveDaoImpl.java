package com.bjut.s14024205.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.bjut.s14024205.dao.LiveDao;
import com.bjut.s14024205.entity.Live;

@Transactional
public class LiveDaoImpl implements LiveDao {
	// hibernateģ��
	HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	// ����ֱ������Ϣ
	public Live findByStreamName(String streamName) {
		DetachedCriteria liveCriteria = DetachedCriteria.forClass(Live.class);
		liveCriteria.add(Restrictions.eq("streamName", streamName));
		List<Live> result = (List<Live>) hibernateTemplate.findByCriteria(liveCriteria);
		if (result.size() == 0) {
			return null;
		} else {
			return result.get(0);
		}
	}
//	����ֱ����Ϣ(�����û���)
	public Live findByName(String uName) {
		DetachedCriteria liveCriteria = DetachedCriteria.forClass(Live.class);
		liveCriteria.add(Restrictions.eq("uName", uName));
		List<Live> result = (List<Live>) hibernateTemplate.findByCriteria(liveCriteria);
		if (result.size() == 0) {
			return null;
		} else {
			return result.get(0);
		}
	}

	// ���ֱ������Ϣ
	// ��Ӧ��Ϣ�Ѿ�����ӣ�true ��Ӧ��Ϣ�����ڣ�true ��Ӧ��Ϣ�Ѿ����ڣ�uName����ͬ��:false
	public boolean add(String uName, String streamName) {
		Live result = this.findByStreamName(streamName);
		if (result == null) {
			Live newLive = new Live();
			newLive.setuName(uName);
			newLive.setStreamName(streamName);
			newLive.setIsOpen(false);
			hibernateTemplate.save(newLive);
			return true;
		} else {
			if (uName.equals(result.getuName())) {
				return true;
			} else {
				return false;
			}
		}

	}

	// ɾ��ֱ����Ϣ
	// true:ɾ���ɹ� false:û�ж�Ӧ����Ϣ
	public boolean delete(String uName, String streamName) {
		Live result = this.findByStreamName(streamName);
		if (result == null) {
			return false;
		} else {
			if (uName.equals(result.getuName())) {
				hibernateTemplate.delete(result);
				return true;
			} else {
				return false;
			}
		}
	}

	// ����ֱ����Ϣ
	// 1:���ĳɹ�
	// -1����Ӧֱ����Ϣ����
	// -2���µ��������Ѿ���ռ��
	public int update(String uName, String oldStream, String newStream) {
		Live oldLive = this.findByStreamName(oldStream);
		if(oldLive == null) {
			return -1;
		}else {
			if(uName.equals(oldLive.getuName())) {
				Live repeater = this.findByStreamName(newStream);
				if(repeater == null) {
					Live newLive = oldLive;
					newLive.setStreamName(newStream);
					hibernateTemplate.update(newLive);
					return 1;
				}else {
					return -2;
				}
				
			}else {
				return -1;
			}
		}
	}
}

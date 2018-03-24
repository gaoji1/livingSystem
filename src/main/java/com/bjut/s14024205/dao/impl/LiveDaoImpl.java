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
	// hibernate模板
	HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	// 查找直播流信息
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
//	查找直播信息(根据用户名)
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

	// 添加直播流信息
	// 对应信息已经被添加：true 对应信息不存在：true 对应信息已经存在（uName不相同）:false
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

	// 删除直播信息
	// true:删除成功 false:没有对应的信息
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

	// 更改直播信息
	// 1:更改成功
	// -1：对应直播信息错误
	// -2：新的流名称已经被占用
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

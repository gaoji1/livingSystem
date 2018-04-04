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
	private HibernateTemplate hibernateTemplate;

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
	/**
	 * ����ֱ������Ϣ
	 * �޸���֮ǰ�İ汾������ֱ����������Ҫ֪����ǰ��ֱ������ʲô
	 * �������ֲ��ֱ����Ϣ
	 * 	���ֱ����ϢΪ�տգ���ôΪ�û��½�ֱ����Ϣ���洢
	 * ���ֱ����Ϣ��Ϊ�գ����һ�����µ������ƽ��в���
	 * ȷ��û���ظ�֮�󣬶������ƽ�������
	 * 
	 * ��ԭ�еĽ����������ߴ����µ�ֱ������Ϣ���㺯��ִ�гɹ�
	 * �£���ֱ��������һ��ʱҲ���޸ĳɹ�
	 * ֻ�е��µ��������Ѿ����ڵ�ʱ��᷵��false
	 * @param uName
	 * @param newStream
	 * @return
	 */
	public boolean update(String uName,String newStream) {
		Live live = this.findByName(uName);
		if(live == null) {
			Live newlive = new Live();
			newlive.setuName(uName);
			newlive.setStreamName(newStream);
			hibernateTemplate.save(newlive);
			return true;
		}else {
			Live live2 = this.findByStreamName(newStream);
			if(live2 == null) {
				live.setStreamName(newStream);
				hibernateTemplate.update(live);
				return true;
			}else if(live2.getuName().equals(live.getuName())) {
				return true;
			}else {
				return false;
			}
		}
	}
	//����ֱ���䷿����
	/**
	 * ����ĳ�û�ֱ����ķ�����
	 * ��������ͬ�û�֮�������ظ�������ֻҪ�û���ǰ��ֱ������Ϣ���ڣ�����������޸�
	 * ���ù��Ƿ��ظ�
	 * ���Գ��ǲ����ڶ�Ӧ�û���ֱ����Ϣ�������޸Ķ��ǻ�ɹ���
	 * 
	 * �������ڶ�Ӧ�û�ֱ����Ϣ��ʱ��Ϊ�û�����ֱ����Ϣ������
	 * ������֮���޸���Զ�����ǳɹ���
	 * @param uName
	 * @param newRoom
	 * @return
	 */
	public boolean changeRoomName(String uName,String newRoom) {
		Live live = this.findByName(uName);
		if(live != null) {
			live.setRoomName(newRoom);
			hibernateTemplate.update(live);
			return true;
		}else {
			Live newlive = new Live();
			newlive.setuName(uName);
			newlive.setRoomName(newRoom);
			hibernateTemplate.save(newlive);
			return true;
		}
		
	}
}

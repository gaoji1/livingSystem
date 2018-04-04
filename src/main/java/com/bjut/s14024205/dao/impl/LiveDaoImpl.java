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
	private HibernateTemplate hibernateTemplate;

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
	/**
	 * 更改直播流信息
	 * 修改了之前的版本，更改直播流并不需要知道以前的直播流叫什么
	 * 根据名字查出直播信息
	 * 	如果直播信息为空空，那么为用户新建直播信息并存储
	 * 如果直播信息不为空，则进一步对新的流名称进行查重
	 * 确认没有重复之后，对流名称进行升级
	 * 
	 * 对原有的进行升级或者创建新的直播流信息都算函数执行成功
	 * 新，旧直播流名称一样时也算修改成功
	 * 只有当新的流名称已经存在的时候会返回false
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
	//更改直播间房间名
	/**
	 * 更改某用户直播间的房间名
	 * 房间名不同用户之间允许重复，所以只要用户当前的直播间信息存在，便可以立即修改
	 * 不用管是否重复
	 * 所以除非不存在对应用户的直播信息，否则修改都是会成功的
	 * 
	 * 当不存在对应用户直播信息的时候，为用户创建直播信息并保存
	 * 这样做之后修改永远都会是成功的
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

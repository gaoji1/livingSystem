package com.bjut.s14024205.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.bjut.s14024205.dao.UserDao;
import com.bjut.s14024205.entity.User;

@Transactional
public class UserDaoImpl implements UserDao {
//  hibernate模板，使用spring注入
	private HibernateTemplate hibernateTemplate;

	

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}



	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}


//	通过姓名查找用户信息
	public User find(String uName) {
		DetachedCriteria UserCritera = DetachedCriteria.forClass(User.class);
		UserCritera.add(Restrictions.eq("uName", uName));
		
		List<User> result =  (List<User>) hibernateTemplate.findByCriteria(UserCritera);
		if (result.size() == 0) {
			return null;
		}
		else {
			return (User)result.get(0);
		}
	}
//	删除用户信息
	public boolean delete(String uName) {
		User result = this.find(uName);
		if(uName.equals(result.getuName())) {
			hibernateTemplate.delete(result);
			return true;
			
		}else {
			return false;
		}
	}
//	添加用户信息
	public boolean add(String uName,String passWord) {
		User result = this.find(uName);
		if(result != null) {
			return false;
		}else {
			User newUser = new User();
			newUser.setuName(uName);
			newUser.setPassWord(passWord);
			hibernateTemplate.save(newUser);
			return true;
		}
	}
//	修改密码
//	1:成功      -1:此用户不存在    -2:原密码错误
	public int update(String uName,String oldPassWord,String newPassWorld) {
		User result = this.find(uName);
		if(uName.equals(result.getuName())) {
			if(oldPassWord.equals(result.getPassWord())) {
				User updateUer = result;
				updateUer.setPassWord(newPassWorld);
				hibernateTemplate.update(updateUer);
				return 1;
			}else {
				return -2;
			}
		}else {
			return -1;
		}
	}
	
	
}

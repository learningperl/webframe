package com.testing.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.testing.entity.User;

@Repository
public class UserDao implements IUserDao {

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void Register(User user) throws Exception {
		sessionFactory.getCurrentSession().save(user);
	}

	@SuppressWarnings("unchecked")
	@Override
	public User Login(String username,String password) {
		List<User> users = sessionFactory.getCurrentSession().createQuery("from User where username = :username and pwd= :password")
				.setParameter("username", username).setParameter("password", password).list();
		if (users.size() > 0) {
			return users.get(0);
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void Updater(User u) {
		Session session = sessionFactory.getCurrentSession();
		List<User> users = session.createQuery("from User where id = :userid")
				.setParameter("userid", u.getId()).list();
		if (users.size() > 0) {
			users.get(0).setDesc(u.getDesc());
			users.get(0).setNick(u.getNick());
			users.get(0).setImg(u.getImg());
			session.update(users.get(0));
			session.flush();
		} else {
			return ;
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public User UserInfo(Integer userid) {
		// TODO Auto-generated method stub
		List<User> users = sessionFactory.getCurrentSession().createQuery("from User where id = :userid").setParameter("userid", userid).list();
		if (users.size() > 0) {
			return users.get(0);
		} else {
			return null;
		}
	}
	
	@Override
	public Integer getUserCount() {
		Session session = sessionFactory.getCurrentSession();
		return Integer.parseInt(session.createSQLQuery("select count(id) from userinfo").list().get(0).toString());
	}

}

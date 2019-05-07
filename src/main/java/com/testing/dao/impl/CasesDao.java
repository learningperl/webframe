package com.testing.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.testing.dao.ICasesDao;
import com.testing.entity.Cases;

@Repository
public class CasesDao implements ICasesDao {

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Cases> getCases(String userid) {
		List<Cases> cases = sessionFactory.getCurrentSession().createQuery("from Cases where userid = :userid")
				.setParameter("userid", Integer.parseInt(userid)).list();
		if (cases.size() > 0) {
			return cases;
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer Updater(Cases c) {
		Session session = sessionFactory.getCurrentSession();
		if (c.getId()!=null) {
			List<Cases> cases = session.createQuery("from Cases where id = :id")
					.setParameter("id", c.getId()).list();
			if (cases.size() > 0) {
				cases.get(0).setUserId(c.getUserId());
				cases.get(0).setCaseName(c.getCaseName());
				cases.get(0).setType(c.getType());
				cases.get(0).setStatus(c.getStatus());
				cases.get(0).setPassRate(c.getPassRate());
			}
		}else {
			session.save(c);
		}
		session.flush();
		return c.getId();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Integer Delete(Integer caseid,Integer userid) {
		Session session = sessionFactory.getCurrentSession();

		List<Cases> cases = session.createQuery("from Cases where id = :id and userid = :userid")
				.setParameter("id", caseid).setParameter("userid", userid).list();
		if(cases.size()>0) {
			session.delete(cases.get(0));
		}else {
			return 0;
		}
		System.out.println("删除cases");
		session.flush();
		return 1;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Cases> getCase(Integer caseid,Integer userid) {
		Session session = sessionFactory.getCurrentSession();

		List<Cases> cases = session.createQuery("from Cases where id = :id and userid = :userid")
				.setParameter("id", caseid).setParameter("userid", userid).list();
		if(cases.size()>0) {
			return cases;
		}else {
			return null;
		}
	}
	
	@Override
	public Integer getCaseCount() {
		Session session = sessionFactory.getCurrentSession();
		return Integer.parseInt(session.createSQLQuery("select count(id) from cases").list().get(0).toString());
	}
}

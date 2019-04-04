package com.testing.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.testing.common.MyLogger;
import com.testing.entity.Cases;
import com.testing.entity.ResDetail;

@Repository
public class ResDetailDao implements IResDetailDao {

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
	public void Updater(ResDetail r) {
		Session session = sessionFactory.getCurrentSession();
		MyLogger.logger.info("ResDetail");
		if (r.getId() != null) {
			List<ResDetail> ress = session.createQuery("from ResDetail where id = :id").setParameter("id", r.getId())
					.list();
			if (ress.size() > 0) {
				ress.get(0).setActual(r.getActual());
				ress.get(0).setResId(r.getResId());
				ress.get(0).setCaseName(r.getCaseName());
				ress.get(0).setType(r.getType());
				ress.get(0).setKeyWord(r.getKeyWord());
				ress.get(0).setParam1(r.getParam1());
				ress.get(0).setParam2(r.getParam2());
				ress.get(0).setParam3(r.getParam3());
				ress.get(0).setStatus(r.getStatus());
				session.flush();
			} else {
				return;
			}
		} else {
			session.save(r);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer Delete(Integer caseid) {
		Session session = sessionFactory.getCurrentSession();

		List<Cases> cases = session.createQuery("from ResDetail where caseid = :caseid").setParameter("caseid", caseid)
				.list();
		if (cases.size() > 0) {
			for (int i = 0; i < cases.size(); i++) {
				session.delete(cases.get(i));
			}
		} else {
			return 0;
		}
		session.flush();
		return 1;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ResDetail> getDetails(Integer caseid) {
		Session session = sessionFactory.getCurrentSession();
		List<ResDetail> details = session.createQuery("from ResDetail where caseid = :caseid")
				.setParameter("caseid", 1).list();
		if (details.size() > 0) {
			return details;
		} else {
			return null;
		}
	}

}

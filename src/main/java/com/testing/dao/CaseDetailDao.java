package com.testing.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.testing.common.MyLogger;
import com.testing.entity.CaseDetail;
import com.testing.entity.Cases;

@Repository
public class CaseDetailDao implements ICaseDetailDao {

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
	public void Updater(CaseDetail c) {
		Session session = sessionFactory.getCurrentSession();
		MyLogger.logger.info("Casedetail");
		if (c.getId() != null) {
			List<CaseDetail> cases = session.createQuery("from CaseDetail where id = :id").setParameter("id", c.getId())
					.list();
			if (cases.size() > 0) {
				cases.get(0).setActual(c.getActual());
				cases.get(0).setCaseId(c.getCaseId());
				cases.get(0).setCaseName(c.getCaseName());
				cases.get(0).setType(c.getType());
				cases.get(0).setKeyWord(c.getKeyWord());
				cases.get(0).setParam1(c.getParam1());
				cases.get(0).setParam2(c.getParam2());
				cases.get(0).setParam3(c.getParam3());
				cases.get(0).setStatus(c.getStatus());
				session.flush();
			} else {
				return;
			}
		} else {
			session.save(c);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer Delete(Integer caseid) {
		Session session = sessionFactory.getCurrentSession();

		List<Cases> cases = session.createQuery("from CaseDetail where caseid = :caseid").setParameter("caseid", caseid)
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
	public List<CaseDetail> getDetails(Integer caseid) {
		Session session = sessionFactory.getCurrentSession();
		List<CaseDetail> details = session.createQuery("from CaseDetail where caseid = :caseid")
				.setParameter("caseid", 1).list();
		if (details.size() > 0) {
			return details;
		} else {
			return null;
		}
	}

}

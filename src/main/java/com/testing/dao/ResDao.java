package com.testing.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.testing.common.MyLogger;
import com.testing.entity.Cases;
import com.testing.entity.Res;

@Repository
public class ResDao implements IResDao {

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
	public void Updater(Res r) {
		Session session = sessionFactory.getCurrentSession();
		MyLogger.logger.info("Res");
		if (r.getId()!=null) {
			List<Res> ress = session.createQuery("from Res where id = :id")
					.setParameter("id", r.getId()).list();
			if (ress.size() > 0) {
				ress.get(0).setCaseId(r.getId());
				ress.get(0).setCaseId(r.getCaseId());
				ress.get(0).setUserId(r.getUserId());
				ress.get(0).setCount(r.getCount());
				ress.get(0).setPass(r.getPass());
				ress.get(0).setFail(r.getFail());
				ress.get(0).setStartTime(r.getStartTime());
				ress.get(0).setEndTime(r.getEndTime());
				ress.get(0).setStatus(r.getStatus());
				session.flush();
			} else {
				return ;
			}
		}else {
			session.save(r);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Integer Delete(Integer caseid) {
		Session session = sessionFactory.getCurrentSession();

		List<Cases> ress = session.createQuery("from Res where caseid = :caseid")
				.setParameter("caseid", caseid).list();
		if(ress.size()>0) {
			for(int i=0;i<ress.size();i++) {
				session.delete(ress.get(i));
			}
		}else {
			return 0;
		}
		session.flush();
		return 1;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Cases> getRes(Integer caseid,Integer userid) {
		Session session = sessionFactory.getCurrentSession();

		List<Cases> cases = session.createQuery("from Res where id = :id and userid = :userid")
				.setParameter("id", caseid).setParameter("userid", userid).list();
		if(cases.size()>0) {
			return cases;
		}else {
			return null;
		}
	}
	
	@Override
	public Integer getResCount() {
		Session session = sessionFactory.getCurrentSession();
		return Integer.parseInt(session.createSQLQuery("select count(id) from res").list().get(0).toString());
	}
}

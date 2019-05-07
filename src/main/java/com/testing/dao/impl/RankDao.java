package com.testing.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.testing.dao.IRankDao;
import com.testing.entity.Rank;

@Repository
public class RankDao implements IRankDao {

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
	public List<Rank> RankList() {
		// TODO Auto-generated method stub
		List<Rank> ranks = sessionFactory.getCurrentSession().createQuery("from Rank").list();
		System.out.println(ranks);
		if (ranks.size() > 0) {
			return ranks;
		} else {
			return null;
		}
	}
}

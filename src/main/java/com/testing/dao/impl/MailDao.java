package com.testing.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.testing.common.MyLogger;
import com.testing.dao.IMailDao;
import com.testing.entity.Cases;
import com.testing.entity.Mail;

@Repository
public class MailDao implements IMailDao {

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
	public Integer Updater(Mail m) {
		Session session = sessionFactory.getCurrentSession();
		MyLogger.logger.info("Casedetail");
		if (m.getId() != null) {
			List<Mail> mails = session.createQuery("from Mail where id = :id and userid = :userid").setParameter("id", m.getId()).setParameter("userid", m.getUserId()).list();
			if (mails.size() > 0) {
				if(m.getMName()!=null) {
					mails.get(0).setMName(m.getMName());
				}
				if(m.getMHtml()!=null) {
					mails.get(0).setMHtml(m.getMHtml());
				}
				session.update(mails.get(0));
				session.flush();
				//表示更新成功
				return 0;
			} else {
				if (m.getUserId() != null) {
					List<Mail> mail1 = session.createQuery("from Mail where userid = :userid").setParameter("userid", m.getUserId()).list();
					if (mail1.size() > 2) {
						//1表示超过3个
						return 1;
					} else {
						if(m.getUserId() == 1) {
							m.setType(1);
						}else {
							m.setType(0);
						}
						session.save(m);
						session.flush();
						//2表示新增成功
						return 2;
					}
				}
				//添加数据库异常
				return -1;
			}
		} else {
			if (m.getUserId() != null) {
				List<Mail> mails = session.createQuery("from Mail where userid = :userid").setParameter("userid", m.getUserId()).list();
				if (mails.size() > 2) {
					//1表示超过3个
					return 1;
				} else {
					if(m.getUserId() == 1) {
						m.setType(1);
					}else {
						m.setType(0);
					}
					session.save(m);
					session.flush();
					//2表示新增成功
					return 2;
				}
			} 
			return 0;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer Delete(Integer id, Integer userid) {
		Session session = sessionFactory.getCurrentSession();

		List<Cases> mails = session.createQuery("from Mail where id = :id and userid= :userid").setParameter("id", id)
				.setParameter("userid", userid).list();
		if (mails.size() > 0) {
			for (int i = 0; i < mails.size(); i++) {
				session.delete(mails.get(i));
			}
			session.flush();
		} else {
			return 0;
		}
		return 1;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Mail> getMails(Integer userid) {
		Session session = sessionFactory.getCurrentSession();
		List<Mail> mails = session.createQuery("from Mail where userid = :userid")
				.setParameter("userid", userid).list();
		if (mails.size() > 0) {
			return mails;
		} else {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Mail> getDefaults() {
		Session session = sessionFactory.getCurrentSession();
		List<Mail> mails = session.createQuery("from Mail where type = 1").list();
		if (mails.size() > 0) {
			return mails;
		} else {
			return null;
		}
	}

}

package com.testing.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testing.dao.impl.MailDao;
import com.testing.entity.Mail;
import com.testing.service.IMailManager;

@Service("mailService")
public class MailManager implements IMailManager {

	@Autowired
	private MailDao mailDao;

	public MailDao getMailDao() {
		return mailDao;
	}

	public void setMailDao(MailDao mailDao) {
		this.mailDao = mailDao;
	}


	@Override
	public Integer Updater(Mail mail) {
		// TODO Auto-generated method stub
		return mailDao.Updater(mail);
	}
	
	@Override
	public Integer Delete(Integer id,Integer userid) {
		// TODO Auto-generated method stub
		return mailDao.Delete(id, userid);
	}
	
	@Override
	public List<Mail> getMails(Integer userid) {
		return mailDao.getMails(userid);
	}
	
	@Override
	public List<Mail> getDefaults() {
		return mailDao.getDefaults();
	}

}

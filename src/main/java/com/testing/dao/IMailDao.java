package com.testing.dao;

import java.util.List;

import com.testing.entity.Mail;

public interface IMailDao {

	Integer Updater(Mail m);
	Integer Delete(Integer id, Integer userid);
	List<Mail> getMails(Integer userid);
	List<Mail> getDefaults();

}

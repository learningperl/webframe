package com.testing.service;

import java.util.List;

import com.testing.entity.Mail;

public interface IMailManager {


	Integer Updater(Mail mail);

	Integer Delete(Integer id, Integer userid);

	List<Mail> getMails(Integer userid);

	List<Mail> getDefaults();


}

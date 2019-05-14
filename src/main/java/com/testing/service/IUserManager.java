package com.testing.service;

import com.testing.entity.User;

public interface IUserManager {
	 //登录接口
	User Login(User user) throws Exception;

	Integer Register(User user) throws Exception;

	void Updater(User user);

	User UserInfo(Integer userid);

	Integer getUserCount();

	void UpdateMailSeting(Integer userid, Integer mailid); 
}

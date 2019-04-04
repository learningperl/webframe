package com.testing.dao;

import com.testing.entity.User;

public interface IUserDao {

	User Login(String username, String password);

	void Register(User user) throws Exception;

	void Updater(User user);

	User UserInfo(Integer userid);

	Integer getUserCount();
}

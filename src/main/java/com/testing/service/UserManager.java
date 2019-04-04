package com.testing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testing.dao.UserDao;
import com.testing.entity.User;

@Service("userService")
public class UserManager implements IUserManager {

	@Autowired
	private UserDao userDao;

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public User Login(User user) throws Exception {
		// TODO Auto-generated method stub
		return userDao.Login(user.getUserName(),user.getPwd());
	}
	
	@Override
	public void Register(User user) throws Exception {
		// TODO Auto-generated method stub
		userDao.Register(user);
	}

	@Override
	public void Updater(User user) {
		// TODO Auto-generated method stub
		userDao.Updater(user);
	}

	@Override
	public User UserInfo(Integer userid) {
		// TODO Auto-generated method stub
		return userDao.UserInfo(userid);
	}
	
	@Override
	public Integer getUserCount() {
		// TODO Auto-generated method stub
		return userDao.getUserCount();
	}

}

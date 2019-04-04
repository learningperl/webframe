package com.testing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testing.dao.ResDao;
import com.testing.entity.Res;

@Service("resService")
public class ResManager implements IResManager {

	@Autowired
	private ResDao resDao;

	public ResDao getUserDao() {
		return resDao;
	}

	public void setUserDao(ResDao resDao) {
		this.resDao = resDao;
	}

	@Override
	public void Updater(Res res) {
		// TODO Auto-generated method stub
		resDao.Updater(res);
	}
	
	@Override
	public Integer Delete(Integer caseid) {
		// TODO Auto-generated method stub
		return resDao.Delete(caseid);
	}
	
	@Override
	public Integer getResCount() {
		// TODO Auto-generated method stub
		return resDao.getResCount();
	}

}

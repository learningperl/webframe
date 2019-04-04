package com.testing.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testing.dao.ResDao;
import com.testing.entity.Cases;
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
	public Integer Delete(Integer resid,Integer userid) {
		// TODO Auto-generated method stub
		return resDao.Delete(resid,userid);
	}
	
	@Override
	public Integer getResCount() {
		// TODO Auto-generated method stub
		return resDao.getResCount();
	}
	
	@Override
	public List<Res> getRess(Integer userid) {
		return resDao.getRess(userid);
	}

}

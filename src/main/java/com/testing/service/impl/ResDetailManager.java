package com.testing.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testing.dao.impl.ResDao;
import com.testing.dao.impl.ResDetailDao;
import com.testing.entity.ResDetail;
import com.testing.service.IResDetailManager;

@Service("resDetailService")
public class ResDetailManager implements IResDetailManager {

	@Autowired
	private ResDetailDao resDetailDao;
	@Autowired
	private ResDao resDao;

	public ResDetailDao getUserDao() {
		return resDetailDao;
	}

	public void setUserDao(ResDetailDao resDetailDao) {
		this.resDetailDao = resDetailDao;
	}


	@Override
	public void UpdaterAll(List<ResDetail> res) {
		// TODO Auto-generated method stub
		resDetailDao.Updater(res.get(0));
	}
	
	@Override
	public void Updater(ResDetail res) {
		// TODO Auto-generated method stub
		resDetailDao.Updater(res);
	}
	
	@Override
	public Integer Delete(Integer resid) {
		// TODO Auto-generated method stub
		return resDetailDao.Delete(resid);
	}
	
	@Override
	public List<ResDetail> getDetails(String userid,Integer resid) {
		if(resDao.getRes(resid, Integer.parseInt(userid))!=null)
			return resDetailDao.getDetails(resid);
		else
			return null;
	}

}

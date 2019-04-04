package com.testing.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testing.dao.CasesDao;
import com.testing.entity.Cases;

@Service("casesService")
public class CasesManager implements ICasesManager {

	@Autowired
	private CasesDao casesDao;

	public CasesDao getUserDao() {
		return casesDao;
	}

	public void setUserDao(CasesDao casesDao) {
		this.casesDao = casesDao;
	}

	@Override
	public List<Cases> getCases(String userid) {
		// TODO Auto-generated method stub
		return casesDao.getCases(userid);
	}
	
	@Override
	public void UpdaterAll(List<Cases> cases) {
		// TODO Auto-generated method stub
		casesDao.Updater(cases.get(0));
	}
	
	@Override
	public Integer Updater(Cases cases) {
		// TODO Auto-generated method stub
		return casesDao.Updater(cases);
	}
	
	@Override
	public Integer Delete(Integer caseid,Integer userid) {
		// TODO Auto-generated method stub
		return casesDao.Delete(caseid,userid);
	}
	
	@Override
	public Integer getCaseCount() {
		// TODO Auto-generated method stub
		return casesDao.getCaseCount();
	}

}

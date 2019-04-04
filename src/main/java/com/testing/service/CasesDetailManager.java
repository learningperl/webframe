package com.testing.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testing.dao.CaseDetailDao;
import com.testing.dao.CasesDao;
import com.testing.entity.CaseDetail;

@Service("caseDetailService")
public class CasesDetailManager implements ICasesDetailManager {

	@Autowired
	private CaseDetailDao caseDetailDao;
	@Autowired
	private CasesDao casesDao;

	public CaseDetailDao getUserDao() {
		return caseDetailDao;
	}

	public void setUserDao(CaseDetailDao caseDetailDao) {
		this.caseDetailDao = caseDetailDao;
	}


	@Override
	public void UpdaterAll(List<CaseDetail> cases) {
		// TODO Auto-generated method stub
		caseDetailDao.Updater(cases.get(0));
	}
	
	@Override
	public void Updater(CaseDetail cases) {
		// TODO Auto-generated method stub
		caseDetailDao.Updater(cases);
	}
	
	@Override
	public Integer Delete(Integer caseid) {
		// TODO Auto-generated method stub
		return caseDetailDao.Delete(caseid);
	}
	
	@Override
	public List<CaseDetail> getDetails(String userid,Integer caseid) {
		if(casesDao.getCase(caseid, Integer.parseInt(userid))!=null)
			return caseDetailDao.getDetails(caseid);
		else
			return null;
	}

}

package com.testing.dao;

import java.util.List;

import com.testing.entity.CaseDetail;

public interface ICaseDetailDao {

	void Updater(CaseDetail c);

	Integer Delete(Integer caseid);


	List<CaseDetail> getDetails(Integer caseid);

}

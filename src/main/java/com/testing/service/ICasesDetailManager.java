package com.testing.service;

import java.util.List;

import com.testing.entity.CaseDetail;

public interface ICasesDetailManager {

	void UpdaterAll(List<CaseDetail> cases);

	void Updater(CaseDetail cases);

	Integer Delete(Integer caseid);

	List<CaseDetail> getDetails(String userid, Integer caseid);


}

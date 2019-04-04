package com.testing.service;

import java.util.List;

import com.testing.entity.Cases;

public interface ICasesManager {

	void UpdaterAll(List<Cases> cases);

	Integer Updater(Cases cases);

	List<Cases> getCases(String userid);

	Integer Delete(Integer caseid, Integer userid);

	Integer getCaseCount();

}

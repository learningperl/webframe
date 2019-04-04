package com.testing.dao;

import java.util.List;

import com.testing.entity.Cases;

public interface ICasesDao {

	Integer Updater(Cases c);

	List<Cases> getCases(String userid);

	Integer Delete(Integer caseid, Integer userid);

	List<Cases> getCase(Integer caseid, Integer userid);

	Integer getCaseCount();


}

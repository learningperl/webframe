package com.testing.dao;

import java.util.List;

import com.testing.entity.ResDetail;

public interface IResDetailDao {

	void Updater(ResDetail c);

	Integer Delete(Integer caseid);


	List<ResDetail> getDetails(Integer caseid);

}

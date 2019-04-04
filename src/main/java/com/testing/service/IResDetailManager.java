package com.testing.service;

import java.util.List;

import com.testing.entity.ResDetail;

public interface IResDetailManager {

	void UpdaterAll(List<ResDetail> ress);

	void Updater(ResDetail res);

	Integer Delete(Integer resid);

	List<ResDetail> getDetails(String userid, Integer caseid);


}

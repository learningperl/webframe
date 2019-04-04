package com.testing.dao;

import java.util.List;

import com.testing.entity.Cases;
import com.testing.entity.Res;

public interface IResDao {

	void Updater(Res r);

	Integer Delete(Integer caseid);

	List<Cases> getRes(Integer caseid, Integer userid);

	Integer getResCount();

}

package com.testing.dao;

import java.util.List;

import com.testing.entity.Cases;
import com.testing.entity.Res;

public interface IResDao {

	void Updater(Res r);

	Integer Delete(Integer caseid);

	List<Res> getRes(Integer caseid, Integer userid);

	Integer getResCount();

	List<Res> getRess(Integer userid);

	Integer Delete(Integer caseid, Integer userid);

}

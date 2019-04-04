package com.testing.service;

import java.util.List;

import com.testing.entity.Cases;
import com.testing.entity.Res;

public interface IResManager {

	void Updater(Res res);

	Integer Delete(Integer caseid);

	Integer getResCount();

	List<Res> getRess(Integer userid);

	Integer Delete(Integer resid, Integer userid);

}

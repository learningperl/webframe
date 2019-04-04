package com.testing.service;

import com.testing.entity.Res;

public interface IResManager {

	void Updater(Res res);

	Integer Delete(Integer caseid);

	Integer getResCount();

}

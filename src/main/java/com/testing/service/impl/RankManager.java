package com.testing.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testing.dao.impl.RankDao;
import com.testing.entity.Rank;
import com.testing.service.IRankManager;

@Service("rankService")
public class RankManager implements IRankManager {

	@Autowired
	private RankDao rankDao;

	public RankDao getUserDao() {
		return rankDao;
	}

	public void setUserDao(RankDao rankDao) {
		this.rankDao = rankDao;
	}

	@Override
	public List<Rank> RankList() {
		// TODO Auto-generated method stub
		return rankDao.RankList();
	}

}

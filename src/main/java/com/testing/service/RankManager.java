package com.testing.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testing.dao.RankDao;
import com.testing.dao.UserDao;
import com.testing.entity.Rank;
import com.testing.entity.User;

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

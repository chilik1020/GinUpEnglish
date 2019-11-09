package com.chilik1020.grammartestsapp.model.repository;

import com.chilik1020.grammartestsapp.model.dao.UserStatDao;
import com.chilik1020.grammartestsapp.model.entities.UserStat;

import io.reactivex.Completable;
import io.reactivex.Single;

public class UserStatRepositoryImpl implements UserStatRepository {

    private UserStatDao userStatDao;

    public UserStatRepositoryImpl(UserStatDao userStatDao) {
        this.userStatDao = userStatDao;
    }

    @Override
    public Completable update(UserStat userStat) {
        return userStatDao.update(userStat);
    }

    @Override
    public Single<UserStat> getUserStatById(Long id) {
        return userStatDao.getUserStatById(id);
    }
}

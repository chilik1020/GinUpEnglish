package com.chilik1020.grammartestsapp.model.repository;

import com.chilik1020.grammartestsapp.model.entities.UserStat;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface UserStatRepository {

    Completable update(UserStat userStat);

    Single<UserStat> getUserStatById(Long id);
}

package com.chilik1020.grammartestsapp.model.repository;

import com.chilik1020.grammartestsapp.model.entities.Purchase;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface PurchaseRepository {
    
    Completable update(Purchase p);

    Single<Purchase> getByName(String name);
}

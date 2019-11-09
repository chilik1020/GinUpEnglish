package com.chilik1020.grammartestsapp.model.repository;

import com.chilik1020.grammartestsapp.model.dao.PurchaseDao;
import com.chilik1020.grammartestsapp.model.entities.Purchase;

import io.reactivex.Completable;
import io.reactivex.Single;

public class PurchaseRepositoryImpl implements PurchaseRepository {

    private PurchaseDao purchaseDao;

    public PurchaseRepositoryImpl(PurchaseDao purchaseDao) {
        this.purchaseDao = purchaseDao;
    }

    @Override
    public Completable update(Purchase p) {
        return purchaseDao.update(p);
    }

    @Override
    public Single<Purchase> getByName(String name) {
        return purchaseDao.getByName(name);
    }
}

package com.chilik1020.grammartestsapp.abstracts;

public interface MvpPresenter<V extends MvpView> {
    public void attachView(V view);
    public void detachView();
}

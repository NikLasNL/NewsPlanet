package com.example.admin.projectniklas.presenters;


import android.database.sqlite.SQLiteConstraintException;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.admin.projectniklas.NewsPlanetApplication;
import com.example.admin.projectniklas.models.Subscribe;
import com.example.admin.projectniklas.models.SubscribeDao;
import com.example.admin.projectniklas.views.SubscribeView;

import java.util.List;

@InjectViewState

public class SubscribePresenter extends MvpPresenter<SubscribeView> {

    private SubscribeDao subDao = NewsPlanetApplication.getDaoSession().getSubscribeDao();

    public SubscribePresenter() { getSubscribes(); }

    public void saveSubscribe(String name, String url){
        Subscribe sub = new Subscribe(name,url);
        try {
            subDao.save(sub);
        }
        catch (SQLiteConstraintException e){
            getViewState().onErrorReceived();
        }
        getSubscribes();
    }

    private void getSubscribes() {
        List<Subscribe> subs = subDao.queryBuilder().orderAsc(SubscribeDao.Properties.Name).list();
        getViewState().getAllSubscribe(subs);
    }
    public void deleteSubscribe(){

    }
}

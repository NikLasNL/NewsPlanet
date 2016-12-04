package com.example.admin.projectniklas.presenters;


import android.database.sqlite.SQLiteConstraintException;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.einmalfel.earl.Feed;
import com.example.admin.projectniklas.NewsPlanetApplication;
import com.example.admin.projectniklas.RssAsyncTask;
import com.example.admin.projectniklas.models.Subscribe;
import com.example.admin.projectniklas.models.SubscribeDao;
import com.example.admin.projectniklas.views.SubscribeView;

import java.util.ArrayList;
import java.util.List;

@InjectViewState

public class SubscribePresenter extends MvpPresenter<SubscribeView> {

    private RssAsyncTask task;

    private SubscribeDao subDao = NewsPlanetApplication.getDaoSession().getSubscribeDao();

    private String url;

    public SubscribePresenter() {
        getSubscribes();
    }


    public void saveSubscribe(String url) {
        createTask();
        this.url = url;
        task.execute(url);

    }


    private void getSubscribes() {
        List<Subscribe> subs = subDao.queryBuilder().orderAsc(SubscribeDao.Properties.Name).list();
        getViewState().getAllSubscribe(subs);
    }

    public void deleteSubscribe() {

    }

    private void createTask() {
        task = new RssAsyncTask(new RssAsyncTask.OnPostResultListener() {
            @Override
            public void onPostExecute(ArrayList<Feed> feeds) {
                try {
                    Subscribe sub = new Subscribe(feeds.get(0).getTitle(),url);
                    subDao.save(sub);
                    getSubscribes();
                }
                catch (SQLiteConstraintException|NullPointerException e ){
                    if(e instanceof NullPointerException) getViewState().onErrorReceived("Неверный URL");
                    else getViewState().onErrorReceived("Такой канал уже добавлен");
                }
            }
        });
    }


}

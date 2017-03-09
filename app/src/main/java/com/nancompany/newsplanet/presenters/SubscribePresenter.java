package com.nancompany.newsplanet.presenters;


import android.database.sqlite.SQLiteConstraintException;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.einmalfel.earl.Feed;
import com.nancompany.newsplanet.NewsPlanetApplication;
import com.nancompany.newsplanet.RssAsyncTask;
import com.nancompany.newsplanet.models.Subscribe;
import com.nancompany.newsplanet.models.SubscribeDao;
import com.nancompany.newsplanet.views.SubscribeView;

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

    //Это по поводу удаления подписок.
    public void deleteSubscribe(String url) {
        Subscribe sub = subDao.queryBuilder().where(SubscribeDao.Properties.Url.eq(url)).unique();
        sub.delete();
        getSubscribes();
    }

    private void createTask() {
        task = new RssAsyncTask(new RssAsyncTask.OnPostResultListener() {
            @Override
            public void onPostExecute(ArrayList<Feed> feeds) {
                try {
                    if (!feeds.isEmpty()) {
                        Subscribe sub = new Subscribe(feeds.get(0).getTitle(), url);
                        subDao.save(sub);
                        getSubscribes();
                    }
                    else getViewState().onErrorReceived("Неверный URL");
                }
                catch (SQLiteConstraintException e){
                    getViewState().onErrorReceived("Такой канал уже добавлен");

                }
            }
        });
    }


}

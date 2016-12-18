package com.example.admin.projectniklas.presenters;


import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import com.einmalfel.earl.Feed;
import com.einmalfel.earl.Item;
import com.example.admin.projectniklas.NewsPlanetApplication;
import com.example.admin.projectniklas.RssAsyncTask;
import com.example.admin.projectniklas.models.News;
import com.example.admin.projectniklas.models.Subscribe;
import com.example.admin.projectniklas.models.SubscribeDao;
import com.example.admin.projectniklas.views.NewsView;

import java.util.ArrayList;

import java.util.Collections;

import java.util.List;


@InjectViewState
public class NewsPresenter extends MvpPresenter<NewsView> {

    private SubscribeDao dao = NewsPlanetApplication.getDaoSession().getSubscribeDao();

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        String[] urls = getRssUrls();
        RssAsyncTask task = new RssAsyncTask(new RssAsyncTask.OnPostResultListener() {
            ArrayList<News> news = new ArrayList();

            @Override
            public void onPostExecute(ArrayList<Feed> feeds) {
                for (Feed feed : feeds) {
                    for (Item item : feed.getItems()) {
                        String imgUrl = "placeholder";
                        if (!item.getEnclosures().isEmpty())
                            imgUrl = item.getEnclosures().get(0).getLink();
                        news.add(new News(item.getLink(),feed.getTitle(), item.getTitle().trim(), item.getPublicationDate(), imgUrl));
                    }
                }

                Collections.sort(news);
                getViewState().onDataReceived(news);
            }
        });
        task.execute(urls);


    }

    private String[] getRssUrls() {
        ArrayList<String> urls = new ArrayList<>();
        List<Subscribe> subs = dao.queryBuilder().orderAsc(SubscribeDao.Properties.Name).list();
        for (Subscribe sub : subs) {
            urls.add(sub.getUrl());
        }
        String[] urlsArr = new String[urls.size()];
        urlsArr = urls.toArray(urlsArr);
        return urlsArr;


    }
}

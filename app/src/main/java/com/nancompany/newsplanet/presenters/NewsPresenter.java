package com.nancompany.newsplanet.presenters;


import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import com.einmalfel.earl.Feed;
import com.einmalfel.earl.Item;
import com.nancompany.newsplanet.NetworkCheck;
import com.nancompany.newsplanet.NewsPlanetApplication;
import com.nancompany.newsplanet.RssAsyncTask;
import com.nancompany.newsplanet.models.News;
import com.nancompany.newsplanet.models.NewsDao;
import com.nancompany.newsplanet.models.Subscribe;
import com.nancompany.newsplanet.models.SubscribeDao;
import com.nancompany.newsplanet.views.NewsView;

import java.util.ArrayList;

import java.util.Collections;

import java.util.List;


@InjectViewState
public class NewsPresenter extends MvpPresenter<NewsView> {

    private SubscribeDao subDao = NewsPlanetApplication.getDaoSession().getSubscribeDao();
    private NewsDao newsDao = NewsPlanetApplication.getDaoSession().getNewsDao();

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        updateNews();
    }

    public void updateNews() {
        if(NetworkCheck.isNetworkAvailable()) {
            String[] urls = getRssUrls();
            RssAsyncTask task = new RssAsyncTask(new RssAsyncTask.OnPostResultListener() {

                @Override
                public void onPostExecute(ArrayList<Feed> feeds) {
                    ArrayList<News> news = new ArrayList();
                    for (Feed feed : feeds) {
                        for (Item item : feed.getItems()) {
                            String imgUrl = "placeholder";
                            if (!item.getEnclosures().isEmpty())
                                imgUrl = item.getEnclosures().get(0).getLink();
                            news.add(new News(item.getLink(), feed.getTitle(), item.getTitle().trim(), item.getPublicationDate(), imgUrl));
                        }
                    }

                    Collections.sort(news);
                    if (!news.isEmpty()){
                        newsDao.deleteAll();
                        newsDao.saveInTx(news);
                    }

                    getViewState().onDataReceived(news);
                }
            });
            task.execute(urls);
        }
        else     {
            List<News> news = newsDao.queryBuilder().orderDesc(NewsDao.Properties.Date).list();
            ArrayList<News> a = new ArrayList<>();
            a.addAll(news);
            getViewState().onDataReceived(a);
        }
    }

    private String[] getRssUrls() {
        ArrayList<String> urls = new ArrayList<>();
        List<Subscribe> subs = subDao.queryBuilder().orderAsc(SubscribeDao.Properties.Name).list();
        for (Subscribe sub : subs) {
            urls.add(sub.getUrl());
        }
        String[] urlsArr = new String[urls.size()];
        urlsArr = urls.toArray(urlsArr);
        return urlsArr;


    }
}

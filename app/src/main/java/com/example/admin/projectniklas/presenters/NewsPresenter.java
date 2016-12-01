package com.example.admin.projectniklas.presenters;


import android.os.AsyncTask;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.einmalfel.earl.EarlParser;
import com.einmalfel.earl.Feed;
import com.einmalfel.earl.Item;
import com.example.admin.projectniklas.models.News;
import com.example.admin.projectniklas.views.NewsView;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.zip.DataFormatException;

@InjectViewState
public class NewsPresenter extends MvpPresenter<NewsView> {

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        String[] urls = {"https://meduza.io/rss/all","https://lenta.ru/rss"};
        RssAsyncTask task = new RssAsyncTask();
        task.execute (urls);
    }

    private class RssAsyncTask extends AsyncTask<String, Void, ArrayList<News>> {

        @Override
        protected ArrayList<News> doInBackground(String... strings) {
            ArrayList<News> news = new ArrayList<>();
            for (String s : strings)
                try {
                    InputStream iStream = new URL(s).openConnection().getInputStream();
                    Feed feed = EarlParser.parseOrThrow(iStream, 0);
                    for (Item item : feed.getItems()) {
                        news.add(new News(feed.getTitle(),item.getTitle().trim(),item.getPublicationDate(),feed.getImageLink()));
                    }

                } catch (IOException | DataFormatException | XmlPullParserException e) {
                    return null;
                }
            Collections.sort(news, new Comparator<News>() {
                @Override
                public int compare(News news, News t1) {
                    return t1.getDate().compareTo(news.getDate());
                }
            });
            return news;
        }

        @Override
        protected void onPostExecute(ArrayList<News> news) {
            super.onPostExecute(news);
            if(news == null) getViewState().onErrorReceived();
            else getViewState().onDataReceived(news);
        }

    }
}

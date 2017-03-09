package com.nancompany.newsplanet.views;

import com.arellomobile.mvp.MvpView;
import com.nancompany.newsplanet.models.News;

import java.util.ArrayList;


public interface NewsView extends MvpView {

    void onDataReceived(ArrayList<News> news);
    void onErrorReceived();
}

package com.example.admin.projectniklas.views;

import com.arellomobile.mvp.MvpView;
import com.example.admin.projectniklas.models.News;

import java.util.ArrayList;


public interface NewsView extends MvpView {

    void onDataReceived(ArrayList<News> news);
    void onErrorReceived();
}

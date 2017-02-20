package com.example.admin.projectniklas.views;


import com.arellomobile.mvp.MvpView;
import com.example.admin.projectniklas.models.Subscribe;

import java.util.List;

public interface SubscribeView  extends MvpView{


    void getAllSubscribe(List<Subscribe> subs);

    void onErrorReceived(String s);
}

package com.nancompany.newsplanet.views;


import com.arellomobile.mvp.MvpView;
import com.nancompany.newsplanet.models.Subscribe;

import java.util.List;

public interface SubscribeView  extends MvpView{


    void getAllSubscribe(List<Subscribe> subs);

    void onErrorReceived(String s);
}

package com.nancompany.newsplanet.presenters;

import android.content.SharedPreferences;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.nancompany.newsplanet.NewsPlanetApplication;
import com.nancompany.newsplanet.views.SettingsView;

@InjectViewState
public class SettingsPresenter extends MvpPresenter<SettingsView>{
    SharedPreferences sp = NewsPlanetApplication.getSp();

    public void getTimeZone(){
        getViewState().ooTimeZoneReceived(sp.getString("timeZone","GMT+0"));
    }

    public void setTimeZone(String zone){
        sp.edit().putString("timeZone",zone).apply();
    }

}

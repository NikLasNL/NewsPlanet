package com.example.admin.projectniklas.presenters;

import android.content.SharedPreferences;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.admin.projectniklas.NewsPlanetApplication;
import com.example.admin.projectniklas.views.SettingsView;

@InjectViewState
public class SettingsPresenter extends MvpPresenter<SettingsView>{
    SharedPreferences sp = NewsPlanetApplication.getSp();

    public void getTimeZone(){
        getViewState().ooTimeZoneReceived(sp.getString("timeZone","GMT+0"));
    }

    public void setTimeZone(String zone){
        sp.edit().putString("timezone",zone).apply();
    }

}

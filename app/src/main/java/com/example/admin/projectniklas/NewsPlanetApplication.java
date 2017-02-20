package com.example.admin.projectniklas;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;

import com.example.admin.projectniklas.models.DaoMaster;
import com.example.admin.projectniklas.models.DaoSession;

public class NewsPlanetApplication extends Application {

    private static DaoSession daoSession;
    private static SharedPreferences sp;

    @Override
    public void onCreate() {
        super.onCreate();
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this,"newsplanet-db");
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster master = new DaoMaster(db);
        daoSession = master.newSession();

        sp = getSharedPreferences("settings", Context.MODE_PRIVATE);
    }
    public static DaoSession getDaoSession(){
        return daoSession;
    }

    public static SharedPreferences getSp(){
        return sp;
    }
}

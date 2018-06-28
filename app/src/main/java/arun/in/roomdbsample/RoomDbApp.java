package arun.in.roomdbsample;

import android.app.Application;

import arun.in.roomdbsample.localdb.AppDataBase;
import arun.in.roomdbsample.sharedpref.AppStorage;

public class RoomDbApp extends Application
{
    public AppStorage appStorage;

    public AppStorage getAppStorage() {
        return appStorage;
    }

    public void setAppStorage(AppStorage appStorage) {
        this.appStorage = appStorage;
    }

    public AppDataBase getAppDataBase() {
        return appDataBase;
    }

    public void setAppDataBase(AppDataBase appDataBase) {
        this.appDataBase = appDataBase;
    }

    public AppDataBase appDataBase;
    public void onCreate()
    {
        super.onCreate();
        appStorage=new AppStorage(getApplicationContext(),"RoomDbSharedPref");
        appDataBase=AppDataBase.getAppDatabase(getApplicationContext());
    }
}

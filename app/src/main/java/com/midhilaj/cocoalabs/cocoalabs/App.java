package com.midhilaj.cocoalabs.cocoalabs;

import android.content.Context;

import com.orm.SugarApp;
import com.orm.SugarContext;

/**
 * Created by midhilaj on 9/26/18.
 */

public class App extends SugarApp {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        android.support.multidex.MultiDex.install(this);
    }


    @Override
    public void onCreate() {
        super.onCreate();
        //Fabric.with(this, new Crashlytics());
        SugarContext.init(this);
    }

    @Override
    public void onTerminate() {
        SugarContext.terminate();
        super.onTerminate();
    } //you can leave this empty or override methods if you like so the thing is that you need to extend from MultiDexApplication
}
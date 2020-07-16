package org.eldorado.simpleweather;

import android.app.Application;

import org.eldorado.simpleweather.BuildConfig;

import timber.log.Timber;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG)
            Timber.plant(new Timber.DebugTree());
    }

}

package com.example.movielist;

import android.app.Application;
import android.content.Context;

import com.example.movielist.dagger.AppComponent;
import com.example.movielist.dagger.AppModule;
import com.example.movielist.dagger.DaggerAppComponent;
import com.example.movielist.retrofit.RetrofitModule;


public class MoviesListApplication extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .retrofitModule(new RetrofitModule())
                .build();
    }

    public AppComponent getApplicationComponent(){
        return appComponent;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }
}

package com.example.movielist.dagger;

import com.example.movielist.MoviesListApplication;
import com.example.movielist.retrofit.RetrofitModule;

import dagger.Module;

@Module(includes = RetrofitModule.class)
public class AppModule {

    private MoviesListApplication baseApplication;

    public AppModule(MoviesListApplication baseApplication) {
        this.baseApplication = baseApplication;
    }
}

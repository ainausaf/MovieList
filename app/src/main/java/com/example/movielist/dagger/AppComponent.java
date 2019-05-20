package com.example.movielist.dagger;

import com.example.movielist.BaseActivity;
import com.example.movielist.MovieDetails.MovieDetailsModule;
import com.example.movielist.MoviesList.MainActivityModule;
import com.example.movielist.data.Repository;
import com.example.movielist.retrofit.RetrofitModule;
import com.example.movielist.scope.ApplicationScope;

import dagger.Component;
/**
 * Dagger App Component with Application Scope
 */

@ApplicationScope
@Component(modules = {AppModule.class, RetrofitModule.class, MainActivityModule.class, MovieDetailsModule.class})
public interface AppComponent {

    Repository getDataRepository();

    void inject(BaseActivity baseActivity);
}

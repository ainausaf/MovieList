package com.example.movielist.MoviesList;


import com.example.movielist.dagger.AppComponent;
import com.example.movielist.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(
        dependencies = {AppComponent.class},
        modules = {MainActivityModule.class}
)
public interface MainActivityComponent {

    MainActivity inject (MainActivity mainActivity);
}

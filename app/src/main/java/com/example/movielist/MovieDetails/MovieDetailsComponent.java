package com.example.movielist.MovieDetails;

import com.example.movielist.dagger.AppComponent;
import com.example.movielist.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(
        dependencies = {AppComponent.class},
        modules = {MovieDetailsModule.class}
)
public interface MovieDetailsComponent {

    MovieDetailsActivity inject(MovieDetailsActivity movieDetailsActivity);
}

package com.example.movielist.MovieDetails;


import com.example.movielist.data.Repository;
import com.example.movielist.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class MovieDetailsModule {

    MovieDetailsActivity movieDetailsActivity;

    public MovieDetailsModule(MovieDetailsActivity movieDetailsActivity) {
        this.movieDetailsActivity = movieDetailsActivity;
    }

    @Provides
    @ActivityScope
    public MovieDetailsContract.View provideMainActivityView() {
        return this.movieDetailsActivity;
    }

    @Provides
    @ActivityScope
    public MovieDetailsContract.Presenter providePresenter(MovieDetailsContract.View view,
                                                           Repository repository) {
        return new MovieDetailsPresenter(view, repository);
    }
}

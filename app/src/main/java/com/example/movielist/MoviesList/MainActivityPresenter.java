package com.example.movielist.MoviesList;

import com.example.movielist.data.Repository;
import com.example.movielist.model.MoviesData;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MainActivityPresenter implements MainActivityContract.Presenter {

    private Repository repository;


    private MainActivityContract.View view;

    public MainActivityPresenter(MainActivityContract.View view, Repository repository) {
        this.view = view;
        this.repository = repository;
    }

    @Override
    public void onCreate() {
        view.showProgressBar(true);
        getMoviesData();
    }

    //service call is being made to retrieve the movies List
    public void getMoviesData() {
        final Observable<List<MoviesData>> moviesList= repository.getMoviesData();
        moviesList.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(moviesListData -> {
                    view.getMoviesList(moviesListData);
                    view.showProgressBar(false);
                }, throwable -> {
                        throwable.printStackTrace();
                        view.showErrorMessage();
                        view.showProgressBar(false);
                    });
    }
}

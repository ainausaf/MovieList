package com.example.movielist.MovieDetails;

import com.example.movielist.data.Repository;
import com.example.movielist.model.MovieDetail;
import com.example.movielist.model.MoviesData;


import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MovieDetailsPresenter implements MovieDetailsContract.Presenter {

    private Repository repository;

    private MovieDetailsContract.View view;

    private int LRUCacheCapacity = 5;

    private Boolean isMovieExist;

    private MovieDetail movieDetail;

    private LRUCacheusingLinkedHashMap lruCacheusingLinkedHashMap;
    public MovieDetailsPresenter(MovieDetailsContract.View view, Repository repository) {
        this.view = view;
        this.repository = repository;
    }

    @Override
    public void onCreate(MoviesData movie){
        view.showProgressBar(true);
        checkIfIdExistsInCache(movie);
    }

    //check if the movie details data exists in the cache
    private void checkIfIdExistsInCache(MoviesData movie) {
        lruCacheusingLinkedHashMap = LRUCacheusingLinkedHashMap .getInstance(LRUCacheCapacity);
        isMovieExist = lruCacheusingLinkedHashMap.isValid(movie.getId());
        if(isMovieExist){
        movieDetail = (MovieDetail) lruCacheusingLinkedHashMap.find(movie.getId());
            view.initViews(movieDetail);
            view.showProgressBar(false);
        }else{
            getMovieDetails(movie);
        }

    }

    //if not in cache, make service call to retrieve data
    private void getMovieDetails(MoviesData movie) {
        final Observable<MovieDetail> movieDetail = repository.getMovieDetail(movie.getId());
        movieDetail.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(movieDetailsData -> {
                    view.initViews(movieDetailsData);
                    view.showProgressBar(false);
                    lruCacheusingLinkedHashMap.add(movie.getId(),movieDetailsData);
                }, throwable -> {
                    throwable.printStackTrace();
                    view.showErrorMessage();
                    view.showProgressBar(false);
                });
    }
}

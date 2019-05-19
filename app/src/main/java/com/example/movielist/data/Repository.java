package com.example.movielist.data;

import com.example.movielist.model.MovieDetail;
import com.example.movielist.model.MoviesData;

import java.util.List;

import rx.Observable;

public class Repository {

    private DataSource remoteDataSource;

    public Repository(DataSource remoteDataSource) {
        this.remoteDataSource = remoteDataSource;
    }

    //to get the movie List for the first screen
    public Observable<List<MoviesData>> getMoviesData() {

        Observable<List<MoviesData>> observable;

        observable = remoteDataSource.getMoviesData();

        return observable;
    }

    //to get the details of the movie
    public Observable<MovieDetail> getMovieDetail(String movieId) {

        Observable<MovieDetail> observable;

        observable = remoteDataSource.getMovieDetail(movieId);

        return observable;
    }
}

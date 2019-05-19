package com.example.movielist.data;

import com.example.movielist.model.MovieDetail;
import com.example.movielist.model.MoviesData;
import com.example.movielist.retrofit.APIinterface;

import java.util.List;
import java.util.Map;

import rx.Observable;


public class DataSource {

    private APIinterface apiInterface;

    private Map<String, String> queryMap;

    public DataSource(APIinterface apiInterface) {
        this.apiInterface = apiInterface;

    }

    //network call to get the list of movies
    Observable<List<MoviesData>> getMoviesData() {

      return apiInterface.getMoviesListData()
              .flatMap(Observable::just);
    }

    //network call to get the details of the movie
    Observable<MovieDetail> getMovieDetail(String movieId) {

        return apiInterface.getMovieDetails(movieId)
                .flatMap(Observable::just);
    }



}

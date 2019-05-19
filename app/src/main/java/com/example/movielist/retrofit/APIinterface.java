package com.example.movielist.retrofit;

import com.example.movielist.model.MovieDetail;
import com.example.movielist.model.MoviesData;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface APIinterface {

    @GET("/api/movies")
    Observable<List<MoviesData>> getMoviesListData();

   //passing the Id parameter in the request
    @GET("/api/movies/{MovieId}")
    Observable<MovieDetail> getMovieDetails(@Path("MovieId") String id);
}

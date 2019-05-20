package com.example.movielist.MovieDetails;

import com.example.movielist.model.MovieDetail;
import com.example.movielist.model.MoviesData;

public interface MovieDetailsContract {

    public interface View{

        void initViews(MovieDetail movieDetail);
        void showProgressBar(boolean isDisplayProgressBar);

        void showErrorMessage();
    }

    public interface Presenter{

        void onCreate(MoviesData movie);
    }
}

package com.example.movielist.MoviesList;

import com.example.movielist.model.MoviesData;

import java.util.List;

public interface MainActivityContract {

    public interface View{


        void getMoviesList(List<MoviesData> moviesListData);

        void showProgressBar(boolean isDisplayProgressBar);

        void showErrorMessage();

       // void navigateToTutorialDetails(List<TutorialDetail> tutorialDetail);
    }

    public interface Presenter{


        void onCreate();

     //   void getTutorialsData(int videoId);

        void getMoviesData();

    }
}

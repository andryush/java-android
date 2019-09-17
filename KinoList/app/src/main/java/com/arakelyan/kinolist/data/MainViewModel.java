package com.arakelyan.kinolist.data;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainViewModel extends AndroidViewModel {

    private static MovieDatabase database;
    private LiveData<List<Movie>> movies;
    private LiveData<List<FavoriteMovie>> favoriteMovies;

    public MainViewModel(@NonNull Application application) {
        super(application);

        database = MovieDatabase.getInstance(getApplication());
        movies = database.moviesDAO().getAllMovies();
        favoriteMovies = database.moviesDAO().getAllFavoriteMovies();

    }

    public LiveData<List<FavoriteMovie>> getFavoriteMovies() {
        return favoriteMovies;
    }

    public LiveData<List<Movie>> getMovies() {
        return movies;
    }


    //Methods for Movie

    public Movie getMovieById(int id) {

        try {

            return new GetMovieTask().execute(id).get();

        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteAllMovies() {

        new DeleteAllMoviesTask().execute();

    }

    public void insertMovie(Movie movie) {

        new InsertMovieTask().execute(movie);

    }

    public void deleteMovie(Movie movie) {

        new DeleteMovieTask().execute(movie);

    }


    //Methods for favorite

    public FavoriteMovie getFavoriteMovieById(int id) {
        try {

            return new GetFavoriteMovieByIdTask().execute(id).get();

        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void deleteFavoriteMovieTask(FavoriteMovie favoriteMovie) {

            new DeleteFavoriteMovieTask().execute(favoriteMovie);
    }


    public void insertFavoriteMovieTask(FavoriteMovie favoriteMovie) {

        new InsertFavoriteMovieTask().execute(favoriteMovie);

    }





    //Async tasks for movies

    public static class DeleteMovieTask extends AsyncTask<Movie, Void, Void> {
        @Override
        protected Void doInBackground(Movie... movies) {

            if (movies != null && movies.length > 0) {
                database.moviesDAO().deleteMovie(movies[0]);
            }
            return null;
        }
    }


    public static class InsertMovieTask extends AsyncTask<Movie, Void, Void> {
        @Override
        protected Void doInBackground(Movie... movies) {
            if (movies != null && movies.length > 0) {
                database.moviesDAO().insertMovie(movies[0]);
            }
            return null;
        }
    }


    public static class DeleteAllMoviesTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... integers) {

            database.moviesDAO().deleteAllMovies();
            return null;
        }

    }


    public static class GetMovieTask extends AsyncTask<Integer, Void, Movie> {
        @Override
        protected Movie doInBackground(Integer... integers) {

            if (integers != null && integers.length > 0) {
                return database.moviesDAO().getMovieById(integers[0]);
            }

            return null;
        }
    }


    // Async tasks for favoriteMovies

    public static class GetFavoriteMovieByIdTask extends AsyncTask<Integer, Void, FavoriteMovie> {
        @Override
        protected FavoriteMovie doInBackground(Integer... integers) {

            if (integers != null && integers.length > 0) {
                return database.moviesDAO().getFavoriteMovieById(integers[0]);
            }

            return null;
        }
    }

    public static class DeleteFavoriteMovieTask extends AsyncTask<FavoriteMovie, Void, Void> {
        @Override
        protected Void doInBackground(FavoriteMovie... favoriteMovies) {

            if (favoriteMovies != null && favoriteMovies.length > 0) {
                database.moviesDAO().deleteFavoriteMovie(favoriteMovies[0]);
            }

            return null;
        }
    }

    public static class InsertFavoriteMovieTask extends AsyncTask<FavoriteMovie, Void, Void> {
        @Override
        protected Void doInBackground(FavoriteMovie... favoriteMovies) {

            if (favoriteMovies != null && favoriteMovies.length > 0) {
                database.moviesDAO().insertFavoriteMovie(favoriteMovies[0]);
            }

            return null;
        }
    }

}

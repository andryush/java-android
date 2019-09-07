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

    public MainViewModel(@NonNull Application application) {
        super(application);

        database = MovieDatabase.getInstance(getApplication());
        movies = database.moviesDAO().getAllMovies();

    }

    public LiveData<List<Movie>> getMovies() {
        return movies;
    }

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
        protected Void doInBackground(Void... voids) {

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
}

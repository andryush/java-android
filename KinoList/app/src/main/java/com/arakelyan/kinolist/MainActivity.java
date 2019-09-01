package com.arakelyan.kinolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.arakelyan.kinolist.adapter.MovieAdapter;
import com.arakelyan.kinolist.data.Movie;
import com.arakelyan.kinolist.utils.JSONUtils;
import com.arakelyan.kinolist.utils.NetworkUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewPosters;
    private MovieAdapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewPosters = findViewById(R.id.rv_posters);
        recyclerViewPosters.setLayoutManager(new GridLayoutManager(this, 2));

        movieAdapter = new MovieAdapter();

        JSONObject jsonObject = NetworkUtils.getJSONFromNetwork(NetworkUtils.POPULARITY, 1);

        ArrayList<Movie> moviesFromJSON = JSONUtils.getMoviesFromJSON(jsonObject);

        movieAdapter.setMovies(moviesFromJSON);

        recyclerViewPosters.setAdapter(movieAdapter);

    }
}

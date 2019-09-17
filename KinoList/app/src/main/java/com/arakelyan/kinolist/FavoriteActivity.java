package com.arakelyan.kinolist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.arakelyan.kinolist.adapter.MovieAdapter;
import com.arakelyan.kinolist.data.FavoriteMovie;
import com.arakelyan.kinolist.data.MainViewModel;
import com.arakelyan.kinolist.data.Movie;

import java.util.ArrayList;
import java.util.List;


public class FavoriteActivity extends AppCompatActivity {

    private RecyclerView recyclerViewFavorite;
    private MovieAdapter adapter;
    private MainViewModel mainViewModel;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_manu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch (id) {

            case R.id.itemMain:
                Intent mainIntent = new Intent(this, MainActivity.class);
                startActivity(mainIntent);
                break;

            case R.id.itemFavorite:
                Intent favoriteIntent = new Intent(this, FavoriteActivity.class);
                startActivity(favoriteIntent);
                break;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        recyclerViewFavorite = findViewById(R.id.rv_favorite);
        recyclerViewFavorite.setLayoutManager(new GridLayoutManager(this, 2));

        adapter = new MovieAdapter();

        recyclerViewFavorite.setAdapter(adapter);

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        LiveData<List<FavoriteMovie>> favoriteMovies = mainViewModel.getFavoriteMovies();

        favoriteMovies.observe(this, new Observer<List<FavoriteMovie>>() {
            @Override
            public void onChanged(List<FavoriteMovie> favoriteMovies) {
                List<Movie> movies = new ArrayList<>();
                if (favoriteMovies != null) {
                    movies.addAll(favoriteMovies);
                    adapter.setMovies(movies);
                }

            }
        });

        adapter.setOnPostClickListener(new MovieAdapter.OnPostClickListener() {
            @Override
            public void onPostClick(int position) {

                Movie movie = adapter.getMovies().get(position);

                Intent movieDetailsIntent = new Intent(FavoriteActivity.this, DetailActivity.class);

                movieDetailsIntent.putExtra("id", movie.getId());

                startActivity(movieDetailsIntent);
            }
        });

    }
}

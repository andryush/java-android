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
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.arakelyan.kinolist.adapter.MovieAdapter;
import com.arakelyan.kinolist.data.MainViewModel;
import com.arakelyan.kinolist.data.Movie;
import com.arakelyan.kinolist.utils.JSONUtils;
import com.arakelyan.kinolist.utils.NetworkUtils;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewPosters;
    private MovieAdapter movieAdapter;
    private Switch switchSort;
    private TextView textViewPopularity;
    private TextView textViewTopRated;

    private MainViewModel viewModel;


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
        setContentView(R.layout.activity_main);

        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        textViewPopularity = findViewById(R.id.tv_popularity);
        textViewTopRated = findViewById(R.id.tv_top_rated);

        switchSort = findViewById(R.id.sw_sort);
        recyclerViewPosters = findViewById(R.id.rv_posters);
        recyclerViewPosters.setLayoutManager(new GridLayoutManager(this, 2));

        movieAdapter = new MovieAdapter();

        recyclerViewPosters.setAdapter(movieAdapter);

        switchSort.setChecked(true);

        switchSort.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                setMethodOfSort(b);
            }
        });

        switchSort.setChecked(false);


        movieAdapter.setOnPostClickListener(new MovieAdapter.OnPostClickListener() {
            @Override
            public void onPostClick(int position) {

                Movie movie = movieAdapter.getMovies().get(position);

                Intent movieDetailsIntent = new Intent(MainActivity.this, DetailActivity.class);

                movieDetailsIntent.putExtra("id", movie.getId());

                startActivity(movieDetailsIntent);
            }
        });

        
        movieAdapter.setOnReachEndListener(new MovieAdapter.OnReachEndListener() {
            @Override
            public void onReachEnd() {
                Toast.makeText(MainActivity.this, "End of list", Toast.LENGTH_SHORT).show();
            }
        });

        LiveData<List<Movie>> moviesFromLiveData = viewModel.getMovies();
        moviesFromLiveData.observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                movieAdapter.setMovies(movies);
            }
        });
    }

    public void setPopularity(View view) {

        setMethodOfSort(false);
        switchSort.setChecked(false);
    }

    public void setTopRated(View view) {

        setMethodOfSort(true);
        switchSort.setChecked(true);
    }

    private void setMethodOfSort(Boolean b) {
        int methodOfSort;
        if (b) {
            textViewTopRated.setTextColor(getResources().getColor(R.color.colorAccent));
            textViewPopularity.setTextColor(getResources().getColor(R.color.colorWhite));

            methodOfSort = NetworkUtils.TOP_RATED;
        }
        else {

            textViewTopRated.setTextColor(getResources().getColor(R.color.colorWhite));
            textViewPopularity.setTextColor(getResources().getColor(R.color.colorAccent));
            methodOfSort = NetworkUtils.POPULARITY;
        }

        downloadData(methodOfSort, 1);
    }


    private void downloadData(int methodOfSort, int page) {

        JSONObject jsonObject = NetworkUtils.getJSONFromNetwork(methodOfSort, 1);

        ArrayList<Movie> moviesFromJSON = JSONUtils.getMoviesFromJSON(jsonObject);

        if (moviesFromJSON != null && !moviesFromJSON.isEmpty()) {
            viewModel.deleteAllMovies();
            for (Movie movie : moviesFromJSON) {
                viewModel.insertMovie(movie);
            }
        }
    }

}

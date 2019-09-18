package com.arakelyan.kinolist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.arakelyan.kinolist.data.FavoriteMovie;
import com.arakelyan.kinolist.data.MainViewModel;
import com.arakelyan.kinolist.data.Movie;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    private ImageView imageViewFavoritesLogoUnchecked;
    private ImageView imageViewLargePoster;
    private TextView textViewTitle;
    private TextView textViewOriginalTitle;
    private TextView textViewRating;
    private TextView textViewReleaseDate;
    private TextView textViewOverview;

    private int id;
    private Movie movie;
    private FavoriteMovie favoriteMovie;

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
        setContentView(R.layout.activity_detail);

        imageViewLargePoster = findViewById(R.id.iv_large_poster);
        textViewTitle = findViewById(R.id.tv_title);
        textViewOriginalTitle = findViewById(R.id.tv_originalTitle);
        textViewRating = findViewById(R.id.tv_rating);
        textViewReleaseDate = findViewById(R.id.tv_releaseDate);
        textViewOverview = findViewById(R.id.tv_overview);
        imageViewFavoritesLogoUnchecked = findViewById(R.id.iv_favoritesLogoUnchecked);

        Intent intent = getIntent();

        if (intent != null && intent.hasExtra("id")) {
            id = intent.getIntExtra("id", -1);
        }
        else  {
            finish();
        }

        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        movie = viewModel.getMovieById(id);

        if (movie != null) {

            createMovieDetails(movie);

        }
        else {
            movie = viewModel.getFavoriteMovieById(id);

            createMovieDetails(movie);

        }


        setFavorite();

    }

    public void onClickChangeFavorites(View view) {


        if (favoriteMovie == null) {
            viewModel.insertFavoriteMovieTask(new FavoriteMovie(movie));
            Toast.makeText(this, R.string.added_to_favorites, Toast.LENGTH_SHORT).show();
        }
        else {
            viewModel.deleteFavoriteMovieTask(favoriteMovie);
            Toast.makeText(this, R.string.removed_from_favorites, Toast.LENGTH_SHORT).show();
        }
        setFavorite();
    }

    private void setFavorite() {

        favoriteMovie = viewModel.getFavoriteMovieById(id);

        if (favoriteMovie == null) {
            imageViewFavoritesLogoUnchecked.setImageResource(R.drawable.starnotfilledtwo);
        }
        else {
            imageViewFavoritesLogoUnchecked.setImageResource(R.drawable.starfilled);
        }

    }

    private void createMovieDetails(Movie movie) {

        Picasso.get().load(movie.getLargePosterPath()).into(imageViewLargePoster);

        textViewTitle.setText(movie.getTitle());
        textViewOriginalTitle.setText(movie.getOriginalTitle());
        textViewOverview.setText(movie.getOverview());
        textViewReleaseDate.setText(movie.getReleaseDate());
        textViewRating.setText(Double.toString(movie.getVoteAverage()));
    }
}

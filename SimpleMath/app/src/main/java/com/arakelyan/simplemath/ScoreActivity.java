package com.arakelyan.simplemath;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity {

    private TextView tvGameOverScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        tvGameOverScore = findViewById(R.id.tv_game_over_score);

        Intent intent = getIntent();
        if (intent != null & (intent.hasExtra("score")) & (intent.hasExtra("tries"))) {
            int score = intent.getIntExtra("score", 0);
            int tries = intent.getIntExtra("tries", 0);

            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
            int max = sharedPreferences.getInt("max", 0);

            String message = String.format("Your score: %s\nTotal question count: %s\n\nBEST SCORE: %s", score, tries, max);

            tvGameOverScore.setText(message);
        }

    }

    public void onClickStartNewGame(View view) {
        Intent mainIntent = new Intent(this, MainActivity.class);
        startActivity(mainIntent);
    }
}

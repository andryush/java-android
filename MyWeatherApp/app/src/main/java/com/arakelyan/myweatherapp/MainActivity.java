package com.arakelyan.myweatherapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView weatherInfo;
    private EditText enteredCity;
    private Button searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        weatherInfo = findViewById(R.id.tv_weather_info);
        enteredCity = findViewById(R.id.et_search_city);
        searchButton = findViewById(R.id.btn_search);

    }
}

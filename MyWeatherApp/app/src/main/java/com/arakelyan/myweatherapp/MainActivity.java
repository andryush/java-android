package com.arakelyan.myweatherapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private TextView weatherInfo;
    private EditText enteredCity;
    private Button searchButton;
    private DownloadJSON downloadJSON;

    private final String WEATHER_URL = "https://api.openweathermap.org/data/2.5/weather?q=%s&appid=8dd754c90efa043abfd104f712f84448&units=metric";


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

    public void onSearchButtonClick(View view) {

        String city = enteredCity.getText().toString().trim();

        if (!city.isEmpty()) {
            DownloadJSON downloadJSON = new DownloadJSON();

            String finalUrl = String.format(WEATHER_URL, city);

            downloadJSON.execute(finalUrl);
        }
    }


    private class DownloadJSON extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {

            URL url = null;
            HttpURLConnection httpURLConnection = null;
            StringBuilder stringBuilder = new StringBuilder();


            try {
                url = new URL(strings[0]);
                httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                String line = bufferedReader.readLine();

                while (line != null) {
                    stringBuilder.append(line);
                    line = bufferedReader.readLine();
                }
                return stringBuilder.toString();


            } catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {

                JSONObject jsonObject = new JSONObject(s);

                String cityName = jsonObject.getString("name");
                String temperature = jsonObject.getJSONObject("main").getString("temp");
                String weatherDescription = jsonObject.getJSONArray("weather").getJSONObject(0).getString("description");

                String weatherDone = String.format("City: %s\nTemperature: %s\nOutside: %s",cityName, temperature, weatherDescription);

                weatherInfo.setText(weatherDone);


            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    }

}

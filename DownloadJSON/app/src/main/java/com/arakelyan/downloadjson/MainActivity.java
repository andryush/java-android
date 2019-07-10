package com.arakelyan.downloadjson;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private String apiCall = "http://api.openweathermap.org/data/2.5/weather?q=Yerevan&appid=8dd754c90efa043abfd104f712f84448";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DownloadJSONTask downloadJSONTask = new DownloadJSONTask();

        downloadJSONTask.execute(apiCall);

    }

    private static class DownloadJSONTask extends AsyncTask<String, Void, String> {
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

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if ( httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.i("myJSON", s);
            try {
//                JSONObject jsonObject = new JSONObject(s);

//                JSONObject main = jsonObject.getJSONObject("main");
//
//                String temp = main.getString("temp");
//                String pressure = main.getString("pressure");

                JSONObject jsonObject = new JSONObject(s);
                JSONArray jsonArray = jsonObject.getJSONArray("weather");
                JSONObject weather = jsonArray.getJSONObject(0);

                String id = weather.getString("id");

                Log.i("idd", id);


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

}

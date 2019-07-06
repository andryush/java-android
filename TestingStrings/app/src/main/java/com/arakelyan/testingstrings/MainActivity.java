package com.arakelyan.testingstrings;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        String url = "<div class=\"image\">\n" +
                "\t\t\t\t\t\t<img src=\"http://cdn.posh24.se/images/:profile/0c0a0c119a1d107c149fabd0eb559d229\" alt=\"Kim Kardashian\"/>\n" +
                "\t\t\t\t\t</div>";

        Pattern pattern = Pattern.compile("src=\"(.*?)\"");
        Matcher matcher = pattern.matcher(url);

        while (matcher.find()) {
            Log.i("MyString", matcher.group(1));
        }

    }
}

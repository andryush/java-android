package com.arakelyan.beerexpert;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private BeerAdvicer beerAdvicer = new BeerAdvicer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void findBeer(View view) {
        TextView showBeers = findViewById(R.id.tv_show_beers);
        Spinner beerColors = findViewById(R.id.sp_beer_color);

        String beerType = (String) beerColors.getSelectedItem();

        List<String> brandList = beerAdvicer.getBrands(beerType);

        StringBuilder stringBuilder = new StringBuilder();

        for (String beer : brandList) {
            stringBuilder.append(beer).append("\n");
        }

        showBeers.setText(stringBuilder);

    }

}

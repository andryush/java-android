package com.arakelyan.gunshop;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class AssaultRiflesActivity extends AppCompatActivity {

    private ListView assaultRiflesList;
    private List<Guns> assaultRifles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assault_rifles);
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.hide();
        }

        assaultRiflesList = findViewById(R.id.lv_assault_rifles_list);

        assaultRifles = new ArrayList<>();

        assaultRifles.add(new Guns(getString(R.string.assault_rifle_name_ak74), getString(R.string.assault_rifle_description_ak74), R.drawable.ak74logo));
        assaultRifles.add(new Guns(getString(R.string.assault_rifle_name_m4), getString(R.string.assault_rifle_description_m4), R.drawable.m4logo));
        assaultRifles.add(new Guns(getString(R.string.assault_rifle_name_aug), getString(R.string.assault_rifle_description_aug), R.drawable.auglogo));

        ArrayAdapter<Guns> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, assaultRifles);

        assaultRiflesList.setAdapter(adapter);

        assaultRiflesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Guns riffleClicked = assaultRifles.get(position);

                Intent assaultRiflesDetailsIntent = new Intent(getApplicationContext(), AssaultRiflesDetailsActivity.class);

                assaultRiflesDetailsIntent.putExtra("name", riffleClicked.getGunName());
                assaultRiflesDetailsIntent.putExtra("description", riffleClicked.getGunDescription());
                assaultRiflesDetailsIntent.putExtra("imageId", riffleClicked.getGunImageId());

                startActivity(assaultRiflesDetailsIntent);

            }
        });


    }
}

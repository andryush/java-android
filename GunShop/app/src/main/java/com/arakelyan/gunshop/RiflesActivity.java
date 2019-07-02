package com.arakelyan.gunshop;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class RiflesActivity extends AppCompatActivity {

    private ListView listViewRifles;
    private List<Guns> rifles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rifles);

        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.hide();
        }

        listViewRifles = findViewById(R.id.lv_rifles_list);

        rifles = new ArrayList<>();

        rifles.add(new Guns(getString(R.string.rifle_name_dragunov), getString(R.string.rifle_description_dragunov), R.drawable.svd));
        rifles.add(new Guns(getString(R.string.rifle_name_awm), getString(R.string.rifle_description_awm), R.drawable.awm));
        rifles.add(new Guns(getString(R.string.rifle_name_m95), getString(R.string.rifle_description_m95), R.drawable.m95));

        ArrayAdapter<Guns> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, rifles);

        listViewRifles.setAdapter(adapter);

        listViewRifles.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Guns rifleClicked = rifles.get(position);

                Intent riflesIntent = new Intent(getApplicationContext(), RiflesDetailsActivity.class);

                riflesIntent.putExtra("name", rifleClicked.getGunName());
                riflesIntent.putExtra("description", rifleClicked.getGunDescription());
                riflesIntent.putExtra("imageId", rifleClicked.getGunImageId());

                startActivity(riflesIntent);

            }
        });

    }
}

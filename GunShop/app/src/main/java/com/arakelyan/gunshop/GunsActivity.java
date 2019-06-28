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

public class GunsActivity extends AppCompatActivity {

    private ListView gunsList;

    private ArrayList<Guns> guns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guns);

        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.hide();
        }

        gunsList = findViewById(R.id.lv_guns);

        guns = new ArrayList<>();

        guns.add(new Guns(getString(R.string.gun_name_tt), getString(R.string.gun_description_tt), R.drawable.ttimage));
        guns.add(new Guns(getString(R.string.gun_name_stechkin), getString(R.string.gun_description_stechkin), R.drawable.stechkinimage));
        guns.add(new Guns(getString(R.string.gun_name_makarov), getString(R.string.gun_description_makarov), R.drawable.makarovimage));

        ArrayAdapter<Guns> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, guns);
        gunsList.setAdapter(adapter);

        gunsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Guns gunClicked = guns.get(position);

                Intent gunsDetailIntent = new Intent(getApplicationContext(), GunsDetailActivity.class);

                gunsDetailIntent.putExtra("name", gunClicked.getGunName());

                gunsDetailIntent.putExtra("description", gunClicked.getGunDescription());

                gunsDetailIntent.putExtra("imageId", gunClicked.getGunImageId());


                startActivity(gunsDetailIntent);

            }
        });

    }
}

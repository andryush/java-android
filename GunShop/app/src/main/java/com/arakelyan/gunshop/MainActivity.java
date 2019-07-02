package com.arakelyan.gunshop;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private ListView mainListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.hide();
        }

        mainListView = findViewById(R.id.lv_sections);

        mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                switch (position) {

                    case 0:
                        Intent gunsIntent = new Intent(getApplicationContext(), GunsActivity.class);
                        startActivity(gunsIntent);
                        break;

                    case 1:
                        Intent shotgunsIntent = new Intent(getApplicationContext(), AssaultRiflesActivity.class);
                        startActivity(shotgunsIntent);
                        break;

                    case 2:
                        Intent assaultRiflesIntent = new Intent(getApplicationContext(), RiflesActivity.class);
                        startActivity(assaultRiflesIntent);
                        break;
                }

            }
        });

    }
}

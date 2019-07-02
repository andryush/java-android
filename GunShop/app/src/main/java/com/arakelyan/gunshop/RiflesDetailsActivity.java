package com.arakelyan.gunshop;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class RiflesDetailsActivity extends AppCompatActivity {

    private ImageView imageViewRifleImage;
    private TextView textViewRiflename;
    private TextView textViewRifleDescription;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rifles_details);

        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.hide();
        }

        imageViewRifleImage = findViewById(R.id.iv_details_rifles_image);
        textViewRiflename = findViewById(R.id.tv_details_rifles_name);
        textViewRifleDescription = findViewById(R.id.tv_details_rifles_description);

        Intent intent = getIntent();

        if (intent.hasExtra("name") && intent.hasExtra("description") && intent.hasExtra("imageId")) {

            String name = intent.getStringExtra("name");
            String description = intent.getStringExtra("description");
            int id = intent.getIntExtra("imageId", -1);

            textViewRiflename.setText(name);
            textViewRifleDescription.setText(description);
            imageViewRifleImage.setImageResource(id);

        }

        else {

            Intent backToRifles = new Intent(this, RiflesActivity.class);

            startActivity(backToRifles);

        }

    }
}

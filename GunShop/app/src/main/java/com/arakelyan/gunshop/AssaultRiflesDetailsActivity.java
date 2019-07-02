package com.arakelyan.gunshop;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class AssaultRiflesDetailsActivity extends AppCompatActivity {

    private ImageView assaultRiflesImage;
    private TextView assaultRiflesName;
    private TextView assaultRiflesDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assault_rifles_details);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        assaultRiflesImage = findViewById(R.id.iv_details_assault_rifles_image);
        assaultRiflesName = findViewById(R.id.tv_details_assault_rifles_name);
        assaultRiflesDescription = findViewById(R.id.tv_details_assault_rifles_description);

        Intent intent = getIntent();

        if (intent.hasExtra("name") && intent.hasExtra("description") && intent.hasExtra("imageId")) {

            String itemName = intent.getStringExtra("name");
            String itemDescription = intent.getStringExtra("description");
            int itemId = intent.getIntExtra("imageId", -1);

            assaultRiflesName.setText(itemName);
            assaultRiflesDescription.setText(itemDescription);
            assaultRiflesImage.setImageResource(itemId);

        }

        else  {
            Intent backToAssaultRifles = new Intent(this, AssaultRiflesActivity.class);
            startActivity(backToAssaultRifles);
        }



    }
}

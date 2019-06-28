package com.arakelyan.gunshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class GunsDetailActivity extends AppCompatActivity {

    private ImageView image;
    private TextView name;
    private TextView description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guns_detail);

        image = findViewById(R.id.iv_details_image);
        name = findViewById(R.id.tv_details_name);
        description = findViewById(R.id.tv_details_description);

        Intent intent = getIntent();

        if (intent.hasExtra("name") && intent.hasExtra("description") && intent.hasExtra("imageId")) {

            String itemName = intent.getStringExtra("name");
            String itemDescription = intent.getStringExtra("description");
            int itemImageId = intent.getIntExtra("imageId", -1);

            name.setText(itemName);
            description.setText(itemDescription);
            image.setImageResource(itemImageId);

        }
        else {
            Intent backIntent = new Intent(this, GunsActivity.class);
            startActivity(backIntent);
        }




    }
}

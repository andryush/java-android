package com.arakelyan.cafeorder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class OrderDetailActivity extends AppCompatActivity {

    private TextView detailedOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        detailedOrder = findViewById(R.id.tv_detailed_order);

        Intent intent = getIntent();
        if (intent.hasExtra("fullOrder")) {
            String order = intent.getStringExtra("fullOrder");
            detailedOrder.setText(order);
        }
        else {
            Intent backToLogin =  new Intent(this, LoginActivity.class);
            startActivity(backToLogin);
        }
    }
}

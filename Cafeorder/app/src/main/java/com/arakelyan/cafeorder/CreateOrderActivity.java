package com.arakelyan.cafeorder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

public class CreateOrderActivity extends AppCompatActivity {

    private TextView textViewHello;
    private TextView textViewAdditions;

    private CheckBox checkBoxSugar;
    private CheckBox checkBoxMilk;
    private CheckBox checkBoxLemon;

    private Spinner spinnerTea;
    private Spinner spinnerCoffee;

    private String name;
    private String password;
    private String hello;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_order);

        Intent intent = getIntent();

        if (intent.hasExtra("name") && intent.hasExtra("password")) {

            name = intent.getStringExtra("name");
            password = intent.getStringExtra("password");
        }
        else {
            name = getString(R.string.default_name);
            password = getString(R.string.default_password);
        }

        textViewHello = findViewById(R.id.tv_helloUser);
        textViewAdditions = findViewById(R.id.tv_ingredients_offer);

        checkBoxSugar = findViewById(R.id.cb_sugar);
        checkBoxMilk = findViewById(R.id.cb_milk);
        checkBoxLemon = findViewById(R.id.cb_lemon);
        
        spinnerTea = findViewById(R.id.sp_tea);
        spinnerCoffee = findViewById(R.id.sp_coffee);

        hello = getString(R.string.tv_hello_user, name);

        textViewHello.setText(hello);

    }

    public void changeDrink(View view) {

    }

    public void sendOrder(View view) {

    }
}

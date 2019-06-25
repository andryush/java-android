package com.arakelyan.cafeorder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
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
    private String drink;

    private StringBuilder builderAdditions;

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

        drink = getString(R.string.rb_tea);

        String additions = String.format(getString(R.string.tv_ingredients), drink);
        textViewAdditions.setText(additions);

        String hello = String.format(getString(R.string.tv_hello_user), name);
        textViewHello.setText(hello);

        builderAdditions = new StringBuilder();

    }

    public void changeDrink(View view) {
        RadioButton button = (RadioButton) view;
        int id = button.getId();

        if (id == R.id.rb_tea) {
            drink = getString(R.string.rb_tea);
            spinnerTea.setVisibility(View.VISIBLE);
            spinnerCoffee.setVisibility(View.INVISIBLE);
            checkBoxLemon.setVisibility(View.VISIBLE);
        }
        else if (id == R.id.rb_coffee) {
            drink = getString(R.string.rb_coffee);
            spinnerCoffee.setVisibility(View.VISIBLE);
            spinnerTea.setVisibility(View.INVISIBLE);
            checkBoxLemon.setVisibility(View.INVISIBLE);
        }

        String additions = String.format(getString(R.string.tv_ingredients), drink);

        textViewAdditions.setText(additions);

    }

    public void sendOrder(View view) {

        builderAdditions.setLength(0);

        if (checkBoxSugar.isChecked()) {
            builderAdditions.append(getString(R.string.cb_sugar)).append(" ");
        }

        if (checkBoxSugar.isChecked()) {
            builderAdditions.append(getString(R.string.cb_sugar)).append(" ");
        }

        if (checkBoxLemon.isChecked() && drink.equals(getString(R.string.rb_tea))) {
            builderAdditions.append(getString(R.string.cb_lemon)).append(" ");
        }

        String optionsDrink = "";

        if (drink.equals(getString(R.string.rb_tea))) {
            optionsDrink = spinnerTea.getSelectedItem().toString();
        }
        else {
            optionsDrink = spinnerCoffee.getSelectedItem().toString();
        }

        String order = String.format("Name: %s\nPassword: %s\n Drink: %s\nDrink type: %s\n", name, password, drink, optionsDrink);

        String additions;

        if (builderAdditions.length() > 0) {
            additions = getString(R.string.need_additions) + builderAdditions.toString();
        }
        else {
            additions = getString(R.string.no_additions);
        }

        String fullOrder = order + additions;

        Intent intent = new Intent(this, OrderDetailActivity.class);

        intent.putExtra("fullOrder", fullOrder);

        startActivity(intent);
    }
}

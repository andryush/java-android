package com.arakelyan.cafeorder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText name;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        name = findViewById(R.id.et_name);
        password = findViewById(R.id.et_password);
    }

    public void onClickLogin(View view) {

        String nameFromForm = name.getText().toString().trim();
        String passwordFromForm = password.getText().toString().trim();

        if (!nameFromForm.isEmpty() && !passwordFromForm.isEmpty()) {

            Intent intent = new Intent(this, CreateOrderActivity.class);

            intent.putExtra("name", nameFromForm);
            intent.putExtra("password", passwordFromForm);

            startActivity(intent);
        }
        else {
            Toast.makeText(this, R.string.login_required, Toast.LENGTH_SHORT).show();
        }




    }
}

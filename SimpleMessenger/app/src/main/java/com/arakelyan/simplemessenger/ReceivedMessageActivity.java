package com.arakelyan.simplemessenger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ReceivedMessageActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_received_message);
        Intent intent = getIntent();
        String msg = intent.getStringExtra("createdMsg");
        textView = findViewById(R.id.tv_received_text);
        textView.setText(msg);
    }
}

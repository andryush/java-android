package com.arakelyan.simplemessenger_v2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText typedMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        typedMessage = findViewById(R.id.et_message);
    }

    public void onSendButtonClick(View view) {
        String message = typedMessage.getText().toString();

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, message);

        Intent chooserIntent = Intent.createChooser(intent, getString(R.string.customChooser));

        startActivity(chooserIntent);

    }
}

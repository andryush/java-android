package com.arakelyan.colortest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Spinner colorSpinner;
    private TextView colorTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        colorSpinner = findViewById(R.id.colorSpinner);
        colorTextView = findViewById(R.id.colorTextView);
    }

    public void showDescription(View view) {
        int postion = colorSpinner.getSelectedItemPosition();
        String description = getDescriptionById(postion);
        colorTextView.setText(description);
    }

    private String getDescriptionById(int position) {
        String[] colorsDescriptions = getResources().getStringArray(R.array.colors_description);
        return colorsDescriptions[position];
    }
}

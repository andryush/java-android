package com.arakelyan.mynotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.arakelyan.mynotes.classes.Note;

public class AddNoteActivity extends AppCompatActivity {

    private EditText editTextTitle;
    private EditText editTextDescription;
    private Spinner spinnerDaysOfWeek;
    private RadioGroup radioGroupPriority;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        editTextTitle = findViewById(R.id.et_title);
        editTextDescription = findViewById(R.id.et_description);
        spinnerDaysOfWeek = findViewById(R.id.sp_daysOfWeek);
        radioGroupPriority = findViewById(R.id.rg_priorityRadioGroup);

    }

    public void onClickCreateNote(View view) {

        String title = editTextTitle.getText().toString().trim();
        String description = editTextDescription.getText().toString().trim();
        String daysOfWeek = spinnerDaysOfWeek.getSelectedItem().toString();

        int radioButtonId = radioGroupPriority.getCheckedRadioButtonId();
        RadioButton radioButton = findViewById(radioButtonId);
        int priority = Integer.parseInt(radioButton.getText().toString());

        Note note = new Note(title, description, daysOfWeek, priority);

        MainActivity.notes.add(note);

        Intent backToHomeIntent = new Intent(this, MainActivity.class);
        startActivity(backToHomeIntent);

    }
}

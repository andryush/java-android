package com.arakelyan.mynotes;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.arakelyan.mynotes.classes.NotesContract;
import com.arakelyan.mynotes.classes.NotesDBHelper;

public class AddNoteActivity extends AppCompatActivity {

    private EditText editTextTitle;
    private EditText editTextDescription;
    private Spinner spinnerDaysOfWeek;
    private RadioGroup radioGroupPriority;
    private NotesDBHelper notesDBHelper;
    private SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        notesDBHelper = new NotesDBHelper(this);
        sqLiteDatabase = notesDBHelper.getWritableDatabase();

        editTextTitle = findViewById(R.id.et_title);
        editTextDescription = findViewById(R.id.et_description);
        spinnerDaysOfWeek = findViewById(R.id.sp_daysOfWeek);
        radioGroupPriority = findViewById(R.id.rg_priorityRadioGroup);

    }

    public void onClickCreateNote(View view) {

        String title = editTextTitle.getText().toString().trim();
        String description = editTextDescription.getText().toString().trim();
        int daysOfWeek = spinnerDaysOfWeek.getSelectedItemPosition();

        int radioButtonId = radioGroupPriority.getCheckedRadioButtonId();
        RadioButton radioButton = findViewById(radioButtonId);
        int priority = Integer.parseInt(radioButton.getText().toString());

        if (isFilled(title, description)) {
            ContentValues contentValues = new ContentValues();

            contentValues.put(NotesContract.NotesEntry.COLUMN_TITLE, title);
            contentValues.put(NotesContract.NotesEntry.COLUMN_DESCRIPTION, description);
            contentValues.put(NotesContract.NotesEntry.COLUMN_DAY_OF_WEEK, daysOfWeek + 1);
            contentValues.put(NotesContract.NotesEntry.COLUMN_PRIORITY, priority);

            sqLiteDatabase.insert(NotesContract.NotesEntry.TABLE_NAME, null, contentValues);

            Intent backToHomeIntent = new Intent(this, MainActivity.class);
            startActivity(backToHomeIntent);
        }
        else {
            Toast.makeText(this, R.string.warning_fields_required, Toast.LENGTH_SHORT).show();
        }


    }

    private boolean isFilled(String title, String description) {
        return !title.isEmpty() && !description.isEmpty();
    }
}

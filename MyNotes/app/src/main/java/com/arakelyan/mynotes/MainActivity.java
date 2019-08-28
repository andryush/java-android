package com.arakelyan.mynotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.arakelyan.mynotes.Model.MainViewModel;
import com.arakelyan.mynotes.classes.Note;
import com.arakelyan.mynotes.classes.NotesAdapter;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewNotes;
    private NotesAdapter adapter;
    private final ArrayList<Note> notes = new ArrayList<>();
    private MainViewModel mainViewModel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        recyclerViewNotes = findViewById(R.id.rv_notes);

        adapter = new NotesAdapter(notes);
        recyclerViewNotes.setLayoutManager(new LinearLayoutManager(this));

        getData();

        recyclerViewNotes.setAdapter(adapter);


        adapter.setOnNoteClickListner(new NotesAdapter.OnNoteClickListener() {
            @Override
            public void onNoteClick(int position) {
                Toast.makeText(MainActivity.this, "Position = " + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNoteLongClick(int position) {
                removeClickedNote(position);
            }
        });

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                removeClickedNote(viewHolder.getAdapterPosition());
            }
        });

        itemTouchHelper.attachToRecyclerView(recyclerViewNotes);

    }


    private void removeClickedNote(int position) {

        Note note = notes.get(position);

        mainViewModel.deleteNote(note);
    }

    public void onClickAddButton(View view) {

        Intent addNoteIntent = new Intent(this, AddNoteActivity.class);

        startActivity(addNoteIntent);

    }

    private void getData() {
        LiveData<List<Note>> notesFromDB = mainViewModel.getNotes();
        notesFromDB.observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notesFromLiveData) {
                notes.clear();
                notes.addAll(notesFromLiveData);
                adapter.notifyDataSetChanged();
            }
        });

    }

}

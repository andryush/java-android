package com.arakelyan.mynotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.arakelyan.mynotes.classes.Note;
import com.arakelyan.mynotes.classes.NotesAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewNotes;
    private NotesAdapter adapter;
    public static final ArrayList<Note> notes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewNotes = findViewById(R.id.rv_notes);

        if (notes.isEmpty()) {

            notes.add(new Note("Shop", "Buy milk", "Monday", 2 ));
            notes.add(new Note("Shop", "Buy sugar", "Sunday", 2 ));
            notes.add(new Note("Work", "Start newsletter", "Tuesday", 1 ));
            notes.add(new Note("Work", "Change banner", "Monday", 2 ));
            notes.add(new Note("Home", "Update TV", "Wednesday", 3 ));
            notes.add(new Note("Barbershop", "Cut hair", "Thursdays", 1 ));
            notes.add(new Note("Home", "Do some work", "Monday", 2 ));

        }



        adapter = new NotesAdapter(notes);
        recyclerViewNotes.setLayoutManager(new LinearLayoutManager(this));
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
        notes.remove(position);
        adapter.notifyDataSetChanged();
    }

    public void onClickAddButton(View view) {

        Intent addNoteIntent = new Intent(this, AddNoteActivity.class);

        startActivity(addNoteIntent);

    }
}

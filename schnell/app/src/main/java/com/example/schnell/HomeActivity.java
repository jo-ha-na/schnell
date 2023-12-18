package com.example.schnell;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HomeActivity extends AppCompatActivity {

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Initialise Firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();

        Button btnAddEvent = findViewById(R.id.btnAddEvent);
        Button btnAddNote = findViewById(R.id.btnAddNote);
        Button btnShowCalendar = findViewById(R.id.btnShowCalendar);
        Button btnShowNotes = findViewById(R.id.btnShowNotes);
        Button btnAddShowEvent = findViewById(R.id.btnShowEvent);

        btnAddEvent.setOnClickListener(view -> {
            // Naviguer vers la page d'ajout d'événement
            Intent intent = new Intent(HomeActivity.this, AddEventActivity.class);
            startActivity(intent);
        });

        btnAddShowEvent.setOnClickListener(view -> {
            // Naviguer vers la page d'affichage de tous les événements
            Intent intent = new Intent(HomeActivity.this, EventListActivity.class);
            startActivity(intent);
        });

        btnAddNote.setOnClickListener(view -> {
            // Naviguer vers la page d'ajout d'événement
            Intent intent = new Intent(HomeActivity.this, AddNoteActivity.class);
            startActivity(intent);
        });
        btnShowNotes.setOnClickListener(view -> {
            // Naviguer vers la page d'affichage des notes
            Intent intent = new Intent(HomeActivity.this, NoteActivity.class);
            startActivity(intent);
        });

        btnShowCalendar.setOnClickListener(view -> {
            // Naviguer vers la page du calendrier
            Intent intent = new Intent(HomeActivity.this, CalendarActivity.class);
            startActivity(intent);
        });
    }
}

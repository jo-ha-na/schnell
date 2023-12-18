package com.example.schnell;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class EventActivity extends AppCompatActivity {

    private RecyclerView recyclerViewEvents;
    private EventAdapter eventAdapter;
    private List<EventModel> eventList;
    private DatabaseReference databaseReference;
    private TextView textViewDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("events");
        recyclerViewEvents = findViewById(R.id.recyclerViewEvents);
        eventList = new ArrayList<>();
        eventAdapter = new EventAdapter(eventList, this);
        recyclerViewEvents.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewEvents.setAdapter(eventAdapter);

        Button btnAddEvent = findViewById(R.id.btnAddEvent);
        btnAddEvent.setOnClickListener(view -> {
            Intent intent = new Intent(EventActivity.this, AddEventActivity.class);
            startActivity(intent);
        });

        textViewDate = findViewById(R.id.textViewDate);


        Intent intent = getIntent();
        if (intent.hasExtra("year") && intent.hasExtra("month") && intent.hasExtra("dayOfMonth")) {
            int year = intent.getIntExtra("year", 0);
            int month = intent.getIntExtra("month", 0);
            int dayOfMonth = intent.getIntExtra("dayOfMonth", 0);

            String formattedDate = String.format("%02d/%02d/%04d", dayOfMonth, month + 1, year);
            textViewDate.setText("Événements à la date : " + formattedDate);

            loadEventsForDate(year, month, dayOfMonth);
        }
        // Bouton pour afficher tous les événements
        Button btnShowAllEvents = findViewById(R.id.btnShowAllEvents);
        btnShowAllEvents.setOnClickListener(view -> {
            Intent showAllIntent = new Intent(EventActivity.this, EventListActivity.class);
            startActivity(showAllIntent);
        });
    }

    private void loadEventsForDate(final int year, final int month, final int dayOfMonth) {
        eventList.clear();

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot eventSnapshot : snapshot.getChildren()) {
                    EventModel event = eventSnapshot.getValue(EventModel.class);
                    if (event != null && event.getYear() == year && (event.getMonth() ) == month && event.getDayOfMonth() == dayOfMonth) {
                        eventList.add(event);
                    }
                }
                eventAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Gérer les erreurs
            }
        });
    }
}

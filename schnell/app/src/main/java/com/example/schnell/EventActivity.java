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
import java.util.Objects;

public class EventActivity extends AppCompatActivity {

    private RecyclerView recyclerViewEvents;
    private EventAdapter eventAdapter;
    private List<com.example.projetandroid.EventModel> eventList;
    private DatabaseReference databaseReference;
    private TextView textViewDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        databaseReference = FirebaseDatabase.getInstance().getReference("events");

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

            List<com.example.projetandroid.EventModel> eventsForDate = loadEventsForDate(year, month, dayOfMonth);

            eventList.clear();
            eventList.addAll(eventsForDate);
            eventAdapter.notifyDataSetChanged();
        }

        // Bouton pour afficher tous les événements
        Button btnShowAllEvents = findViewById(R.id.btnShowAllEvents);
        btnShowAllEvents.setOnClickListener(view -> {
            Intent showAllIntent = new Intent(EventActivity.this, EventListActivity.class);
            startActivity(showAllIntent);
        });
    }

    private List<com.example.projetandroid.EventModel> loadEventsForDate(int year, int month, int dayOfMonth) {
        List<com.example.projetandroid.EventModel> events = new ArrayList<>();

        databaseReference.child(year + "-" + month + "-" + dayOfMonth).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot eventSnapshot : snapshot.getChildren()) {
                    com.example.projetandroid.EventModel event = eventSnapshot.getValue(com.example.projetandroid.EventModel.class);
                    events.add(Objects.requireNonNull(event));
                }
                eventAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Gérer les erreurs
            }
        });

        return events;
    }

    private void openModifyDateActivity(Object eventId) {
        Intent modifyDateIntent = new Intent(EventActivity.this, ModifyDateActivity.class);
        modifyDateIntent.putExtra("eventId", eventId.toString());
        startActivity(modifyDateIntent);
    }
}

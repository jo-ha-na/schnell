package com.example.schnell;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;

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
import java.util.Collections;
import java.util.List;

public class CalendarActivity extends AppCompatActivity {
    private EventAdapter eventAdapter;
    private CalendarView calendarView;
    private RecyclerView recyclerViewUpcomingEvents;
    private UpcomingEventsAdapter upcomingEventsAdapter;
    private DatabaseReference databaseReference;
    private List<EventModel> eventList;

    private Button btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        calendarView = findViewById(R.id.calendarView);
        recyclerViewUpcomingEvents = findViewById(R.id.recyclerViewUpcomingEvents);
        btn2 = findViewById(R.id.btn2);

        eventList = new ArrayList<>();
        eventAdapter = new EventAdapter(eventList, this);
        upcomingEventsAdapter = new UpcomingEventsAdapter(new ArrayList<>());
        recyclerViewUpcomingEvents.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewUpcomingEvents.setAdapter(upcomingEventsAdapter);


        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent pour démarrer HomeActivity
                Intent intent = new Intent(CalendarActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });


        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            Intent intent = new Intent(CalendarActivity.this, EventActivity.class);
            intent.putExtra("year", year);
            intent.putExtra("month", month);
            intent.putExtra("dayOfMonth", dayOfMonth);
            startActivity(intent);
        });

        // Chargez tous les événements et affichez-les dans le RecyclerView
        loadAllEvents();
    }

    private void loadAllEvents() {
        databaseReference.child("events").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<EventModel> allEvents = new ArrayList<>();
                for (DataSnapshot eventSnapshot : snapshot.getChildren()) {
                    EventModel event = eventSnapshot.getValue(EventModel.class);
                    if (event != null) {
                        allEvents.add(event);
                    }
                }

                // Triez la liste d'événements par date si nécessaire
                // ...

                // Mettez à jour l'adaptateur avec la liste d'événements
                upcomingEventsAdapter.updateEvents(allEvents);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Gérer les erreurs
            }
        });
    }

}

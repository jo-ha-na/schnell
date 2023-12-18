package com.example.schnell;


import android.content.Intent;
import android.os.Bundle;
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
import java.util.List;

public class CalendarActivity extends AppCompatActivity {

    private CalendarView calendarView;
    private RecyclerView recyclerViewUpcomingEvents;
    private UpcomingEventsAdapter upcomingEventsAdapter;

    // Ajoutez une référence à la base de données
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        // Initialisez la référence à la base de données
        databaseReference = FirebaseDatabase.getInstance().getReference();

        calendarView = findViewById(R.id.calendarView);
        recyclerViewUpcomingEvents = findViewById(R.id.recyclerViewUpcomingEvents);

        // Initialisez l'adapter et le RecyclerView
        upcomingEventsAdapter = new UpcomingEventsAdapter(new ArrayList<>());
        recyclerViewUpcomingEvents.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewUpcomingEvents.setAdapter(upcomingEventsAdapter);

        // Écoutez les changements de date dans le calendrier
        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            // Utilisez la méthode correcte pour charger les événements
            List<com.example.schnell.EventModel> upcomingEvents = loadEventsForDate(year, month, dayOfMonth);

            // Mettez à jour l'adaptateur avec les nouveaux événements
            upcomingEventsAdapter.updateEvents(upcomingEvents);

            // Passez à EventActivity avec la date sélectionnée
            Intent intent = new Intent(CalendarActivity.this, EventActivity.class);
            intent.putExtra("year", year);
            intent.putExtra("month", month);
            intent.putExtra("dayOfMonth", dayOfMonth);
            startActivity(intent);
        });
    }

    // Méthode pour obtenir les événements à venir pour une date spécifique
    private List<com.example.schnell.EventModel> loadEventsForDate(int year, int month, int dayOfMonth) {
        List<com.example.schnell.EventModel> events = new ArrayList<>();

        // Construisez le chemin approprié dans la base de données Firebase en fonction de la date
        String datePath = year + "/" + month + "/" + dayOfMonth;

        // Utilisez la référence à la base de données Firebase avec le chemin correct
        DatabaseReference eventsRef = databaseReference.child("events").child(datePath);

        // Ajoutez un écouteur pour récupérer les événements depuis Firebase
        eventsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot eventSnapshot : snapshot.getChildren()) {
                    com.example.schnell.EventModel event = eventSnapshot.getValue(com.example.schnell.EventModel.class);
                    events.add(event);
                }

                // Mettez à jour votre adaptateur après avoir récupéré les événements
                upcomingEventsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Gérer les erreurs
            }
        });

        return events;
    }
}

package com.example.schnell;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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

public class EventListActivity extends AppCompatActivity {

    private RecyclerView recyclerViewAllEvents;
    private EventAdapter eventAdapter;
    private List<EventModel> allEventsList;
    private DatabaseReference allEventsReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);
        Button btnAddEvent1 = findViewById(R.id.btnAddEvent1);
        btnAddEvent1.setOnClickListener(view -> {
            Intent intent = new Intent(EventListActivity.this, AddEventActivity.class);
            startActivity(intent);
        });

        allEventsReference = FirebaseDatabase.getInstance().getReference().child("events");

        recyclerViewAllEvents = findViewById(R.id.recyclerViewAllEvents);
        allEventsList = new ArrayList<>();
        eventAdapter = new EventAdapter(allEventsList, this);
        recyclerViewAllEvents.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewAllEvents.setAdapter(eventAdapter);

        loadAllEvents();


    }

    private void loadAllEvents() {
        allEventsReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot eventSnapshot : snapshot.getChildren()) {
                    EventModel event = eventSnapshot.getValue(EventModel.class);
                    if (event != null) {
                        allEventsList.add(event);
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

    // Méthode appelée lorsque le bouton "Ajouter un événement" est cliqué
    public void onAddEventButtonClick(View view) {
        // Intent pour lancer l'activité AddEventActivity
        Intent intent = new Intent(this, AddEventActivity.class);
        startActivity(intent);
    }



}


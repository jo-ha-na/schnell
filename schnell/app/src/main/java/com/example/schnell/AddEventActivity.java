package com.example.schnell;

import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddEventActivity extends AppCompatActivity {
    private DatabaseReference eventsDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        // Ajoutez vos éléments d'interface utilisateur et la logique d'ajout d'événement ici
        EditText etEventName = findViewById(R.id.etEventName);
        EditText etEventDescription = findViewById(R.id.etEventDescription);
        DatePicker datePicker = findViewById(R.id.datePicker);
        Button btnSaveEvent = findViewById(R.id.btnSaveEvent);
        Button btnCancel = findViewById(R.id.btnCancel);

        eventsDatabaseReference = FirebaseDatabase.getInstance().getReference().child("events");

        btnSaveEvent.setOnClickListener(v -> {
            // Récupérez les détails de l'événement depuis les éléments d'interface utilisateur
            String eventName = etEventName.getText().toString();
            String eventDescription = etEventDescription.getText().toString();
            int dayOfMonth = datePicker.getDayOfMonth();
            int month = datePicker.getMonth();
            int year = datePicker.getYear();
            String formattedDate = String.format("%02d/%02d/%04d", dayOfMonth, month + 1, year);

            // Générez une clé unique pour chaque nouvel événement
            String eventId = eventsDatabaseReference.push().getKey();

            // Créez un objet EventModel avec les détails de l'événement
            EventModel newEvent = new EventModel(eventId, eventName, eventDescription, dayOfMonth, month, year, formattedDate);

            // Enregistrez le nouvel événement sous le chemin "events/{eventId}"
            eventsDatabaseReference.child(eventId).setValue(newEvent);

            // Revenez à EventActivity après l'ajout
            finish();
        });


        btnCancel.setOnClickListener(v -> {
            // Ajoutez la logique pour annuler l'ajout de l'événement
            // ...

            // Revenez à EventActivity sans ajouter l'événement
            finish();
        });
    }
}

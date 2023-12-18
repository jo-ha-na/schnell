package com.example.schnell;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddEventActivity extends AppCompatActivity {

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

        btnSaveEvent.setOnClickListener(v -> {
            // Récupérez les détails de l'événement depuis les éléments d'interface utilisateur
            String eventName = etEventName.getText().toString();
            String eventDescription = etEventDescription.getText().toString();
            int dayOfMonth = datePicker.getDayOfMonth();
            int month = datePicker.getMonth();
            int year = datePicker.getYear();

            // Ajoutez la logique pour enregistrer le nouvel événement dans votre source de données
            // ...

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

package com.example.schnell;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class ModifyDateActivity extends AppCompatActivity {

    private EditText etEventName;
    private EditText etEventDescription;
    private DatePicker datePicker;
    private Button btnSaveChanges;
    private Button btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_date);

        etEventName = findViewById(R.id.etEventName);
        etEventDescription = findViewById(R.id.etEventDescription);
        datePicker = findViewById(R.id.datePicker);
        btnSaveChanges = findViewById(R.id.btnSaveChanges);
        btnCancel = findViewById(R.id.btnCancel);

        // Obtenez l'ID de l'événement à partir de l'intent
        Intent intent = getIntent();
        if (intent.hasExtra("eventId")) {
            String eventId = intent.getStringExtra("eventId");

            // Chargez les détails de l'événement à partir de la base de données (Firebase, SQLite, etc.)
            // Vous devrez implémenter cette logique en fonction de votre source de données.
            EventModel currentEvent = loadEventDetails(eventId);

            // Pré-remplissez les champs avec les détails de l'événement actuel
            etEventName.setText(currentEvent.getName());
            etEventDescription.setText(currentEvent.getDescription());

            // Pré-remplissez la date
            datePicker.updateDate(currentEvent.getYear(), currentEvent.getMonth(), currentEvent.getDayOfMonth());

            // Gérez le clic sur le bouton de sauvegarde des modifications
            btnSaveChanges.setOnClickListener(v -> {
                // Récupérez les nouvelles valeurs des champs
                String newName = etEventName.getText().toString();
                String newDescription = etEventDescription.getText().toString();
                int newDay = datePicker.getDayOfMonth();
                int newMonth = datePicker.getMonth();
                int newYear = datePicker.getYear();

                // Mettez à jour les détails de l'événement dans la base de données
                updateEventDetails(eventId, newName, newDescription, newDay, newMonth, newYear);

                // Revenez à l'activité précédente
                finish();
            });

            // Gérez le clic sur le bouton d'annulation
            btnCancel.setOnClickListener(v -> {
                // Revenez à l'activité précédente sans effectuer de modifications
                finish();
            });
        }
    }

    // Méthode pour charger les détails de l'événement depuis la base de données
    private EventModel loadEventDetails(String eventId) {
        // Ajoutez la logique pour charger les détails de l'événement depuis votre source de données (Firebase, SQLite, etc.)
        // ...

        // Exemple de création d'un événement fictif pour éviter une erreur de compilation
        return new EventModel("Nom de l'événement", "Description de l'événement", 1, 1, 2023);
    }

    // Méthode pour mettre à jour les détails de l'événement dans la base de données
    private void updateEventDetails(String eventId, String newName, String newDescription, int newDay, int newMonth, int newYear) {
        // Ajoutez la logique pour mettre à jour les détails de l'événement dans votre source de données (Firebase, SQLite, etc.)
        // ...
    }
}

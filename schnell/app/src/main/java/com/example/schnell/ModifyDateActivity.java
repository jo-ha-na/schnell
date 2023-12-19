package com.example.schnell;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;


import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class ModifyDateActivity extends AppCompatActivity {

    private DatePicker datePicker;
    private Button btnSaveChanges, btnCancel;
    private EditText etEventName, etEventDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_date);

        datePicker = findViewById(R.id.datePicker);
        btnSaveChanges = findViewById(R.id.btnSaveChanges);
        btnCancel = findViewById(R.id.btnCancel);
        etEventName = findViewById(R.id.etEventName);
        etEventDescription = findViewById(R.id.etEventDescription);

        Intent intent = getIntent();
        final String eventId = intent.getStringExtra("eventId");
        int currentYear = intent.getIntExtra("year", 2023);
        int currentMonth = intent.getIntExtra("month", 0);
        int currentDayOfMonth = intent.getIntExtra("dayOfMonth", 1);

        datePicker.updateDate(currentYear, currentMonth, currentDayOfMonth);

        btnSaveChanges.setOnClickListener(v -> {
            String newName = etEventName.getText().toString();
            String newDescription = etEventDescription.getText().toString();
            int newYear = datePicker.getYear();
            int newMonth = datePicker.getMonth();
            int newDayOfMonth = datePicker.getDayOfMonth();
            String formattedDate = String.format("%02d/%02d/%04d", newDayOfMonth, newMonth + 1, newYear);

            DatabaseReference eventsRef = FirebaseDatabase.getInstance().getReference().child("events");

            Map<String, Object> eventUpdates = new HashMap<>();
            eventUpdates.put("name", newName);
            eventUpdates.put("description", newDescription);
            eventUpdates.put("year", newYear);
            eventUpdates.put("month", newMonth);
            eventUpdates.put("dayOfMonth", newDayOfMonth);
            eventUpdates.put("formattedDate", formattedDate);

            eventsRef.child(eventId).updateChildren(eventUpdates).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Toast.makeText(ModifyDateActivity.this, "Événement mis à jour avec succès", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(ModifyDateActivity.this, "Échec de la mise à jour de l'événement", Toast.LENGTH_SHORT).show();
                }
            });
        });

        btnCancel.setOnClickListener(v -> finish());
    }
}


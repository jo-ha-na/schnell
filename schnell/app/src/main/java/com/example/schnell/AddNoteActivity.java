package com.example.schnell;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddNoteActivity extends AppCompatActivity {

    private EditText editTextNote;
    private Button btnSaveNote;
    private Spinner spinnerCategories;
    private String noteIdToUpdate; // Pour stocker l'ID de la note à mettre à jour, le cas échéant
    private String text; // Pour stocker le texte de la note à mettre à jour
    private String category; // Pour stocker la catégorie de la note à mettre à jour

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        spinnerCategories = findViewById(R.id.spinnerCategories);
        editTextNote = findViewById(R.id.editTextNote);
        btnSaveNote = findViewById(R.id.btnSaveNote);

        String[] categories = {"Personnel", "Travail", "Autre"};
        ArrayAdapter<String> categoriesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);
        categoriesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategories.setAdapter(categoriesAdapter);

        Intent intent = getIntent();
        if (intent.hasExtra("NoteText")) {
            text = intent.getStringExtra("NoteText");
            noteIdToUpdate = intent.getStringExtra("NoteId");
            editTextNote.setText(text);
        }

        // Gérer le clic sur le bouton pour enregistrer la note
        btnSaveNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String noteText = editTextNote.getText().toString();
                String selectedCategory = spinnerCategories.getSelectedItem().toString();

                if (noteIdToUpdate != null) {
                    updateNoteInFirebase(noteIdToUpdate, noteText, selectedCategory);
                } else {
                    createNoteInFirebase(noteText, selectedCategory);
                }
            }
        });
    }

    // Méthode pour créer une nouvelle note dans la base de données Firebase
    private void createNoteInFirebase(String noteText, String category) {
        DatabaseReference notesRef = FirebaseDatabase.getInstance().getReference().child("notes");
        String noteId = notesRef.push().getKey();

        NoteModel note = new NoteModel(noteId, noteText, category);
        notesRef.child(noteId).setValue(note);

        Toast.makeText(this, "Nouvelle note enregistrée avec succès", Toast.LENGTH_SHORT).show();
        finish();
    }

    // Méthode pour mettre à jour une note existante dans la base de données Firebase
    private void updateNoteInFirebase(String noteId, String noteText, String category) {
        DatabaseReference notesRef = FirebaseDatabase.getInstance().getReference().child("notes");

        NoteModel updatedNote = new NoteModel(noteId, noteText, category);
        notesRef.child(noteId).setValue(updatedNote);

        Toast.makeText(this, "Note mise à jour avec succès", Toast.LENGTH_SHORT).show();

        finish();
    }
}

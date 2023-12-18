package com.example.schnell;



import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddNoteActivity extends AppCompatActivity {

    private EditText editTextNote;
    private Button btnSaveNote;
    private Spinner spinnerCategories;

    private static final long DOUBLE_CLICK_DELAY = 300;
    private long lastClickTime = 0;
    private int clickCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        spinnerCategories = findViewById(R.id.spinnerCategories);
        editTextNote = findViewById(R.id.editTextNote);
        btnSaveNote = findViewById(R.id.btnSaveNote);

        // Simulez des données de catégories
        String[] categories = {"Personnel", "Travail", "Autre"};

        // Affichez les catégories dans le spinner
        ArrayAdapter<String> categoriesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);
        categoriesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategories.setAdapter(categoriesAdapter);

        // Obtenez les données de l'intent
        Intent intent = getIntent();
        if (intent.hasExtra("NoteText")) {
            String existingNote = intent.getStringExtra("NoteText");
            editTextNote.setText(existingNote);
        }

        // Gérer le clic sur le bouton pour enregistrer la note
        btnSaveNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Récupérez le texte et la catégorie de la note
                String noteText = editTextNote.getText().toString();
                String selectedCategory = spinnerCategories.getSelectedItem().toString();

                // Vérifiez si c'est un double-clic ou un simple clic
                if (isDoubleClick()) {
                    // Double-clic, ouvrez la note (vous devrez implémenter cette logique)
                    openNoteDetails(noteText, selectedCategory);
                } else {
                    // Simple clic, cochez la note (vous devrez implémenter cette logique)
                    checkNoteForDeletion(noteText, selectedCategory);
                }
            }
        });
    }

    private boolean isDoubleClick() {
        long clickTime = System.currentTimeMillis();
        if (clickTime - lastClickTime < DOUBLE_CLICK_DELAY) {
            clickCount++;
        } else {
            clickCount = 1;
        }

        lastClickTime = clickTime;

        // Retourne vrai si c'est un double-clic
        return clickCount == 2;
    }

    private void openNoteDetails(String noteText, String category) {
        // Implémentez la logique pour ouvrir les détails de la note
        // par exemple, lancez une nouvelle activité
        Toast.makeText(this, "Double-clic pour ouvrir : " + noteText, Toast.LENGTH_SHORT).show();
    }

    private void checkNoteForDeletion(String noteText, String category) {
        // Implémentez la logique pour cocher la note pour la suppression
        // par exemple, mettez à jour la base de données ou l'adaptateur de liste
        Toast.makeText(this, "Simple clic pour cocher : " + noteText, Toast.LENGTH_SHORT).show();
    }
}

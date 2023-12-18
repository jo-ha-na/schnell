package com.example.schnell;


import android.content.Intent;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class NoteActivity extends AppCompatActivity {

    private Spinner spinnerCategories;
    private Button btnAddNote;
    private ListView listViewNotes;

    private Button btnDeleteNote;

    private ArrayList<String> notesList;
    private ArrayAdapter<String> notesAdapter;
    private DatabaseReference notesRef;

    private static final long DOUBLE_CLICK_DELAY = 300;
    private long lastClickTime = 0;
    private int clickCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        spinnerCategories = findViewById(R.id.spinnerCategories);
        btnAddNote = findViewById(R.id.btnAddNote);
        listViewNotes = findViewById(R.id.listViewNotes);
        btnDeleteNote = findViewById(R.id.btnDeleteNote);

        notesRef = FirebaseDatabase.getInstance().getReference("notes");

        String[] categories = {"Toutes", "Personnel", "Travail", "Autre"};
        ArrayAdapter<String> categoriesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);
        categoriesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategories.setAdapter(categoriesAdapter);

        notesList = new ArrayList<>();
        notesList.add("Note 1 - Travail");
        notesList.add("Note 2 - Personnel");

        notesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, notesList);
        listViewNotes.setAdapter(notesAdapter);

        listViewNotes.setOnItemClickListener((parent, view, position, id) -> {
            if (isDoubleClick()) {
                // Double-clic, ne rien faire ici ou implémentez une logique spécifique
            } else {
                Intent intent = new Intent(NoteActivity.this, AddNoteActivity.class);
                intent.putExtra("NoteText", notesList.get(position));
                startActivity(intent);
            }
        });

        listViewNotes.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                listViewNotes.setItemChecked(position, !listViewNotes.isItemChecked(position));
                return true;
            }
        });

        btnAddNote.setOnClickListener(v -> {
            startActivity(new Intent(NoteActivity.this, AddNoteActivity.class));
        });

        btnDeleteNote.setOnClickListener(v -> {
            SparseBooleanArray selectedItems = listViewNotes.getCheckedItemPositions();
            for (int i = selectedItems.size() - 1; i >= 0; i--) {
                int position = selectedItems.keyAt(i);
                if (selectedItems.valueAt(i)) {
                    notesList.remove(position);
                }
            }
            notesAdapter.notifyDataSetChanged();
            // Mettez en œuvre la logique pour supprimer les notes de la base de données
            // Vous devrez implémenter cette logique en fonction de vos besoins
            listViewNotes.clearChoices();
        });

        spinnerCategories.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                updateNotesList(categories[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Ne rien faire ici pour l'instant
            }
        });

        listViewNotes.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedNote = notesList.get(position);
                Intent intent = new Intent(NoteActivity.this, AddNoteActivity.class);
                intent.putExtra("NoteText", selectedNote);
                startActivity(intent);
                return true;
            }
        });
    }

    private void updateNotesList(String selectedCategory) {
        // Mettez à jour la liste des notes en fonction de la catégorie sélectionnée
        // Vous devrez implémenter cette logique en fonction de vos besoins
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
}
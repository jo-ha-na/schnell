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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class NoteActivity extends AppCompatActivity {

    private Spinner spinnerCategories;
    private Button btnAddNote;
    private ListView listViewNotes;
    private Button btnDeleteNote;

    private ArrayList<NoteModel> notesList;
    private ArrayAdapter<NoteModel> notesAdapter; // Utilisez l'adaptateur NoteModel
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
        notesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, notesList);
        listViewNotes.setAdapter(notesAdapter);

        listViewNotes.setOnItemClickListener((parent, view, position, id) -> {
            if (isDoubleClick()) {
                String selectedNoteId = notesList.get(position).getId();
                String selectedNoteText = notesList.get(position).getText();
                String selectedNoteCategory = notesList.get(position).getCategory();

                Intent intent = new Intent(NoteActivity.this, AddNoteActivity.class);
                intent.putExtra("NoteId", selectedNoteId);
                intent.putExtra("NoteText", selectedNoteText);
                intent.putExtra("NoteCategory", selectedNoteCategory);
                intent.putExtra("EditMode", true); // Indiquez que vous êtes en mode édition
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
            deleteSelectedNotes();
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
    }

    private boolean isDoubleClick() {
        long clickTime = System.currentTimeMillis();
        if (clickTime - lastClickTime < DOUBLE_CLICK_DELAY) {
            clickCount++;
        } else {
            clickCount = 1;
        }
        lastClickTime = clickTime;
        return clickCount == 2;
    }

    private void updateNotesList(String selectedCategory) {
        if (selectedCategory.equals("Toutes")) {
            // Afficher toutes les notes
            loadAllNotes();
        } else {
            // Afficher les notes de la catégorie sélectionnée
            loadNotesByCategory(selectedCategory);
        }
    }

    private void loadAllNotes() {
        notesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                notesList.clear();
                for (DataSnapshot noteSnapshot : snapshot.getChildren()) {
                    NoteModel note = noteSnapshot.getValue(NoteModel.class);
                    if (note != null) {
                        notesList.add(note);
                    }
                }
                notesAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Gérer les erreurs
            }
        });
    }

    private void loadNotesByCategory(String category) {
        notesRef.orderByChild("category").equalTo(category).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                notesList.clear();
                for (DataSnapshot noteSnapshot : snapshot.getChildren()) {
                    NoteModel note = noteSnapshot.getValue(NoteModel.class);
                    if (note != null) {
                        notesList.add(note);
                    }
                }
                notesAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Gérer les erreurs
            }
        });
    }

    private void deleteSelectedNotes() {
        SparseBooleanArray selectedItems = listViewNotes.getCheckedItemPositions();
        for (int i = selectedItems.size() - 1; i >= 0; i--) {
            int position = selectedItems.keyAt(i);
            if (selectedItems.valueAt(i)) {
                String selectedNoteId = notesList.get(position).getId();
                // Supprimer la note de la base de données
                deleteNoteFromDatabase(selectedNoteId);
                notesList.remove(position);
            }
        }
        notesAdapter.notifyDataSetChanged();
        listViewNotes.clearChoices();
    }

    private void deleteNoteFromDatabase(String noteId) {
        DatabaseReference noteToDeleteRef = notesRef.child(noteId);
        noteToDeleteRef.removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                // La note a été supprimée avec succès de la base de données
                Toast.makeText(NoteActivity.this, "Note supprimée avec succès", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // Une erreur s'est produite lors de la suppression de la note
                Toast.makeText(NoteActivity.this, "Erreur lors de la suppression de la note", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

package com.example.schnell;

import com.google.firebase.database.PropertyName;

public class NoteModel {
    @PropertyName("id")
    private String id;

    @PropertyName("text")
    private String text;

    @PropertyName("category")
    private String category;

    public NoteModel() {
        // Constructeur vide requis pour Firebase
    }

    public NoteModel(String id, String text, String category) {
        this.id = id;
        this.text = text;
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getCategory() {
        return category;
    }
}

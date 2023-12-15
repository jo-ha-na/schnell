package com.example.schnell;


public class NoteModel {
    private String id;
    private String text;
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
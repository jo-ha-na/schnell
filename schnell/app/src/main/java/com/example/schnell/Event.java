package com.example.schnell;

public class Event {
    private String name;
    private String description;
    private int dayOfMonth;
    private int month;
    private int year;

    // Constructeur
    public Event(String name, String description, int dayOfMonth, int month, int year) {
        this.name = name;
        this.description = description;
        this.dayOfMonth = dayOfMonth;
        this.month = month;
        this.year = year;
    }
    public void updateDate(int dayOfMonth, int month, int year) {
        this.dayOfMonth = dayOfMonth;
        this.month = month;
        this.year = year;
    }
    // Getter pour le nom de l'événement
    public String getName() {
        return name;
    }

    // Getter pour la description de l'événement
    public String getDescription() {
        return description;
    }

    // Ajouter des getters pour dayOfMonth, month et year si nécessaire
    public int getDayOfMonth() {
        return dayOfMonth;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public Object getId() {
        return null;
    }
}


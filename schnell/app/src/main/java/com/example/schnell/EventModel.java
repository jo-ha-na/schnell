package com.example.schnell;
public class EventModel {

    private String name;
    private String description;
    private int dayOfMonth;
    private int month;
    private int year;
    private Object id; // Ajoutez cette ligne pour déclarer la variable id

    public EventModel(String name, String description, int dayOfMonth, int month, int year) {
        this.name = name;
        this.description = description;
        this.dayOfMonth = dayOfMonth;
        this.month = month;
        this.year = year;
        // Vous devez initialiser l'ID d'une manière ou d'une autre, selon votre logique d'application.
        // Par exemple, vous pourriez le générer automatiquement ou le passer en tant que paramètre.
        // this.id = generateId();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

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
        return id;
    }

    public String getFormattedDate() {
        return String.format("%02d/%02d/%04d", dayOfMonth, month + 1, year);
    }
}

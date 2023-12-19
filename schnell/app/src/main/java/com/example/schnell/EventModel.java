package com.example.schnell;

public class EventModel {

    private String name;
    private String description;
    private long dayOfMonth;
    private long month;
    private long year;
    private String eventId;
    private String formattedDate; // Ajout de formattedDate

    // Ajout d'un constructeur vide nécessaire pour Firebase
    public EventModel() {
    }

    public EventModel(String eventId, String name, String description, long dayOfMonth, long month, long year, String formattedDate) {
        this.eventId = eventId;
        this.name = name;
        this.description = description;
        this.dayOfMonth = dayOfMonth;
        this.month = month;
        this.year = year;
        this.formattedDate = formattedDate;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public long getDayOfMonth() {
        return dayOfMonth;
    }

    public long getMonth() {
        return month;
    }

    public long getYear() {
        return year;
    }

    public String getEventId() {
        return eventId;
    }

    public String getFormattedDate() {
        return formattedDate;
    }
}

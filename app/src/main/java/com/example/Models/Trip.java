package com.example.Models;

import org.joda.time.DateTime;

public class Trip {
    private int tripId;
    private String tripTitle;
    private DateTime startDate, endDate;
    private String type;
    private String description;
    private int capacity;

    public Trip(int tripId, String tripTitle, DateTime startDate, DateTime endDate, String type, String description, int capacity) {
        this.tripId = tripId;
        this.tripTitle = tripTitle;
        this.startDate = startDate;
        this.endDate = endDate;
        this.type = type;
        this.description = description;
        this.capacity = capacity;
    }

    public int getTripId() {
        return tripId;
    }

    public String getTripTitle() {
        return tripTitle;
    }

    public DateTime getStartDate() {
        return startDate;
    }

    public DateTime getEndDate() {
        return endDate;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public int getCapacity() {
        return capacity;
    }
}

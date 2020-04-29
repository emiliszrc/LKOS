package com.example.Models;

import org.joda.time.DateTime;

public class Trip {
    private int tripId;
    private String tripTitle;
    private DateTime startDate, endDate;
    private int type;
    private String description;
    private int capacity;

    public Trip(int tripId, String tripTitle, String startDate, String endDate, int type, String description, int capacity) {
        this.tripId = tripId;
        this.tripTitle = tripTitle;
        this.startDate = DateTime.parse(startDate);
        this.endDate = DateTime.parse(endDate);
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

    public int getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public int getCapacity() {
        return capacity;
    }
}

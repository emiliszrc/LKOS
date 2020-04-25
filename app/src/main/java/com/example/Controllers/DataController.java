package com.example.Controllers;

import android.os.Build;

import androidx.annotation.RequiresApi;
import java.time.*;
import com.example.Models.Trip;
import com.example.Models.Object;
import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;
import com.google.gson.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;

public class DataController {

    @RequiresApi(api = Build.VERSION_CODES.O)
    public ArrayList<Trip> parseAllTrips (String data) throws JSONException {
        ArrayList<Trip> trips = new ArrayList<>();
        JSONArray Trips = new JSONArray(data);
        for (int i = 0; i<Trips.length(); i++){
            JSONObject json = Trips.getJSONObject(i);
            int TripId = Integer.parseInt(json.getString("tripId"));
            String TripTitle = json.getString("tripTitle");
            DateTime StartDate = DateTime.parse(json.getString("startDate"));
            DateTime EndDate = DateTime.parse(json.getString("endDate"));
            String tripType = json.getString("tripType");
            String TripDescription  = json.getString("tripDescription");
            int Capacity  = Integer.parseInt(json.getString("capacity"));
            Trip temp = new Trip(TripId,TripTitle, StartDate, EndDate, tripType, TripDescription, Capacity);
            trips.add(temp);
        }
        return trips;
    }

    public ArrayList<Object> parseAllObjectsForTrip (String data) throws JSONException {
        ArrayList<Object> objects = new ArrayList<>();
        JSONObject obj = new JSONObject(data);

        JSONArray Objects = obj.getJSONArray("objects");
        for (int i = 0; i<Objects.length(); i++){
            JSONObject json = Objects.getJSONObject(i);
            int id = Integer.parseInt(json.getString("objectId"));
            int type = Integer.parseInt(json.getString("objectType"));
            String title = json.getString("objectTitle");
            String address = json.getString("objectAddress");
            Object temp = new Object(id, type, title, address);
            objects.add(temp);
        }
        return objects;
    }

    public ArrayList<Object> parseAllObjects (String data) throws JSONException {
        ArrayList<Object> objects = new ArrayList<>();
        JSONArray Objects = new JSONArray(data);
        for (int i = 0; i<Objects.length(); i++){
            JSONObject json = Objects.getJSONObject(i);
            int id = Integer.parseInt(json.getString("objectId"));
            int type = Integer.parseInt(json.getString("objectType"));
            String title = json.getString("objectTitle");
            String address = json.getString("objectAddress");
            Object temp = new Object(id, type, title, address);
            objects.add(temp);
        }
        return objects;
    }
    public Object parseObject (String data) throws JSONException {
        JSONObject json = new JSONObject(data);
            int id = Integer.parseInt(json.getString("objectId"));
            int type = Integer.parseInt(json.getString("objectType"));
            String title = json.getString("objectTitle");
            String address = json.getString("objectAddress");
            Object Object = new Object(id, type, title, address);
        return Object;
    }
}

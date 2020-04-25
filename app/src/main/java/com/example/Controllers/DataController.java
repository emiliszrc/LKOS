package com.example.Controllers;

import com.example.Models.Trip;
import com.example.Models.Object;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DataController {

    public ArrayList<Trip> parseAllTrips (String data) throws JSONException {
        ArrayList<Trip> trips = new ArrayList<>();
        JSONArray Trips = new JSONArray(data);
        for (int i = 0; i<Trips.length(); i++){
            JSONObject json = Trips.getJSONObject(i);
            Trip temp = new Trip();//todo add the contructor
        }

        return trips;
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

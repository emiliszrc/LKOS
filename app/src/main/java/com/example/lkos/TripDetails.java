package com.example.lkos;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.Controllers.DataController;
import com.example.Controllers.NetController;
import com.example.Models.Trip;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONException;

import java.util.concurrent.ExecutionException;

public class TripDetails extends AppCompatActivity {
    private SharedPreferences pref;
    private NetController netController = new NetController();
    private DataController dataController = new DataController();
    Button passengerListButton;
    Button tripTimetableButton;
    TextView tripTitle, tripId, dateTimeFrom, dateTimeTo, textView3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_details);
        pref = getSharedPreferences("APPDetails", Context.MODE_PRIVATE);
        passengerListButton = (Button)findViewById(R.id.passengerListButton);
        tripTimetableButton = (Button)findViewById(R.id.tripTimetableButton);
        tripTitle = (TextView) findViewById(R.id.tripTitle);
        tripId = (TextView) findViewById(R.id.tripId);
        dateTimeFrom = (TextView) findViewById(R.id.dateTimeFrom);
        dateTimeTo = (TextView) findViewById(R.id.dateTimeTo);
        textView3 = (TextView) findViewById(R.id.textView3);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.nav_Activity);
        try {
            Trip selectedTrip = dataController.parseTrip(netController.getTripById(pref.getString("token", null),pref.getInt("selectedTrip",13)));
            tripTitle.setText(selectedTrip.getTripTitle());
            tripId.setText("Trip id: "+String.valueOf(selectedTrip.getTripId()));
            String Time;
            Time = selectedTrip.getStartDate().toString();
            Time = Time.substring(0, Time.length() - 13);
            Time = Time.replace("T"," ");
            dateTimeFrom.setText("Start Time: " + Time);
            Time = selectedTrip.getEndDate().toString();
            Time = Time.substring(0, Time.length() - 13);
            Time = Time.replace("T"," ");
            dateTimeTo.setText("End Time: " +Time);
            textView3.setText(selectedTrip.getDescription());
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_Schedule:
                        startActivity(new Intent(getApplicationContext()
                                ,Schedule.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.nav_Trips:
                        startActivity(new Intent(getApplicationContext()
                                ,TripList.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.nav_Activity:
                        startActivity(new Intent(getApplicationContext()
                                ,MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }

                return false;
            }
        });

        passengerListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //button press shows passenger list
                System.out.println("passenger list");
                startActivity(new Intent(getApplicationContext()
                        ,Passengers.class));
                overridePendingTransition(0,0);
            }
        });
        tripTimetableButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //button press shows trip timetable
                System.out.println("trip timetable");
                startActivity(new Intent(getApplicationContext()
                        ,TripTimetable.class));
                overridePendingTransition(0,0);
            }
        });
    }
}

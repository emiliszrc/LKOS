package com.example.lkos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class TripDetails extends AppCompatActivity {

    Button passengerListButton;
    Button tripTimetableButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_details);

        passengerListButton = (Button)findViewById(R.id.passengerListButton);
        tripTimetableButton = (Button)findViewById(R.id.tripTimetableButton);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.nav_Activity);

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

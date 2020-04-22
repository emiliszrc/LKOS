package com.example.lkos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;


import com.example.Controllers.SharedPreferenceController;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private TextView startDateTime, busNo, capacity, firstStopAdress, object, arrivalDateTime, accommodationTitle, upcomingAddress;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startDateTime = (TextView) findViewById(R.id.startDateTime);
        busNo = (TextView) findViewById(R.id.busNr);
        capacity = (TextView) findViewById(R.id.capacity);
        firstStopAdress = (TextView) findViewById(R.id.firstStopAddress);
        object = (TextView) findViewById(R.id.objectTitle);
        arrivalDateTime = (TextView) findViewById(R.id.arrivalDateTime);
        accommodationTitle = (TextView) findViewById(R.id.accommodation);
        upcomingAddress = (TextView) findViewById(R.id.upcomingAddress);

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
                        return true;
                }

                return false;
            }
        });
    }
}

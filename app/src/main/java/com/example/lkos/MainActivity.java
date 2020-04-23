package com.example.lkos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.Models.Trip;
import com.example.Models.Object;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;


import com.example.Controllers.DataController;
import com.example.Controllers.NetController;
import com.example.Controllers.SharedPreferenceController;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONException;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private NetController netController = new NetController();
    private DataController dataController = new DataController();
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

        pref = getSharedPreferences("APPDetails", Context.MODE_PRIVATE);
        editor = pref.edit();

        try {
            Object object = dataController.parseObject(netController.getObjectById(pref.getString("token", null), 1));
        } catch (Exception e) {
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
                        return true;
                }

                return false;
            }
        });
    }
}

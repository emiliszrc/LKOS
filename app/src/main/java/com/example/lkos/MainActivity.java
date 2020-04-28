package com.example.lkos;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.Controllers.DataController;
import com.example.Controllers.NetController;
import com.example.Models.Object;
import com.example.Models.Trip;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private SharedPreferences pref;
    private NetController netController = new NetController();
    private DataController dataController = new DataController();
    private TextView startDateTime, busNo, capacity, firstStopAdress, object, arrivalDateTime, accommodationTitle, upcomingAddress, tvDestination;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvDestination = (TextView) findViewById(R.id.tvDestination);
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

        try {
           Trip FirstTrip = dataController.parseAllTrips(netController.getTrips
                   (pref.getString("token",null))).get(1);
           System.out.println(FirstTrip.getCapacity());
           ArrayList<Object> accommodation = dataController.parseAccomodationObjectsForTrip
                   (netController.getObjectsForTrip(pref.getString("token",null)
                           ,FirstTrip.getTripId()));
           ArrayList<Object> objects = dataController.parseActualObjectsForTrip
                    (netController.getObjectsForTrip(pref.getString("token",null)
                            ,FirstTrip.getTripId()));
           tvDestination.setText(FirstTrip.getTripTitle());
           capacity.setText(String.valueOf(FirstTrip.getCapacity()));
           String StartTime = FirstTrip.getStartDate().toString();
           StartTime = StartTime.replace("T"," ");
           startDateTime.setText(StartTime.substring(0, StartTime.length() - 13));
           firstStopAdress.setText(objects.get(0).getObjectAddress());
           object.setText(objects.get(0).getObjectTitle());
           accommodationTitle.setText(accommodation.get(0).getObjectTitle());
           upcomingAddress.setText(accommodation.get(0).getObjectAddress());
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

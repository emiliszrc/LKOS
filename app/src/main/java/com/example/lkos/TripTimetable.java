package com.example.lkos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class TripTimetable extends AppCompatActivity {

    ListView tripTimetableList;
    String[] dateArray = {"June 6", "June 6", "June 6", "June 6", "June 6", "June 6"};
    String[] timeArray = {"7:00 - 10:00","10:00 - 10:30", "11:00 - 12:00", "11:00 - 12:00", "11:00 - 12:00", "11:00 - 12:00"};
    String[] activityArray = {"Departure to accommodation", "Free time", "Landmark visitation", "Landmark visitation", "Landmark visitation", "Landmark visitation"};
    String[] objectAddressArray = {
            "Hotel Runmis, Panevezio g. 8A, Vilnius",
            "Hotel Runmis, Panevezio g. 8A, Vilnius",
            "Kudirkos paminklas, Gedimino pr. 20, Vilnius",
            "Kudirkos paminklas, Gedimino pr. 20, Vilnius",
            "Kudirkos paminklas, Gedimino pr. 20, Vilnius",
            "Kudirkos paminklas, Gedimino pr. 20, Vilnius"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_timetable);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.nav_Activity);

        tripTimetableList = findViewById(R.id.tripTimetableListView);
        final CustomAdapter customAdapter = new CustomAdapter();
        tripTimetableList.setAdapter(customAdapter);

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
    }
    class CustomAdapter extends BaseAdapter {
        @Override
        public int getCount(){
            return activityArray.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }


        @SuppressLint({"ViewHolder", "InflateParams"})
        @Override
        public View getView(int i, View convertView, ViewGroup parent) {
            convertView = getLayoutInflater().inflate(R.layout.trip_timetable_layout, null);
            TextView date = (TextView)convertView.findViewById(R.id.dateLabel);
            TextView time = (TextView)convertView.findViewById(R.id.timeLabel);
            TextView activity = (TextView)convertView.findViewById(R.id.activityLabel);
            TextView objectAddress = (TextView)convertView.findViewById(R.id.objectAddressLabel);

            date.setText(dateArray[i]);
            time.setText(timeArray[i]);
            activity.setText(activityArray[i]);
            objectAddress.setText(objectAddressArray[i]);

            return convertView;
        }
    }
}

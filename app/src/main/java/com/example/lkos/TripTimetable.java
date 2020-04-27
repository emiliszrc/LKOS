package com.example.lkos;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.Models.Object;
import com.example.Controllers.DataController;
import com.example.Controllers.NetController;

import com.example.Models.Trip;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class TripTimetable extends AppCompatActivity {
    private SharedPreferences pref;
    private NetController netController = new NetController();
    private DataController dataController = new DataController();
    private Button Search;
    private EditText SearchText;
    ListView tripTimetableList;
    ArrayList<String> dateArray = new ArrayList<>();// {"June 6", "June 6", "June 6", "June 6", "June 6", "June 6"};
    ArrayList<String> timeArray = new ArrayList<>();//{"7:00 - 10:00","10:00 - 10:30", "11:00 - 12:00", "11:00 - 12:00", "11:00 - 12:00", "11:00 - 12:00"};
    ArrayList<String> activityArray = new ArrayList<>();//{"Departure to accommodation", "Free time", "Landmark visitation", "Landmark visitation", "Landmark visitation", "Landmark visitation"};
    ArrayList<String> objectAddressArray = new ArrayList<>();//

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_timetable);
        SearchText = (EditText)findViewById(R.id.SearchField);
        Search = (Button)findViewById(R.id.SearchButton);
        pref = getSharedPreferences("APPDetails", Context.MODE_PRIVATE);
        try {
            ArrayList<Object> objects = dataController.parseAllObjectsForTrip
                    (netController.getObjectsForTrip(pref.getString("token", null),pref.getInt("selectedTrip", 13)));
            for (int i = 0; i<objects.size();i++){
                activityArray.add(objects.get(i).getObjectTitle());
                objectAddressArray.add(objects.get(i).getObjectAddress());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.nav_Activity);

        tripTimetableList = findViewById(R.id.tripTimetableListView);
        final CustomAdapter customAdapter = new CustomAdapter();
        tripTimetableList.setAdapter(customAdapter);
        Search.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String searchCriteria = SearchText.getText().toString();
                try {
                    String Time;
                    ArrayList<Object> objects;
                    activityArray.clear();
                    objectAddressArray.clear();
                    if (searchCriteria.equals(""))
                        objects = dataController.parseAllObjectsForTrip
                                (netController.getObjectsForTrip(pref.getString("token", null),pref.getInt("selectedTrip", 13)));
                    else
                        objects = dataController.parseAllObjects
                                (netController.searchForObjects(pref.getString("token", null),searchCriteria));
                    for (int i = 0; i<objects.size();i++){
                        activityArray.add(objects.get(i).getObjectTitle());
                        objectAddressArray.add(objects.get(i).getObjectAddress());
                    }
                    tripTimetableList.invalidateViews();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
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
            return activityArray.size();
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

            activity.setText(activityArray.get(i));
            objectAddress.setText(objectAddressArray.get(i));

            return convertView;
        }
    }
}

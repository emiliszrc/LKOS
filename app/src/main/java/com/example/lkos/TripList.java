package com.example.lkos;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.Controllers.DataController;
import com.example.Controllers.NetController;
import com.example.Models.Trip;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


public class TripList extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private SharedPreferences pref;
    private NetController netController = new NetController();
    private DataController dataController = new DataController();
    private Button Search;
    private EditText SearchText;
    ListView tripList;
    ArrayList<String> tripTitleArray = new ArrayList<>();// = {"Trip title", "Trip title", "Trip title", "Trip title", "Trip title", "Trip title7"};
    ArrayList<String> dateFromArray = new ArrayList<>();// = {"2020 00 00", "2020 00 00", "2020 00 00", "2020 00 00", "2020 00 00", "2020 00 00"};
    ArrayList<String> dateToArray = new ArrayList<>();// = {"2020 00 00", "2020 00 00", "2020 00 00", "2020 00 00", "2020 00 00", "2020 00 00"};
    ArrayList<String> capacityArray = new ArrayList<>();// = {"Capacity 20/30", "Capacity 20/30", "Capacity 20/30", "Capacity 20/30", "Capacity 20/30", "Capacity 20/30"};
    ArrayList<String> tripIdArray = new ArrayList<>();// = {"Trip ID 7d89s6", "Trip ID 7d89s6", "Trip ID 7d89s6", "Trip ID 7d89s6", "Trip ID 7d89s6", "Trip ID 7d89s6"};

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_list);
        SearchText = (EditText)findViewById(R.id.SearchField);
        Search = (Button)findViewById(R.id.SearchButton);
        pref = getSharedPreferences("APPDetails", Context.MODE_PRIVATE);
        try {
            String Time;
            ArrayList<Trip> trips = dataController.parseAllTrips
                    (netController.getTrips(pref.getString("token", null)));
            for (int i = 0; i<trips.size();i++){
                tripTitleArray.add(trips.get(i).getTripTitle());
                Time = trips.get(i).getStartDate().toString();
                Time = Time.substring(0, Time.length() - 13);
                Time = Time.replace("T"," ");
                dateFromArray.add(Time);
                Time = trips.get(i).getEndDate().toString();
                Time = Time.substring(0, Time.length() - 13);
                Time = Time.replace("T"," ");
                dateToArray.add(Time);
                capacityArray.add(String.valueOf(trips.get(i).getCapacity()));
                tripIdArray.add(String.valueOf(trips.get(i).getTripId()));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        setContentView(R.layout.activity_trip_list);
        final Drawable logout = getResources().getDrawable(R.drawable.ic_exit_to_app);
        final Drawable logout = getResources().getDrawable(R.drawable.ic_exit_to_app);
      
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //logout.setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
       // getActionBar().setHomeAsUpIndicator(logout);


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.nav_Trips);

        tripList = findViewById(R.id.tripListView);
        final CustomAdapter customAdapter = new CustomAdapter();
        tripList.setAdapter(customAdapter);
        tripList.setOnItemClickListener(this);
        Search.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String searchCriteria = SearchText.getText().toString();
                try {
                    String Time;
                    ArrayList<Trip> trips;
                    tripTitleArray.clear();
                    dateFromArray.clear();
                    dateToArray.clear();
                    capacityArray.clear();
                    tripIdArray.clear();
                    if (searchCriteria.equals(""))
                        trips = dataController.parseAllTrips
                                (netController.getTrips(pref.getString("token", null)));
                    else
                    trips = dataController.parseAllTrips
                            (netController.searchForTrips(pref.getString("token", null),searchCriteria));
                    for (int i = 0; i<trips.size();i++){
                        tripTitleArray.add(trips.get(i).getTripTitle());
                        Time = trips.get(i).getStartDate().toString();
                        Time = Time.substring(0, Time.length() - 13);
                        Time = Time.replace("T"," ");
                        dateFromArray.add(Time);
                        Time = trips.get(i).getEndDate().toString();
                        Time = Time.substring(0, Time.length() - 13);
                        Time = Time.replace("T"," ");
                        dateToArray.add(Time);
                        capacityArray.add(String.valueOf(trips.get(i).getCapacity()));
                        tripIdArray.add(String.valueOf(trips.get(i).getTripId()));
                    }
                    tripList.invalidateViews();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });


/*
        SearchView searchView = (SearchView)findViewById(R.id.search_bar);
        searchView.setQueryHint("Search here!");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                if (newText != null && !newText.isEmpty()){
                    //turi isfiltruoti bet dar nzn kaip padaryti

            }

                return false;
            }
        });

 */


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_Activity:
                        startActivity(new Intent(getApplicationContext()
                                ,MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.nav_Schedule:
                        startActivity(new Intent(getApplicationContext()
                                ,Schedule.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.nav_Trips:
                        return true;
                }
                return false;
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        SharedPreferences.Editor editor;
        editor = pref.edit();
        editor.putInt("selectedTrip",Integer.parseInt(tripIdArray.get(position)));
        editor.commit();
      
        startActivity(new Intent(getApplicationContext()
                , TripDetails.class));
        overridePendingTransition(0,0);

        System.out.println("id " + position );




    }

    class CustomAdapter extends BaseAdapter{
        @Override
        public int getCount(){
            return tripTitleArray.size();
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
            convertView = getLayoutInflater().inflate(R.layout.trip_list_layout, null);
            TextView tripTitle = (TextView)convertView.findViewById(R.id.tripTitle);
            TextView dateFrom = (TextView)convertView.findViewById(R.id.dateFrom);
            TextView dateTo = (TextView)convertView.findViewById(R.id.dateTo);
            TextView capacity = (TextView)convertView.findViewById(R.id.capacity);
            TextView tripId = (TextView)convertView.findViewById(R.id.tripId);

            tripTitle.setText(tripTitleArray.get(i));
            dateFrom.setText(dateFromArray.get(i));
            dateTo.setText(dateToArray.get(i));
            capacity.setText(capacityArray.get(i));
            tripId.setText(tripIdArray.get(i));


            return convertView;
        }
    }
}

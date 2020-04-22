package com.example.lkos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;


import com.google.android.material.bottomnavigation.BottomNavigationView;


public class TripList extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView tripList;
    String[] tripTitleArray = {"Trip title", "Trip title", "Trip title", "Trip title", "Trip title", "Trip title7"};
    String[] dateFromArray = {"2020 00 00", "2020 00 00", "2020 00 00", "2020 00 00", "2020 00 00", "2020 00 00"};
    String[] dateToArray = {"2020 00 00", "2020 00 00", "2020 00 00", "2020 00 00", "2020 00 00", "2020 00 00"};
    String[] capacityArray = {"Capacity 20/30", "Capacity 20/30", "Capacity 20/30", "Capacity 20/30", "Capacity 20/30", "Capacity 20/30"};
    String[] tripIdArray = {"Trip ID 7d89s6", "Trip ID 7d89s6", "Trip ID 7d89s6", "Trip ID 7d89s6", "Trip ID 7d89s6", "Trip ID 7d89s6"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_list);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.nav_Trips);

        tripList = findViewById(R.id.tripListView);
        final CustomAdapter customAdapter = new CustomAdapter();
        tripList.setAdapter(customAdapter);

        tripList.setOnItemClickListener(this);

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

        //on trip click

        startActivity(new Intent(getApplicationContext()
                , TripDetails.class));
        overridePendingTransition(0,0);

    }

    class CustomAdapter extends BaseAdapter{
        @Override
        public int getCount(){
            return tripTitleArray.length;
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
            TextView tripId = (TextView)convertView.findViewById(R.id.tripTitle);

            tripTitle.setText(tripTitleArray[i]);
            dateFrom.setText(dateFromArray[i]);
            dateTo.setText(dateToArray[i]);
            capacity.setText(capacityArray[i]);
            tripId.setText(tripIdArray[i]);


            return convertView;
        }
    }
}

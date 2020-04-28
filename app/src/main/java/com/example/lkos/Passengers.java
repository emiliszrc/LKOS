package com.example.lkos;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Passengers extends AppCompatActivity {

    ListView passengersList;
    String[] passengerNames = {"Jonas", "Jonas", "Jonas", "Jonas", "Jonas", "Jonas"};
    String[] passengerLastNames = {"Puodas", "Puodas", "Puodas", "Puodas", "Puodas", "Puodas"};
    String[] passengerSeats = {"Seat No. 23", "Seat No. 23", "Seat No. 23", "Seat No. 23", "Seat No. 23", "Seat No. 23"};
    String[] passengerPhoneNumbers = {"864356785", "864356785", "864356785", "864356785", "864356785", "864356785" };
    CheckBox checkBox;
    boolean flag;
    Button selectAll, unselectAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       // setContentView(R.layout.passengers_layout);
       // checkBox = (CheckBox)findViewById(R.id.checkBox);

        setContentView(R.layout.activity_passengers);
        selectAll = (Button)findViewById(R.id.selectAll);
        unselectAll = (Button)findViewById(R.id.unselectAll);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.nav_Activity);

        passengersList = findViewById(R.id.passengersListView);
        final CustomAdapter customAdapter = new CustomAdapter();
        passengersList.setAdapter(customAdapter);

        selectAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = true;
                customAdapter.notifyDataSetChanged();
            }
        });
        unselectAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = false;
                customAdapter.notifyDataSetChanged();
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
            return passengerNames.length;
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
            convertView = getLayoutInflater().inflate(R.layout.passengers_layout, null);
            TextView name = (TextView)convertView.findViewById(R.id.passengerName);
            TextView lastName = (TextView)convertView.findViewById(R.id.passengerLastName);
            TextView seatNo = (TextView)convertView.findViewById(R.id.passengerSeatNo);
            TextView phoneNumber = (TextView)convertView.findViewById(R.id.passengerPhoneNumber);
            CheckBox checkBox = (CheckBox)convertView.findViewById(R.id.checkBox);

            name.setText(passengerNames[i]);
            lastName.setText(passengerLastNames[i]);
            seatNo.setText(passengerSeats[i]);
            phoneNumber.setText(passengerPhoneNumbers[i]);
            if (flag==true){
                checkBox.setChecked(true);
            }
            return convertView;
        }
    }

}


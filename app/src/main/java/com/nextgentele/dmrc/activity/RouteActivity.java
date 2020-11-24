package com.nextgentele.dmrc.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.nextgentele.dmrc.R;
import com.nextgentele.dmrc.roomdb.AppDatabase;
import com.nextgentele.dmrc.roomdb.tables.RouteMaster;

import java.util.ArrayList;
import java.util.List;

public class RouteActivity extends AppCompatActivity {

    Button submmit;
    AppDatabase appDatabase;
    Spinner spinner, frSpinner, toSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route);  
        submmit=findViewById(R.id.submitbtn);
        spinner=findViewById(R.id.spinner1);
        frSpinner=findViewById(R.id.spinner2);
        toSpinner=findViewById(R.id.spinner3);
        appDatabase=AppDatabase.getAppDatabase(this);
         loadSpinnerData();

        submmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(RouteActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {




            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void loadSpinnerData() {

        List<RouteMaster> routes=appDatabase.RouteMasterDao().getList();
        List<String> labels = new ArrayList<>();

        String r1="Select the Trip";
        labels.add(r1);
        for (RouteMaster route:routes )
        {
            String rm=route.id+" - "+route.routeNo+" - "+route.srcStation+" - "+route.destStation;
            labels.add(rm);
        }

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, labels);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner

        spinner.setSelection(0);
        spinner.setAdapter(dataAdapter);
        
    }
}
package com.nextgentele.dmrc.activity;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import android.widget.TextClock;
import android.widget.TextView;

import com.nextgentele.dmrc.R;

import com.nextgentele.dmrc.util.CardLibInterfaceClass;


import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity{


    TextView dateView, currentStop, newStop;
    TextClock tClock;
    CardLibInterfaceClass cardLibObj;

    TextView busTV,depotTV, routeTV,fromTV, toTV;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cardLibObj=new CardLibInterfaceClass(this);


        dateView = findViewById(R.id.date_activity_main);
        currentStop = findViewById(R.id.cStop_activity_main);
        newStop = findViewById(R.id.nStop_activity_main);
        tClock = findViewById(R.id.time_activity_main);
        busTV=findViewById(R.id.bus_activity_main);
        depotTV=findViewById(R.id.depot_activity_main);
        routeTV=findViewById(R.id.routno_activity_main);
        fromTV=findViewById(R.id.source_activity_main);
        toTV=findViewById(R.id.des_activity_main);
        setDate(dateView);


        cardLibObj.findCard();
    }

    private void setDate(TextView dateView) {
        Date today = Calendar.getInstance().getTime();//getting date
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy");
        String str = formatter.format(today);
        dateView.setText(str);
    }

}
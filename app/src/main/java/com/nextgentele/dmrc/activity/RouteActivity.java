package com.nextgentele.dmrc.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.nextgentele.dmrc.R;

public class RouteActivity extends AppCompatActivity {

    Button submmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route);

        submmit=findViewById(R.id.submitbtn);

        submmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(RouteActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
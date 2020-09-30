package com.nextgentele.dmrc.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nextgentele.dmrc.R;
import com.nextgentele.dmrc.db.MyDb;
import com.nextgentele.dmrc.db.Users;
import com.nextgentele.dmrc.util.UartClass;

public class SecondActivity extends AppCompatActivity {

    TextView availableBalance;
    TextView ok;
    LinearLayout buttonView, textViewInsifficient;
    Users users;
    MyDb myDb;
    UartClass uartClass;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        availableBalance = findViewById(R.id.aCardBal_activity_second);
        ok=findViewById(R.id.btnOk);
        buttonView = findViewById(R.id.okButton);
        textViewInsifficient=findViewById(R.id.insufficientText);
        uartClass=new UartClass();

        users= (Users) getIntent().getSerializableExtra("user");

        if (users!=null){
        availableBalance.setText("INR "+ users.getUserBal());

        if (users.getUserBal()<10){
            buttonView.setVisibility(View.GONE);
            textViewInsifficient.setVisibility(View.VISIBLE);
        }else{
            buttonView.setVisibility(View.VISIBLE);
            textViewInsifficient.setVisibility(View.GONE);
            myDb=new MyDb(this);
            boolean result= myDb.insertData(users);
            if (!result){
                Log.e("SecondActivity","Data Not inserted");
            }else {
                uartClass.init();
                uartClass.uart_open();
//                uartClass.uart_start();
                uartClass.uart_send("GO11");
                Log.i("SecondActivity","Data inserted");
            }
        }}

//        ok.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(SecondActivity.this,MainActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        });


        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                Intent intent=new Intent(SecondActivity.this,MainActivity.class);
                startActivity(intent);
                finish();

            }
        }, 5000);



    }
}
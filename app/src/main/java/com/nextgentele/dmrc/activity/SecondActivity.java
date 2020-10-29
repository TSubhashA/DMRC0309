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

import com.newcapec.czpos.auxlib.utils.MyUtils;
import com.nextgentele.dmrc.R;
import com.nextgentele.dmrc.db.MyDb;
import com.nextgentele.dmrc.db.Users;
import com.nextgentele.dmrc.response.ResponseReceived;
import com.nextgentele.dmrc.util.UartClass;

import java.io.ByteArrayInputStream;

import static com.nextgentele.dmrc.response.ResponseReceived.pszBuf;

public class SecondActivity extends AppCompatActivity {

    TextView availableBalance;
    TextView ok;
    LinearLayout buttonView, textViewInsifficient;
    Users users;
    MyDb myDb;
    UartClass uartClass;

    boolean ifEnd;

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
                uartClass.uart_send("GO11");
                Log.i("SecondActivity","Data inserted");
            }
        }}

        String res="PASS";
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                String res="Pass";
                if (ResponseReceived.pszBuf== res.getBytes()){
                Intent intent=new Intent(SecondActivity.this,MainActivity.class);
                startActivity(intent);
                finish();}else
                {}

            }
        }, 4000);

        Runnable runnable1 = new Runnable() {
            @Override
            public void run(){
                int count = 0;
                ifEnd = false;
                while (!ifEnd) {
                    try{
                        Thread.sleep(100);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if( pszBuf == null ) {
                        continue;
                    }
                    count ++;
                    try
                    {
                        String recvStr = MyUtils.bytes2HexStr1(pszBuf);
                        if( recvStr.length() > 0 && recvStr.equals(res)){
                            Intent intent=new Intent(SecondActivity.this,MainActivity.class);
                            startActivity(intent);
                            finish();
                            Log.i( "recv : "+count," :"+recvStr );
                        }
                    } catch(NullPointerException e)	{
                        e.printStackTrace();
                    }
                }
            }
        };
        new Thread(runnable1).start();

    }
}
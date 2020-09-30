package com.nextgentele.dmrc.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;

import android.os.Handler;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;

import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.location.FusedLocationProviderClient;
import com.newcapec.b601.sys.HwGpio;
import com.newcapec.czpos.auxlib.CardData;
import com.newcapec.czpos.auxlib.NcCardLib;
import com.newcapec.czpos.auxlib.utils.ByteUtil;
import com.newcapec.czpos.auxlib.utils.MyUtils;
import com.newcapec.jni.SerialPort;
import com.nextgentele.dmrc.R;
import com.nextgentele.dmrc.apis.ApiClient;
import com.nextgentele.dmrc.apis.ApiInterface;
import com.nextgentele.dmrc.apis.apiModel.LoginModule;
import com.nextgentele.dmrc.apis.apiModel.LoginPayload;
import com.nextgentele.dmrc.apis.apiModel.LoginResponse;
import com.nextgentele.dmrc.db.MyDb;
import com.nextgentele.dmrc.db.Users;
import com.nextgentele.dmrc.lib.CapecMifareClassicLib;
import com.nextgentele.dmrc.lib.CardLibInterface;
import com.nextgentele.dmrc.lib.ResultInterface;
import com.nextgentele.dmrc.util.ConnectionDetector;
import com.nextgentele.dmrc.util.UartClass;


import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements LocationListener {


    TextView dateView, currentStop, newStop;
    TextClock tClock;
    CardLibInterface cardLibObj;
    String userName;
    long dateTimeIn;
    long dateTimeOut;
    String latIn = "37.42166666";
    String LongIn = "122.04578899";
    String latOut = "37.42166666";
    String LongIOut = "122.04578899";
    float amount;
    Location location;


    UartClass uart;
    Users users;
    MyDb myDb;

    LocationManager locationManager;

    Intent i;

    boolean ifEnd = false;
    final private int UART_PORT = 1;
    final private int BAUD_RATE = 9600;
    private boolean mOpenFlag = false;

    SerialPort serial;
    HwGpio hw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dateView = findViewById(R.id.date_activity_main);
        currentStop = findViewById(R.id.cStop_activity_main);
        newStop = findViewById(R.id.nStop_activity_main);
        tClock = findViewById(R.id.time_activity_main);
        myDb = new MyDb(this);
        setDate(dateView);
        uart = new UartClass();

        init();


        cardLibObj.findCard();
        //cardLibObj.creditCardBal(100);
        //displayToast();

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        boolean enabled = locationManager
                .isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (enabled) {
            Criteria criteria = new Criteria();
            String bestProvider = String.valueOf(locationManager.getBestProvider(criteria, false));

            try {
                Log.i("Location", "Started");
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                    return;
                }else{
                location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
               Log.i("Location","Ended");}
            }catch (Exception e){
                Toast.makeText(this, ""+e, Toast.LENGTH_LONG).show();
                Log.i("Exception : ", String.valueOf(e));
            }
            Log.i("Location_Result",String.valueOf(location));
            if (location != null) {
                Log.e("TAG", "GPS is on");
                onLocationChanged(location);
                //Toast.makeText(MainActivity.this, "latitude:" + location.getLatitude() + " longitude:" + location.getLatitude(), Toast.LENGTH_SHORT).show();

            } else {
                //This is what you need:
                //locationManager.requestLocationUpdates(bestProvider, 0, 0, this);
                Toast.makeText(this, "GPS is Null", Toast.LENGTH_SHORT).show();
                java.util.Date date=new java.util.Date();

                Log.i("Date", String.valueOf(date));
            }
        }

    }

    @Override
    public void onLocationChanged(Location location) {
        //Toast.makeText(this, "Location : "+location.getLatitude()+" : "+ location.getLatitude(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProviderDisabled(String provider) {
        Log.d("Latitude","disable");
    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.d("Latitude","enable");
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.d("Latitude","status");
    }


    void init(){
       hw = new HwGpio();
       uart.init();
        Log.i("Hardware","GPIO");
        cardLibObj = CapecMifareClassicLib.getInstance(MainActivity.this, new ResultInterface() {

            @Override
            public void onResult(CMD_LIST currCmd, Object result) {
                switch (currCmd){
                    case FIND_CARD:
                        System.out.println("UID: " + (String) result);
                        //cardLibObj.creditCardBal(100);
                        userName=(String) result;
                        Users value = myDb.getUser(userName);
                        if(value.getUserBal()==0 || value.getUserName()==null || value.getUserBal()<10){
                            cardLibObj.getCardBal();
                        }else if (value.getUserBal()>=10)
                        {
                            i=new Intent(MainActivity.this,ThirdActivity.class);
                            i.putExtra("user",value);
                            cardLibObj.debitCardBal(25);
                            //finish();
                        }

                        //cardLibObj.creditCardBal(5);
                        //cardLibObj.getCardBal();
                        break;
                    case CREDIT:
                        System.out.println("Current Card Value after Credit: " + (int)result);
                        //cardLibObj.getCardBal();
                        break;
                    case DEBIT:
                        System.out.println("Current Card Value after Debit: " + (int)result);
//                        uart_open();
//                        uart_send("GO10");
                        startActivity(i);

                        break;
                    case GET_BAL:
                        System.out.println("Current Card Value: " + (int)result);
                        amount= (int) result;
                        java.util.Date date=new java.util.Date();
                        dateTimeIn = date.getTime();
                        if (amount!=-1){
                        users=new Users(userName,amount,dateTimeIn,0,latIn,LongIn,null,null,0);
                        Intent i=new Intent(MainActivity.this,SecondActivity.class);
                        i.putExtra("user",users);
                        if  (amount>10){
                             uart_open();
                            uart_send("GO11");
                        }
                        startActivity(i);}
                        //cardLibObj.creditCardBal(10000);
                        //finish();
                        break;
                }
            }
        });
    }

    private void setDate(TextView dateView) {
        Date today = Calendar.getInstance().getTime();//getting date
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy");
        String str = formatter.format(today);
        dateView.setText(str);
    }


    private void uart_send(String msg1) {

        String msg0 = msg1;
//		byte[] msg = MyUtils.string2ByteArray(msg0);
        byte[] msg = ByteUtil.toGB2312(msg0);
        Log.d("MainActivity", msg0);
        if(serial!=null) {
            int res=serial.sendMsg(msg);
            Log.i("Response", String.valueOf(res));
        }
    }

    private void uart_start() {

        int ret = serial.open(1, BAUD_RATE, 1);
        if( ret != 0){
            mOpenFlag = false;
            Log.i("uart_start: ","------open uart port fail------");
        }else {
            mOpenFlag = true;
            Log.i("uart_start: ","------open uart port succ------");
        }

        Runnable runnable = new Runnable() {
            @Override
            public void run(){
                byte[] pszBuf = new byte[1000];
                int count = 0;
                ifEnd = false;
                while (!ifEnd) {
                    try{
                        Thread.sleep(100);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    pszBuf = serial.recvMsg();
                    if( pszBuf == null ) {
                        continue;
                    }
                    count ++;
                    try
                    {
//						String recvStr = new String(pszBuf).trim();
                        String recvStr = MyUtils.bytes2HexStr1(pszBuf);
                        if( recvStr.length() > 0 ){
                            Log.i( "recv"+count," :"+recvStr );
                        } else {
                        }
                    } catch(NullPointerException e)	{
                        e.printStackTrace();
                    }
                }
            }
        };
        new Thread(runnable).start();
    }



    private void uart_open() {
        Log.i("uart start","Starting");
        hw.HW_UartMode_Set(HwGpio.UART_MODE_RS232);
        if(serial==null) {
            serial = new SerialPort();
            uart_start();
        }
    }

}
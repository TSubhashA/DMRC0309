package com.nextgentele.dmrc.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.nextgentele.dmrc.R;
import com.nextgentele.dmrc.apis.ApiClient;
import com.nextgentele.dmrc.apis.ApiInterface;
import com.nextgentele.dmrc.apis.apiModel.LoginModule;
import com.nextgentele.dmrc.apis.apiModel.LoginResponse;
import com.nextgentele.dmrc.db.MyDb;
import com.nextgentele.dmrc.db.Users;
import com.nextgentele.dmrc.util.ConnectionDetector;
import com.nextgentele.dmrc.util.UartClass;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThirdActivity extends AppCompatActivity {

    TextView currentBalance, fromTo, charges, finalBalance;
    Users users;

    MyDb myDb;
    String latOut = "37.42166666";
    String LongIOut = "122.04578899";
    UartClass uartClass;
    String sdf="";

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        sdf = getIntent().getStringExtra("qr1123");

        uartClass=new UartClass();

        currentBalance = findViewById(R.id.aCardBal_activity_second);
        fromTo = findViewById(R.id.fareFromto_third_activity);
        charges = findViewById(R.id.fareDeduct_activity_second);
        finalBalance = findViewById(R.id.newBalance_activity_second);
        myDb = new MyDb(this);

        String from = "Dwarka";
        String to = "Noida";
        double charge = 25.00f;

        users = (Users) getIntent().getSerializableExtra("user");
        if (users != null) {
            java.util.Date date = new java.util.Date();
            long dateTimeOut = date.getTime();
            myDb.update(users.getUserName(), dateTimeOut, latOut, LongIOut, charge);
            currentBalance.setText("INR " + users.getUserBal());
            fromTo.setText("Fare deducted from " + from + " to " + to);
//            sendData(users.getUserName());
            charges.setText("INR " + charge);
            double bal = users.getUserBal() - charge;
            finalBalance.setText("INR " + bal);

            int result = myDb.deleteUser(users.getUserName());
            Log.i("Delete","User");
            if (result > 0) {
                Log.i("ThirdActivity", "Deleted Data");
                Toast.makeText(ThirdActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
            }

        }

        //QR Reader

        if (!Objects.equals(sdf, "")){
            Toast.makeText(this, ""+sdf, Toast.LENGTH_SHORT).show();
        }
//

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                Intent intent = new Intent(ThirdActivity.this, MainActivity.class);
                startActivity(intent);
                finish();

            }
        }, 3000);

    }


        public void sendData (String username){
            Users users = myDb.getUser(username);

            ConnectionDetector connectionDetector = new ConnectionDetector(this);
            if (connectionDetector.isConnectingToInternet()) {
                Toast.makeText(this, "Internet access", Toast.LENGTH_SHORT).show();


//                LoginModule loginModule = new LoginModule(users.getUserName(), users.getDateTimeIn(), users.getDatetimeOut(), users.getLatIn(), users.getLongIn(), users.getLatOut(), users.getLongOut(), users.getFare());
//
//
//                Gson gson = new Gson();
//                String json = gson.toJson(loginModule);
//                LoginModule deserialized = gson.fromJson(json, LoginModule.class);
//
//                ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
//
//                Call<LoginResponse> call = apiInterface.login(deserialized);
//
//                call.enqueue(new Callback<LoginResponse>() {
//                    @Override
//                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
//                        Log.i("onResponse", "" + response);
//                        if (response.code() == 200) {
//                            LoginResponse loginResponse = response.body();
//                            Toast.makeText(ThirdActivity.this, ""+loginModule, Toast.LENGTH_SHORT).show();
//                            assert loginResponse != null;
//                            if (loginResponse.getStatusCode() == 200) {
//                                int result = myDb.deleteUser(loginResponse.getResponseEntity().getCardId());
//                                Log.i("Delete","User");
//                                if (result > 0) {
//                                    Log.i("ThirdActivity", "Deleted Data");
//                                    Toast.makeText(ThirdActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
//                                    uartClass.init();
//                                    uartClass.uart_open();
//                                    uartClass.uart_send("GO10");
//                                }
//
////                                Log.i("Api response",""+jsonObject);
////                                Toast.makeText(ThirdActivity.this, ""+jsonObject, Toast.LENGTH_SHORT).show();
//
//                            }
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<LoginResponse> call, Throwable t) {
//
//                        Log.i("Failure",t.getCause().toString());
//
//
//                    }
//                });

            }

        }

}
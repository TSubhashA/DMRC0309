package com.nextgentele.dmrc.activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.nextgentele.dmrc.R;

import com.nextgentele.dmrc.adapter.MyAdapter;
import com.nextgentele.dmrc.apis.ApiClient;
import com.nextgentele.dmrc.apis.ApiInterface;
import com.nextgentele.dmrc.apis.apiModel.LoginModule;
import com.nextgentele.dmrc.apis.apiModel.LoginPayload;
import com.nextgentele.dmrc.apis.apiModel.LoginResponse;
import com.nextgentele.dmrc.apis.apiModel.LoginResponsePayload;
import com.nextgentele.dmrc.apis.apiModel.MasterRequest;
import com.nextgentele.dmrc.apis.apiModel.MasterRequestPayload;
import com.nextgentele.dmrc.apis.apiModel.MasterResponse;
import com.nextgentele.dmrc.constants.Constants;
import com.nextgentele.dmrc.pref.AppPreferences;
import com.nextgentele.dmrc.pref.VariablesConstant;
import com.nextgentele.dmrc.roomdb.AppDatabase;
import com.nextgentele.dmrc.roomdb.tables.DriverDetails;
import com.nextgentele.dmrc.roomdb.tables.RouteMaster;
import com.nextgentele.dmrc.service.MyService;
import com.nextgentele.dmrc.util.CheckPermission;
import com.nextgentele.dmrc.util.ConnectionDetector;
import com.nextgentele.dmrc.util.PermissionManagerUtil;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    Button btn;
    TabLayout tabLayout;
    ViewPager viewPager;

    ConnectionDetector cd;
    ApiInterface apiInterface;
    PermissionManagerUtil pm;

    DriverDetails driverDetails;
    List<RouteMaster> routeMasterList;

    LoginModule loginModule;
    MasterRequest masterRequest;


    AppDatabase appDatabase;


    private static final int GPS_PERMISSION = 100;
    private static final int STORAGE_PERMISSION = 101;
    private static final int GPS_PERMISSION_COARPSE = 102;

    CheckPermission checkPermission;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        checkPermission = new CheckPermission(this);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        tabLayout.addTab(tabLayout.newTab().setText("Login ID"));
        tabLayout.addTab(tabLayout.newTab().setText("mPin"));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final MyAdapter adapter = new MyAdapter(this,getSupportFragmentManager(),
                tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });


        cd=new ConnectionDetector(this);
        pm=new PermissionManagerUtil(this);



        Constants.ipAddress = pm.getLocalIpAddress();

        Constants.imei = pm.showPhoneStatePermission();


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isvalidation()) {
                    if (cd.isConnectingToInternet()) {
                        final ProgressDialog progressdialog = ProgressDialog.show(
                                LoginActivity.this, "Please wait",
                                "Loading please wait..", true);
                        progressdialog.show();
                        progressdialog.setCancelable(true);
                        loadData();

                        apiInterface = ApiClient.getClient().create(ApiInterface.class);

                        Call<LoginResponse> call = apiInterface.login(loginModule);

                        call.enqueue(new Callback<LoginResponse>() {
                            @Override
                            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                                if (response.code() == 200) {
                                    LoginResponse loginResponse = response.body();
                                    assert loginResponse != null;
                                    if (loginResponse.getStatus() == 200) {
                                        List<LoginResponsePayload> loginResponsePayload = loginResponse.getPayload();
                                        if (loginResponse.getMessage().equals("Login Successful")) {

                                            appDatabase=AppDatabase.getAppDatabase(LoginActivity.this);

                                            driverDetails= loginResponse.getPayload().get(0);
                                            appDatabase.driverDao().addDriver(driverDetails);
                                            Toast.makeText(LoginActivity.this, "DriverData Added", Toast.LENGTH_SHORT).show();


                                            AppPreferences.setAppPrefrences(VariablesConstant.EMP_CODE, loginResponsePayload.get(0).getEmpCode(), LoginActivity.this);

                                            AppPreferences.setAppPrefrences(VariablesConstant.BUS,loginResponsePayload.get(0).getBusAssigned(),LoginActivity.this);
                                            AppPreferences.setAppPrefrences(VariablesConstant.DEPOT,loginResponsePayload.get(0).getDepotName(),LoginActivity.this);
                                            AppPreferences.setAppPrefrences(VariablesConstant.MOBILE, loginResponsePayload.get(0).getMobile(), LoginActivity.this);

                                            loadData1();
                                            Call<MasterResponse> call1=apiInterface.getMaster(masterRequest);
                                            call1.enqueue(new Callback<MasterResponse>() {
                                                @Override
                                                public void onResponse(Call<MasterResponse> call, Response<MasterResponse> response) {
                                                    if (response.code()==200){
                                                        MasterResponse masterResponse=response.body();
                                                        if (masterResponse.getStatus()==200) {

                                                            routeMasterList=new ArrayList<>();

                                                            for (int i=0;i<masterResponse.getPayload().get(0).getRoutes().size();i++){
                                                                RouteMaster routeMaster;
                                                            routeMaster=masterResponse.getPayload().get(0).getRoutes().get(i);
                                                            Log.i("ErorDB",String.valueOf(routeMaster));
                                                            routeMasterList.add(routeMaster);
                                                            }
                                                            appDatabase.RouteMasterDao().addRoutes(routeMasterList);

                                                        }

                                                    }


                                                }

                                                @Override
                                                public void onFailure(Call<MasterResponse> call, Throwable t) {

                                                }
                                            });


                                            progressdialog.dismiss();
                                            Intent intent = new Intent(LoginActivity.this, RouteActivity.class);
                                            startActivity(intent);
                                        } else if (loginResponse.getMessage().equals("Mobile or Password wrong")) {
                                            progressdialog.dismiss();
                                            Toast.makeText(LoginActivity.this, "Mobile or Password wrong", Toast.LENGTH_SHORT).show();
                                        } else {
                                            progressdialog.dismiss();
                                            Toast.makeText(LoginActivity.this, "Error : " + loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                                        }

                                    } else {
                                        progressdialog.dismiss();
                                        Toast.makeText(LoginActivity.this, "Error : " + loginResponse.getMessage(), Toast.LENGTH_SHORT).show();

                                    }
                                }else {
                                    progressdialog.dismiss();
                                    Toast.makeText(LoginActivity.this, "Server Error : ", Toast.LENGTH_SHORT).show();

                                }
                            }

                            @Override
                            public void onFailure(Call<LoginResponse> call, Throwable t) {

                                progressdialog.dismiss();
                                Toast.makeText(LoginActivity.this, "Failed : " + t.getCause(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }



            }
        });


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            checkPermission.checkPermission(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    GPS_PERMISSION);
            checkPermission.checkPermission(
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    GPS_PERMISSION_COARPSE);

            checkPermission.checkPermission(
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    STORAGE_PERMISSION);
        } else {
            startService(new Intent(this, MyService.class));
            Log.i("StartService", "sarted");
        }
    }

    private void loadData1() {
        int channelId=1;
        String tokenId="tokn1234";

        String mobile=AppPreferences.getAppPrefrences(VariablesConstant.MOBILE,this);
        String deviceId=pm.showPhoneStatePermission();

        MasterRequestPayload payload=new MasterRequestPayload(mobile,deviceId);

        masterRequest =new MasterRequest(channelId,tokenId,payload);

    }

    private void loadData() {

        int channelId=1;
        String tokenId="tok1234";

        String mobile=mobileET.getText().toString().trim();
        String password=pswET.getText().toString();
        String deviceId= pm.showPhoneStatePermission();

        LoginPayload payload=new LoginPayload(mobile,password,deviceId);

        loginModule=new LoginModule(channelId,tokenId,payload);


    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode,
                permissions,
                grantResults);

        if (requestCode == GPS_PERMISSION) {

            // Checking whether user granted the permission or not.
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                // Showing the toast message
                Toast.makeText(LoginActivity.this,
                        "GPS Permission Granted",
                        Toast.LENGTH_SHORT)
                        .show();
            } else {
                Toast.makeText(LoginActivity.this,
                        "GPS Permission Denied",
                        Toast.LENGTH_SHORT)
                        .show();
            }
        } else if (requestCode == STORAGE_PERMISSION) {

            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(LoginActivity.this,
                        "Storage Permission Granted",
                        Toast.LENGTH_SHORT)
                        .show();
            } else {
                Toast.makeText(LoginActivity.this,
                        "Storage Permission Denied",
                        Toast.LENGTH_SHORT)
                        .show();
            }
        } else if (requestCode == GPS_PERMISSION_COARPSE) {

            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(LoginActivity.this,
                        "Storage Permission Granted",
                        Toast.LENGTH_SHORT)
                        .show();
            } else {
                Toast.makeText(LoginActivity.this,
                        "Storage Permission Denied",
                        Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }


    private boolean isvalidation() {

        if (mobileET.getText().toString().length() == 0) {
            mobileET.setError("Please Enter the email");
            return false;
        } else if (pswET.length() == 0) {
            pswET.setError("Please Enter the Password");
            return false;
        }
        return true;
    }

}
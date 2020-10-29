package com.nextgentele.dmrc.apis;



import com.nextgentele.dmrc.apis.apiModel.LoginModule;
import com.nextgentele.dmrc.apis.apiModel.LoginResponse;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface ApiInterface {

    @POST(EndApi.LOGIN_USER)
    Call<LoginResponse> login(@Body LoginModule loginModule);



}
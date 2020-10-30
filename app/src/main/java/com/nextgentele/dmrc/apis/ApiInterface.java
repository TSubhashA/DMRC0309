package com.nextgentele.dmrc.apis;



import com.nextgentele.dmrc.apis.apiModel.LoginModule;
import com.nextgentele.dmrc.apis.apiModel.LoginResponse;
import com.nextgentele.dmrc.apis.apiModel.MasterRequest;
import com.nextgentele.dmrc.apis.apiModel.MasterResponse;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface ApiInterface {

    @POST(EndApi.LOGIN_USER)
    Call<LoginResponse> login(@Body LoginModule loginModule);

    @POST(EndApi.MASTER_DATA)
    Call<MasterResponse> getMaster(@Body MasterRequest loginModule);



}
package com.example.forexample.API;

import com.example.forexample.DataClasses.DataDoors;
import com.example.forexample.DataClasses.JSONCamerasData;

import retrofit2.Call;
import retrofit2.http.GET;

public interface API {
    @GET("api/rubetek/doors")
    Call<DataDoors> getDoors();

    @GET("api/rubetek/cameras")
    Call<JSONCamerasData> getCameras();
}
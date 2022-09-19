package com.example.forexample.Services.Retrofit;

import com.example.forexample.Services.Retrofit.Response.CamerasResponse;
import com.example.forexample.Services.Retrofit.Response.DoorResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface API {
    @GET("api/rubetek/doors")
    Call<DoorResponse> getDoors();

    @GET("api/rubetek/cameras")
    Call<CamerasResponse> getCameras();
}
package com.example.forexample.Services.Retrofit;

import com.example.forexample.Services.Retrofit.Requests.CamerasRequest;
import com.example.forexample.Services.Retrofit.Requests.DoorRequest;

import retrofit2.Call;
import retrofit2.http.GET;

public interface API {
    @GET("api/rubetek/doors")
    Call<DoorRequest> getDoors();

    @GET("api/rubetek/cameras")
    Call<CamerasRequest> getCameras();
}
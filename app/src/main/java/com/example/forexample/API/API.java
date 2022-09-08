package com.example.forexample.API;

import com.example.forexample.Cameras;
import com.example.forexample.Doors;

import retrofit2.Call;
import retrofit2.http.GET;

public interface API {
    @GET("api/rubetek/doors")
    Call<Doors> getDoors();

    @GET("api/rubetek/cameras")
    Call<Cameras> getCameras();
}

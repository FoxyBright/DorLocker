package com.example.forexample.API;

import com.example.forexample.Cameras;
import com.example.forexample.CamerasData;
import com.example.forexample.DataDoors;
import com.example.forexample.Doors;
import com.example.forexample.JSONCamerasData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface API {
    @GET("api/rubetek/doors")
    Call<DataDoors> getDoors();

    @GET("api/rubetek/cameras")
    Call<JSONCamerasData> getCameras();
}
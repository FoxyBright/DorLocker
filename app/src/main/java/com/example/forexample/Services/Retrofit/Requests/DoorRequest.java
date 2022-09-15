package com.example.forexample.Services.Retrofit.Requests;

import java.util.List;

import com.example.forexample.Models.Door;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DoorRequest implements Request {

    @SerializedName("data")
    @Expose
    private final List<Door> doors = null;

    @Override
    public List<Door> getData() {
        return doors;
    }
}

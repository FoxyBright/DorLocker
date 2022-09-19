package com.example.forexample.Services.Retrofit.Response;

import java.util.List;

import com.example.forexample.Models.Door;
import com.google.gson.annotations.SerializedName;

public class DoorResponse{

    @SerializedName("data")
    private final List<Door> doors = null;

    public List<Door> getDoors() {
        return doors;
    }
}

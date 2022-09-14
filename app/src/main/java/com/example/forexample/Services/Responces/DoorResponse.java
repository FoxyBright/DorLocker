package com.example.forexample.Services.Responces;

import java.util.List;
import com.example.forexample.Classes.Door;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DoorResponse {

    @SerializedName("data")
    @Expose
    private final List<Door> doors = null;

    public List<Door> getDoors() {
        return doors;
    }
}

package com.example.forexample;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RoomsData {

    @SerializedName("room")
    @Expose
    private String room;
    @SerializedName("cameras")
    @Expose
    private List<Cameras> cameras = null;

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public List<Cameras> getCameras() {
        return cameras;
    }

    public void setCameras(List<Cameras> cameras) {
        this.cameras = cameras;
    }
}
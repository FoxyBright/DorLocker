package com.example.forexample;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CamerasData {

    @SerializedName("room")
    @Expose
    private List<String> room = null;
    @SerializedName("cameras")
    @Expose
    private List<Cameras> cameras = null;

    public List<String> getRoom() {
        return room;
    }

    public void setRoom(List<String> room) {
        this.room = room;
    }

    public List<Cameras> getCameras() {
        return cameras;
    }

    public void setCameras(List<Cameras> cameras) {
        this.cameras = cameras;
    }

}
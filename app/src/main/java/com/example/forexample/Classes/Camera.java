package com.example.forexample.Classes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Camera {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("snapshot")
    @Expose
    private String snapshot;
    @SerializedName("room")
    @Expose
    private String room;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("favorites")
    @Expose
    private Boolean favorites;
    @SerializedName("rec")
    @Expose
    private Boolean rec;

    public Camera() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSnapshot() {
        return snapshot;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getFavorites() {
        return favorites;
    }

    public Boolean getRec() {
        return rec;
    }
}
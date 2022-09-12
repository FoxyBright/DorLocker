package com.example.forexample.DataClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Doors {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("room")
    @Expose
    private String room;
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("favorites")
    @Expose
    private boolean favorites;
    @SerializedName("snapshot")
    @Expose
    private String snapshot;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isFavorites() {
        return favorites;
    }

    public void setFavorites(boolean favorites) {
        this.favorites = favorites;
    }

    public String getSnapshot() {
        return snapshot;
    }

    public void setSnapshot(String snapshot) {
        this.snapshot = snapshot;
    }

}
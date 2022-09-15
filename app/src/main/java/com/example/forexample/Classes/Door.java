package com.example.forexample.Classes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class Door{

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("snapshot")
    @Expose
    private String snapshot;
    @SerializedName("favorites")
    @Expose
    private Boolean favorites;

    public void setSnapshot(String snapshot) {
        this.snapshot = snapshot;
    }

    public Boolean getFavorites() {
        return favorites;
    }

    public void setFavorites(Boolean favorites) {
        this.favorites = favorites;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSnapshot() {
        return snapshot;
    }

}
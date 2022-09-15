package com.example.forexample.Models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity
public class Door implements Serializable {

    @SerializedName("name")
    @Expose
    @ColumnInfo(name = "name")
    private String name;

    @SerializedName("id")
    @Expose
    @PrimaryKey()
    private int id;

    @SerializedName("snapshot")
    @Expose
    @ColumnInfo(name = "snapshot")
    private String snapshot;

    @SerializedName("favorites")
    @Expose
    @ColumnInfo(name = "favorites")
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
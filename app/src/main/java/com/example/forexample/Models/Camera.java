package com.example.forexample.Models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity
public class Camera implements Serializable {

    @SerializedName("name")
    @Expose
    @ColumnInfo(name = "name")
    private String name;

    @SerializedName("snapshot")
    @Expose
    @ColumnInfo(name = "snapshot")
    private String snapshot;

    @SerializedName("room")
    @Expose
    @ColumnInfo(name = "room")
    private String room;

    @SerializedName("id")
    @Expose
    @PrimaryKey()
    private Integer id;

    @SerializedName("favorites")
    @Expose
    @ColumnInfo(name = "favorites")
    private Boolean favorites;

    @SerializedName("rec")
    @Expose
    @ColumnInfo(name = "rec")
    private Boolean rec;

    public void setSnapshot(String snapshot) {
        this.snapshot = snapshot;
    }

    public void setFavorites(Boolean favorites) {
        this.favorites = favorites;
    }

    public void setRec(Boolean rec) {
        this.rec = rec;
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
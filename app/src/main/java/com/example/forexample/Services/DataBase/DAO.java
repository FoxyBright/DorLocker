package com.example.forexample.Services.DataBase;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.forexample.Models.Camera;
import com.example.forexample.Models.Door;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;


@Dao
public interface DAO {

    @Insert(onConflict = REPLACE)
    void insertCamera(Camera camera);

    @Query("SELECT * FROM Camera")
    Flowable<List<Camera>> getCameras();

    @Insert(onConflict = REPLACE)
    void insertDoor(Door door);

    @Query("SELECT * FROM Door")
    Flowable<List<Door>> getDoors();
}

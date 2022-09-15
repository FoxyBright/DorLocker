package com.example.forexample.Services.DataBase;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.forexample.Classes.Camera;
import com.example.forexample.Classes.Door;

import java.util.List;

@Dao
public interface DAO {

    @Insert(onConflict = REPLACE)
    void insertCamera(Camera camera);

    @Query("SELECT * FROM Camera")
    List<Camera> getCameras();

    @Insert(onConflict = REPLACE)
    void insertDoor(Door door);

    @Query("SELECT * FROM Door")
    List<Door> getDoors();
}

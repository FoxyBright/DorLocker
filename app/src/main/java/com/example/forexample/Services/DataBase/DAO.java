package com.example.forexample.Services.DataBase;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.forexample.Models.Camera;
import com.example.forexample.Models.Door;

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

    @Query("UPDATE Camera SET favorites = :favorite where id = :id")
    void cameraSetFavorite(int id, Boolean favorite);

    @Query("UPDATE Door SET favorites = :favorite where id = :id")
    void doorSetFavorite(int id, Boolean favorite);
}

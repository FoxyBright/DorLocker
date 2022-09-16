package com.example.forexample.Services.DataBase;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.forexample.Models.Camera;
import com.example.forexample.Models.Door;

@androidx.room.Database(entities = {Camera.class, Door.class}, version = 3, exportSchema = false)
public abstract class Database extends RoomDatabase {

    private static Database database;

    public static Database getInstance(Context context) {

        if (database == null) {
            database = Room.databaseBuilder(context.getApplicationContext(),
                            Database.class, "database")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return database;
    }

    public abstract DAO DAO();
}

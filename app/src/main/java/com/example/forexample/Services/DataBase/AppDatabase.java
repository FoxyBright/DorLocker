package com.example.forexample.Services.DataBase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.forexample.Classes.Camera;
import com.example.forexample.Classes.Door;

@Database(entities = {Camera.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase database;

    public static AppDatabase getInstance(Context context, String DATABASE_NAME){

        if (database == null){
            database = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return database;
    }

    public abstract DAO DAO();
}

package com.example.forexample.Models;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.forexample.Services.Retrofit.Response.DoorResponse;
import com.example.forexample.Services.Retrofit.RetrofitAPI;

import io.realm.Realm;
import io.realm.RealmObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Door extends RealmObject {
    private String name;
    private int id;
    private String snapshot;
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

    public void getDoors() {

        Call<DoorResponse> getDoorsCall = RetrofitAPI.getInstance().getAPI().getDoors();
        getDoorsCall.enqueue(new Callback<DoorResponse>() {
            @Override
            public void onResponse(@NonNull Call<DoorResponse> call, @NonNull Response<DoorResponse> response) {
                Realm realm = Realm.getDefaultInstance();
                realm.executeTransactionAsync(realm1 -> {
                    realm1.where(Door.class).findAll().deleteAllFromRealm();
                    Door d;
                    assert response.body() != null;
                    for (Door door : response.body().getDoors()) {
                        d = realm1.createObject(Door.class);
                        d.setId(door.getId());
                        d.setName(door.getName());
                        d.setFavorites(door.getFavorites());
                        d.setSnapshot(door.getSnapshot());
                    }
                });
                realm.close();
            }

            @Override
            public void onFailure(@NonNull Call<DoorResponse> call, @NonNull Throwable t) {
                Log.d("Error", "Doors get data failed");
            }
        });
    }

    public void renamedDoor(int id, String name){
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.where(Door.class).equalTo("id", id).findFirst().setName(name);
            }
        });
        realm.close();
    }
}
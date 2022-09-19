package com.example.forexample.Models;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.forexample.Services.Retrofit.Response.CamerasResponse;
import com.example.forexample.Services.Retrofit.RetrofitAPI;

import java.util.Objects;

import io.realm.Realm;
import io.realm.RealmObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Camera extends RealmObject {

    private String name;
    private String snapshot;
    private String room;
    private Integer id;
    private Boolean favorites;
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

    public void getCameras() {

        Call<CamerasResponse> getCamerasCall = RetrofitAPI.getInstance().getAPI().getCameras();
        getCamerasCall.enqueue(new Callback<CamerasResponse>() {
            @Override
            public void onResponse(@NonNull Call<CamerasResponse> call, @NonNull Response<CamerasResponse> response) {
                Realm realm = Realm.getDefaultInstance();
                realm.executeTransactionAsync(realm1 -> {
                    realm1.where(Camera.class).findAll().deleteAllFromRealm();
                    Camera c;
                    assert response.body() != null;
                    for (Camera camera : response.body().getCameras()) {
                        c = realm1.createObject(Camera.class);
                        c.setId(camera.getId());
                        c.setName(camera.getName());
                        c.setFavorites(camera.getFavorites());
                        c.setRec(camera.getRec());
                        c.setRoom(camera.getRoom());
                        c.setSnapshot(camera.getSnapshot());
                    }
                });
                realm.close();
            }

            @Override
            public void onFailure(@NonNull Call<CamerasResponse> call, @NonNull Throwable t) {
                Log.d("Error", "Camera get data failed");
            }
        });
    }

    public void cameraFavoriteSet(int id, Boolean favorites){
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransactionAsync(realm1 -> Objects.requireNonNull(realm1.where(Camera.class).equalTo("id", id).findFirst()).setFavorites(favorites));
        realm.close();
    }
}
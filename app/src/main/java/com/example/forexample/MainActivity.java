package com.example.forexample;

import android.os.Bundle;

import com.example.forexample.Classes.Camera;
import com.example.forexample.Services.Realm.Migration;
import com.example.forexample.Services.Requests.CamerasRequest;
import com.example.forexample.Services.Retrofit.mRetrofit;
import com.example.forexample.databinding.ActivityMainBinding;
import com.example.forexample.UI.TabLayout.CustomViewPager;
import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.forexample.UI.TabLayout.SectionsPagerAdapter;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        CustomViewPager viewPager = binding.viewPager;
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = binding.tabs;
        viewPager.setEnableSwipe(false);
        viewPager.beginFakeDrag();
        tabs.setupWithViewPager(viewPager);
        Realm.init(getApplicationContext());
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("realmDB")
                .allowQueriesOnUiThread(true)
                .allowWritesOnUiThread(true)
                .migration(new Migration())
                .build();
        Call<CamerasRequest> getCamerasCall = mRetrofit.getInstance().getAPI().getCameras();
        getCamerasCall.enqueue(new Callback<CamerasRequest>() {
            @Override
            public void onResponse(Call<CamerasRequest> call, Response<CamerasRequest> response) {
                Realm realm = Realm.getInstance(config);
                realm.beginTransaction();
                realm.where(Camera.class).findAll().deleteAllFromRealm();
                realm.commitTransaction();
                for (Camera c : response.body().getData()) {
                    realm.beginTransaction();
                    Camera camera = realm.createObject(Camera.class);
                    camera.setId(c.getId());
                    camera.setName(c.getName());
                    camera.setRoom(c.getRoom());
                    camera.setFavorites(c.getFavorites());
                    camera.setSnapshot(c.getSnapshot());
                    camera.setRec(c.getRec());
                    realm.commitTransaction();
                }
                realm.close();
            }
            @Override
            public void onFailure(Call<CamerasRequest> call, Throwable t) {
            }
        });
    }
}
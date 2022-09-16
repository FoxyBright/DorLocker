package com.example.forexample;

import android.os.Bundle;
import android.widget.Toast;

import com.example.forexample.Models.Camera;
import com.example.forexample.Models.Door;
import com.example.forexample.Services.DataBase.Database;
import com.example.forexample.Services.Retrofit.Requests.CamerasRequest;
import com.example.forexample.Services.Retrofit.Requests.DoorRequest;
import com.example.forexample.Services.Retrofit.RetrofitAPI;
import com.example.forexample.databinding.ActivityMainBinding;
import com.example.forexample.UI.CustomViewPager;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.forexample.UI.SectionsPagerAdapter;

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
        viewPager.setEnableSwipe(false);
        viewPager.beginFakeDrag();
        binding.tabs.setupWithViewPager(viewPager);

        Database database = Database.getInstance(this);

        Call<CamerasRequest> getCamerasCall = RetrofitAPI.getInstance().getAPI().getCameras();
        getCamerasCall.enqueue(new Callback<CamerasRequest>() {
            @Override
            public void onResponse(@NonNull Call<CamerasRequest> call, @NonNull Response<CamerasRequest> response) {
                assert response.body() != null;
                for (Camera camera : response.body().getData())
                    database.DAO().insertCamera(camera);
            }

            @Override
            public void onFailure(@NonNull Call<CamerasRequest> call, @NonNull Throwable t) {
                Toast.makeText(MainActivity.this, R.string.not_cameras, Toast.LENGTH_SHORT).show();
            }
        });

        Call<DoorRequest> getDoorCall = RetrofitAPI.getInstance().getAPI().getDoors();
        getDoorCall.enqueue(new Callback<DoorRequest>() {
            @Override
            public void onResponse(@NonNull Call<DoorRequest> call, @NonNull Response<DoorRequest> response) {
                assert response.body() != null;
                for (Door door : response.body().getData())
                    database.DAO().insertDoor(door);
            }

            @Override
            public void onFailure(@NonNull Call<DoorRequest> call, @NonNull Throwable t) {
                Toast.makeText(MainActivity.this, R.string.not_doors, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
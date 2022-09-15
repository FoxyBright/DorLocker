package com.example.forexample;

import android.os.Bundle;
import com.example.forexample.Classes.Camera;
import com.example.forexample.Services.DataBase.AppDatabase;
import com.example.forexample.Services.Requests.CamerasRequest;
import com.example.forexample.Services.Retrofit.mRetrofit;
import com.example.forexample.databinding.ActivityMainBinding;
import com.example.forexample.UI.TabLayout.CustomViewPager;
import com.google.android.material.tabs.TabLayout;
import androidx.appcompat.app.AppCompatActivity;
import com.example.forexample.UI.TabLayout.SectionsPagerAdapter;

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
        AppDatabase database = AppDatabase.getInstance(this, "database");
        Call<CamerasRequest> getCamerasCall = mRetrofit.getInstance().getAPI().getCameras();
        getCamerasCall.enqueue(new Callback<CamerasRequest>() {
            @Override
            public void onResponse(Call<CamerasRequest> call, Response<CamerasRequest> response) {
                for (Camera camera: response.body().getData())
                    database.DAO().insertCamera(camera);
            }
            @Override
            public void onFailure(Call<CamerasRequest> call, Throwable t) {
            }
        });
    }
}
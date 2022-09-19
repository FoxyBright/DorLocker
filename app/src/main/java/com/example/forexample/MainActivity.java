package com.example.forexample;

import android.os.Bundle;

import com.example.forexample.Models.Camera;
import com.example.forexample.Models.Door;

import androidx.appcompat.app.AppCompatActivity;

import com.example.forexample.UI.SectionsPagerAdapter;
import com.example.forexample.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.tabs.setupWithViewPager(new SectionsPagerAdapter(
                this, getSupportFragmentManager()).getViewPager(binding.viewPager));

        Camera camera = new Camera();
        camera.getCameras();
        Door door = new Door();
        door.getDoors();
    }
}
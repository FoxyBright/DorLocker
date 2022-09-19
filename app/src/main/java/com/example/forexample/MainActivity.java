package com.example.forexample;

import android.os.Bundle;

import com.example.forexample.Models.Camera;
import com.example.forexample.Models.Door;
import com.example.forexample.databinding.ActivityMainBinding;
import com.example.forexample.UI.CustomViewPager;

import androidx.appcompat.app.AppCompatActivity;

import com.example.forexample.UI.SectionsPagerAdapter;

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

        Camera camera = new Camera();
        camera.getCameras();
        Door door = new Door();
        door.getDoors();
    }
}
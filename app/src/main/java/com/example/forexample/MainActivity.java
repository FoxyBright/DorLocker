package com.example.forexample;

import android.os.Bundle;

import com.example.forexample.databinding.ActivityMainBinding;
import com.example.forexample.ui.main.CustomViewPager;
import com.google.android.material.tabs.TabLayout;
import androidx.appcompat.app.AppCompatActivity;
import com.example.forexample.ui.main.SectionsPagerAdapter;


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
    }
}
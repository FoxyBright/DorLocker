package com.example.forexample;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.forexample.databinding.FragmentDoorBinding;

public class DoorsFragment extends Fragment {

    private FragmentDoorBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentDoorBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        final ImageView roundedImageView = binding.roundedImageView;
        roundedImageView.setClipToOutline(true);

        return view;
    }
}
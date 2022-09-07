package com.example.forexample;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.forexample.databinding.FragmentDoorsBinding;

public class DoorsFragment extends Fragment {

    private FragmentDoorsBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentDoorsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final ImageView roundedImageView = binding.roundedImageView;
        roundedImageView.setClipToOutline(true);

        return root;
    }
}
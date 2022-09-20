package com.example.forexample.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.forexample.R;
import com.example.forexample.databinding.FragmentIntercomBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.Objects;

public class IntercomFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentIntercomBinding binding = FragmentIntercomBinding
                .inflate(getLayoutInflater(), container, false);

        binding.openDoorButton.setOnClickListener(v ->
                Toast.makeText(getActivity(), "Дверь открыта", Toast.LENGTH_SHORT).show());

        binding.backButton.setOnClickListener(v -> {
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.remove(Objects.requireNonNull(getParentFragmentManager()
                    .findFragmentByTag("FragmentTransaction")));
            transaction.commit();
        });

        binding.settings.setOnClickListener(v -> {
            @SuppressLint("PrivateResource")
            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(
                    requireActivity(),
                    com.google.android.material.R.style.Base_Theme_Material3_Light_BottomSheetDialog);
            View bottomSheetView = LayoutInflater.from(getActivity()).inflate(
                    R.layout.settings, inflater.inflate(R.layout.fragment_intercom,
                            container, false).findViewById(R.id.bottomSheetContainer));
            bottomSheetDialog.setContentView(bottomSheetView);
            bottomSheetDialog.show();
        });

        binding.crossedOutEyeButton.setOnClickListener(view1 ->
                Toast.makeText(getActivity(), "Функция в доработке", Toast.LENGTH_SHORT).show());

        return binding.getRoot();
    }
}
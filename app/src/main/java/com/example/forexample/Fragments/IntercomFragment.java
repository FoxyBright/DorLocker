package com.example.forexample.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.forexample.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.Objects;

public class IntercomFragment extends Fragment {

    Button openDoor;
    LinearLayout settings;
    ImageView back_button;
    ImageView crossed_out_eye_button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_intercom, container, false);
        openDoor = view.findViewById(R.id.openDoorButton);
        back_button = view.findViewById(R.id.backButton);
        settings = view.findViewById(R.id.settings);
        crossed_out_eye_button = view.findViewById(R.id.crossedOutEyeButton);
        openDoor.setOnClickListener(v -> Toast.makeText(getActivity(), "Дверь открыта", Toast.LENGTH_SHORT).show());
        back_button.setOnClickListener(v -> {
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.remove(Objects.requireNonNull(getParentFragmentManager().findFragmentByTag("FragmentTransaction")));
            transaction.commit();
        });
        settings.setOnClickListener(v -> {
            @SuppressLint("PrivateResource") final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(
                    requireActivity(),
                    com.google.android.material.R.style.Base_Theme_Material3_Light_BottomSheetDialog);
            View bottomSheetView = LayoutInflater.from(getActivity()).inflate(
                    R.layout.settings, view.findViewById(R.id.bottomSheetContainer));
            bottomSheetDialog.setContentView(bottomSheetView);
            bottomSheetDialog.show();
        });

        crossed_out_eye_button.setOnClickListener(view1 ->
                Toast.makeText(getActivity(), "Функция в доработке", Toast.LENGTH_SHORT).show());

        return view;
    }
}
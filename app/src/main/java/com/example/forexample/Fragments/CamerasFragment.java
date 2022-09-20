package com.example.forexample.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daimajia.swipe.util.Attributes;
import com.example.forexample.Adapters.CamerasRecyclerAdapter;
import com.example.forexample.Models.Camera;
import com.example.forexample.databinding.FragmentCamerasBinding;

import io.realm.Realm;

public class CamerasFragment extends Fragment {

    Realm realm;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentCamerasBinding binding = FragmentCamerasBinding
                .inflate(getLayoutInflater(), container, false);

        binding.recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        realm = Realm.getDefaultInstance();
        realm.addChangeListener(realm -> {
            CamerasRecyclerAdapter adapter = new CamerasRecyclerAdapter(getActivity(),
                    realm.where(Camera.class).findAll());
            ((CamerasRecyclerAdapter) adapter).setMode(Attributes.Mode.Single);
            binding.recycler.setAdapter(adapter);
        });

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        realm.close();
    }
}
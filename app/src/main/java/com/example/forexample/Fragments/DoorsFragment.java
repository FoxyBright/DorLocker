package com.example.forexample.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daimajia.swipe.util.Attributes;
import com.example.forexample.Adapters.DoorsRecyclerAdapter;
import com.example.forexample.Models.Door;
import com.example.forexample.databinding.FragmentDoorsBinding;

import io.realm.Realm;

public class DoorsFragment extends Fragment {

    Realm realm;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentDoorsBinding binding = FragmentDoorsBinding
                .inflate(getLayoutInflater(), container, false);

        binding.recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        realm = Realm.getDefaultInstance();
        realm.addChangeListener(realm -> {
            DoorsRecyclerAdapter adapter = new DoorsRecyclerAdapter(getActivity(),
                    realm.where(Door.class).findAll());
            ((DoorsRecyclerAdapter) adapter).setMode(Attributes.Mode.Single);
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
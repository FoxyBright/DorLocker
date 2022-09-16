package com.example.forexample.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daimajia.swipe.util.Attributes;
import com.example.forexample.Adapters.CamerasRecyclerAdapter;
import com.example.forexample.R;
import com.example.forexample.Services.DataBase.Database;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;

public class CamerasFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cameras, container, false);

        RecyclerView recycler = view.findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));

        Database.getInstance(getActivity()).DAO().getCameras().observeOn(AndroidSchedulers.mainThread()).subscribe(cameras -> {
            CamerasRecyclerAdapter adapter = new CamerasRecyclerAdapter(getActivity(), cameras);
            ((CamerasRecyclerAdapter) adapter).setMode(Attributes.Mode.Single);
            recycler.setAdapter(adapter);
        });

        return view;
    }
}
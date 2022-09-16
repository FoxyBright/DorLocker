package com.example.forexample.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.daimajia.swipe.util.Attributes;
import com.example.forexample.Adapters.CamerasRecyclerAdapter;
import com.example.forexample.Adapters.DoorsRecyclerAdapter;
import com.example.forexample.Models.Camera;
import com.example.forexample.R;
import com.example.forexample.Services.DataBase.Database;

import java.util.List;

public class CamerasFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cameras, container, false);

        RecyclerView recycler = view.findViewById(R.id.recycler);
        TextView empty_view = view.findViewById(R.id.empty_view);
        List<Camera> cameras = Database.getInstance(getActivity()).DAO().getCameras();
        if (cameras.isEmpty()) {
            recycler.setVisibility(View.GONE);
            empty_view.setVisibility(View.VISIBLE);
        } else {
            recycler.setVisibility(View.VISIBLE);
            empty_view.setVisibility(View.GONE);
        }

        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        CamerasRecyclerAdapter adapter = new CamerasRecyclerAdapter(getActivity(), cameras);
        ((CamerasRecyclerAdapter) adapter).setMode(Attributes.Mode.Single);
        recycler.setAdapter(adapter);

        return view;
    }
}
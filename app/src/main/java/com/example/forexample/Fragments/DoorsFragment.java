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
import com.example.forexample.Adapters.DoorsRecyclerAdapter;
import com.example.forexample.Models.Door;
import com.example.forexample.R;
import com.example.forexample.Services.DataBase.Database;

import java.util.List;

public class DoorsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doors, container, false);

        RecyclerView recycler = view.findViewById(R.id.recycler);
        TextView empty_view = view.findViewById(R.id.empty_view);
        List<Door> doors = Database.getInstance(getActivity()).DAO().getDoors();
        if (doors.isEmpty()) {
            recycler.setVisibility(View.GONE);
            empty_view.setVisibility(View.VISIBLE);
        } else {
            recycler.setVisibility(View.VISIBLE);
            empty_view.setVisibility(View.GONE);
        }

        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        DoorsRecyclerAdapter adapter = new DoorsRecyclerAdapter(getActivity(), doors);
        ((DoorsRecyclerAdapter) adapter).setMode(Attributes.Mode.Single);
        recycler.setAdapter(adapter);

        return view;
    }
}
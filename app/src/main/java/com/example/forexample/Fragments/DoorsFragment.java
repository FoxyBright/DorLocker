package com.example.forexample.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daimajia.swipe.util.Attributes;
import com.example.forexample.Adapters.DoorsRecyclerAdapter;
import com.example.forexample.R;
import com.example.forexample.Services.DataBase.Database;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;

public class DoorsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doors, container, false);

        RecyclerView recycler = view.findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));

        Database.getInstance(getActivity()).DAO().getDoors().observeOn(AndroidSchedulers.mainThread()).subscribe(doors -> {
            DoorsRecyclerAdapter adapter = new DoorsRecyclerAdapter(getActivity(), doors);
            ((DoorsRecyclerAdapter) adapter).setMode(Attributes.Mode.Single);
            recycler.setAdapter(adapter);
        });

        return view;
    }
}
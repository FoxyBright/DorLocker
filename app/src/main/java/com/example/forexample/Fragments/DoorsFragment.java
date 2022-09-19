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
import com.example.forexample.Models.Door;
import com.example.forexample.R;

import io.realm.Realm;

public class DoorsFragment extends Fragment {

    Realm realm;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doors, container, false);

        RecyclerView recycler = view.findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        realm = Realm.getDefaultInstance();
        realm.addChangeListener(realm -> {
            DoorsRecyclerAdapter adapter = new DoorsRecyclerAdapter(getActivity(),
                    realm.where(Door.class).findAll());
            ((DoorsRecyclerAdapter) adapter).setMode(Attributes.Mode.Single);
            recycler.setAdapter(adapter);
        });
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        realm.close();
    }
}
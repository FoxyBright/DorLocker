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
import com.example.forexample.Models.Camera;
import com.example.forexample.R;

import io.realm.Realm;

public class CamerasFragment extends Fragment {

    Realm realm;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cameras, container, false);

        RecyclerView recycler = view.findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        realm = Realm.getDefaultInstance();
        realm.addChangeListener(realm -> {
            CamerasRecyclerAdapter adapter = new CamerasRecyclerAdapter(getActivity(),
                    realm.where(Camera.class).findAll());
            ((CamerasRecyclerAdapter) adapter).setMode(Attributes.Mode.Single);
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
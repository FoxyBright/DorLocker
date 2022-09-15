package com.example.forexample.Fragments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.forexample.Adapters.CamerasRecyclerAdapter;
import com.example.forexample.Models.Camera;
import com.example.forexample.R;
import com.example.forexample.Services.DataBase.Database;
import com.example.forexample.UI.RecyclerSwiper;

import java.util.List;

public class CamerasFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cameras, container, false);

        RecyclerView recycler = view.findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        List<Camera> cameras = Database.getInstance(getActivity()).DAO().getCameras();
        recycler.setAdapter(new CamerasRecyclerAdapter(getActivity(), cameras));

        try {
            RecyclerSwiper mySwipeHelper = new RecyclerSwiper(getActivity(), recycler, 150) {
                @Override
                protected void instantiateMyButton(RecyclerView.ViewHolder viewHolder, List<MyButton> buffer) {
                    buffer.add(new MyButton(requireActivity(),
                            "",
                            45,
                            R.drawable.favorite_button_disactivate,
                            Color.parseColor("#F6F6F6"),
                            pos -> Toast.makeText(getActivity(), "Favorite " + cameras.get(pos).getName(), Toast.LENGTH_SHORT).show()));
                }
            };
        } catch (Exception ignored) {
        }

        return view;
    }
}
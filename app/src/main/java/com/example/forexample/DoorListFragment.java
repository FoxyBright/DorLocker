package com.example.forexample;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.forexample.API.SingletonRetrofitObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DoorListFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<Doors> DoorsArray;
    public DoorsRecyclerAdapter doorsRecyclerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_door_list, container, false);
        recyclerView = view.findViewById(R.id.recicler);
        DoorsArray = new DataDoors().getDataDoors();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        SingletonRetrofitObject api = SingletonRetrofitObject.getInstance();
        Call<DataDoors> getDoorsCall = api.getJSONApi().getDoors();
        getDoorsCall.enqueue(new Callback<DataDoors>() {
            @Override
            public void onResponse(Call<DataDoors> call, Response<DataDoors> response) {
                DoorsArray = response.body().getDataDoors();
                doorsRecyclerAdapter = new DoorsRecyclerAdapter(getActivity(), DoorsArray);
                recyclerView.setAdapter(doorsRecyclerAdapter);
            }

            @Override
            public void onFailure(Call<DataDoors> call, Throwable t) {
                Toast.makeText(getActivity(), "Ошибка получения данных", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
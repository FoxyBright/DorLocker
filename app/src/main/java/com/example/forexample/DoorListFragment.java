package com.example.forexample;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.forexample.Services.Responces.DoorResponse;
import com.example.forexample.Classes.Door;
import com.example.forexample.Services.Retrofit.mRetrofit;
import com.example.forexample.ui.main.RecyclerSwiper;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DoorListFragment extends Fragment {

    private RecyclerView recycler;
    private List<Door> doorArray;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_door_list, container, false);
        recycler = view.findViewById(R.id.recycler);
        doorArray = new DoorResponse().getDoors();
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRetrofit api = mRetrofit.getInstance();
        Call<DoorResponse> getDoorsCall = api.getAPI().getDoors();
        getDoorsCall.enqueue(new Callback<DoorResponse>() {
            @Override
            public void onResponse(@NonNull Call<DoorResponse> call, @NonNull Response<DoorResponse> response) {
                assert response.body() != null;
                doorArray = response.body().getDoors();
                try {
                    RecyclerSwiper mySwipeHelper = new RecyclerSwiper(getActivity(), recycler, 200) {
                        @Override
                        protected void instantiateMyButton(RecyclerView.ViewHolder viewHolder, List<MyButton> buffer) {

                            buffer.add(new MyButton(getActivity(),
                                    "",
                                    45,
                                    R.drawable.favorite_disactivate,
                                    Color.parseColor("#F6F6F6"),
                                    pos -> Toast.makeText(getActivity(), "Favorite " + doorArray.get(pos).getName(), Toast.LENGTH_SHORT).show()));
                            buffer.add(new MyButton(getActivity(),
                                    "",
                                    45,
                                    R.drawable.edit,
                                    Color.parseColor("#F6F6F6"),
                                    pos -> Toast.makeText(getActivity(), "Edit " + doorArray.get(pos).getName(), Toast.LENGTH_SHORT).show()));
                        }
                    };
                } catch (Exception ignored) {
                }
                DoorsRecyclerAdapter doorsRecyclerAdapter = new DoorsRecyclerAdapter(getActivity(), doorArray);
                recycler.setAdapter(doorsRecyclerAdapter);
            }

            @Override
            public void onFailure(@NonNull Call<DoorResponse> call, @NonNull Throwable t) {
                Toast.makeText(getActivity(), "Ошибка получения данных от дверей", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
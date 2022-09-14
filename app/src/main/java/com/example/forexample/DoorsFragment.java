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

import com.example.forexample.Services.Requests.DoorRequest;
import com.example.forexample.Classes.Door;
import com.example.forexample.Services.Retrofit.mRetrofit;
import com.example.forexample.UI.RecyclerSwiper;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DoorsFragment extends Fragment {

    private RecyclerView recycler;
    private List<Door> doorArray;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_doors, container, false);
        recycler = view.findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        Call<DoorRequest> getDoorsCall = mRetrofit.getInstance().getAPI().getDoors();
        getDoorsCall.enqueue(new Callback<DoorRequest>() {
            @Override
            public void onResponse(@NonNull Call<DoorRequest> call, @NonNull Response<DoorRequest> response) {
                assert response.body() != null;
                doorArray = response.body().getData();
                try {
                    RecyclerSwiper mySwipeHelper = new RecyclerSwiper(getActivity(), recycler, 200) {
                        @Override
                        protected void instantiateMyButton(RecyclerView.ViewHolder viewHolder, List<MyButton> buffer) {

                            buffer.add(new MyButton(getActivity(),
                                    "",
                                    45,
                                    R.drawable.favorite_button_disactivate,
                                    Color.parseColor("#F6F6F6"),
                                    pos -> Toast.makeText(getActivity(), "Favorite " + doorArray.get(pos).getName(), Toast.LENGTH_SHORT).show()));
                            buffer.add(new MyButton(getActivity(),
                                    "",
                                    45,
                                    R.drawable.edit_pencil,
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
            public void onFailure(@NonNull Call<DoorRequest> call, @NonNull Throwable t) {
                Toast.makeText(getActivity(), "Ошибка получения данных от дверей", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
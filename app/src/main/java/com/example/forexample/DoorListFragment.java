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

import com.example.forexample.DataClasses.DataDoors;
import com.example.forexample.DataClasses.Doors;
import com.example.forexample.API.SingletonRetrofitObject;
import com.example.forexample.helper.MySwipeHelper;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DoorListFragment extends Fragment {

    private RecyclerView recycler;
    private List<Doors> DoorsArray;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_door_list, container, false);
        recycler = view.findViewById(R.id.recycler);
        DoorsArray = new DataDoors().getDataDoors();
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        SingletonRetrofitObject api = SingletonRetrofitObject.getInstance();
        Call<DataDoors> getDoorsCall = api.getJSONApi().getDoors();
        getDoorsCall.enqueue(new Callback<DataDoors>() {
            @Override
            public void onResponse(@NonNull Call<DataDoors> call, @NonNull Response<DataDoors> response) {
                assert response.body() != null;
                DoorsArray = response.body().getDataDoors();
                try {
                    MySwipeHelper mySwipeHelper = new MySwipeHelper(getActivity(), recycler, 200) {
                        @Override
                        protected void instantiateMyButton(RecyclerView.ViewHolder viewHolder, List<MyButton> buffer) {

                            buffer.add(new MyButton(getActivity(),
                                    "",
                                    45,
                                    R.drawable.favorite_disactivate,
                                    Color.parseColor("#F6F6F6"),
                                    pos -> Toast.makeText(getActivity(), "Favorite " + DoorsArray.get(pos).getName(), Toast.LENGTH_SHORT).show()));
                            buffer.add(new MyButton(getActivity(),
                                    "",
                                    45,
                                    R.drawable.edit,
                                    Color.parseColor("#F6F6F6"),
                                    pos -> Toast.makeText(getActivity(), "Edit " + DoorsArray.get(pos).getName(), Toast.LENGTH_SHORT).show()));
                        }
                    };
                } catch (Exception ignored) {
                }
                DoorsRecyclerAdapter doorsRecyclerAdapter = new DoorsRecyclerAdapter(getActivity(), DoorsArray);
                recycler.setAdapter(doorsRecyclerAdapter);
            }

            @Override
            public void onFailure(@NonNull Call<DataDoors> call, @NonNull Throwable t) {
                Toast.makeText(getActivity(), "Ошибка получения данных от дверей", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
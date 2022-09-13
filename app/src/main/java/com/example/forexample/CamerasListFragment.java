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

import com.example.forexample.DataClasses.Cameras;
import com.example.forexample.DataClasses.CamerasData;
import com.example.forexample.DataClasses.JSONCamerasData;
import com.example.forexample.API.SingletonRetrofitObject;
import com.example.forexample.helper.MySwipeHelper;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CamerasListFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<Cameras> CamerasData;
    private List<String> RoomsList;
    public CamerasRecyclerAdapter CamerasRecyclerAdapter;

    private List<Cameras> sort(List<Cameras> cam) {
        cam.removeIf(cameras -> !cameras.getRoom().equals("Вне комнат"));
        return cam;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_cameras_list, container, false);
        recyclerView = view.findViewById(R.id.recycler);
        CamerasData = new CamerasData().getCameras();
        RoomsList = new CamerasData().getRoom();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        SingletonRetrofitObject api = SingletonRetrofitObject.getInstance();
        Call<JSONCamerasData> getCamerasCall = api.getJSONApi().getCameras();
        getCamerasCall.enqueue(new Callback<JSONCamerasData>() {
            @Override
            public void onResponse(@NonNull Call<JSONCamerasData> call, @NonNull Response<JSONCamerasData> response) {
                assert response.body() != null;
                CamerasData = response.body().getCamerasData().getCameras();
                for (Cameras cameras : CamerasData)
                    if (cameras.getRoom() == null)
                        cameras.setRoom("Вне комнат");
                RoomsList = response.body().getCamerasData().getRoom();
                RoomsList.add("Вне комнат");

                MySwipeHelper mySwipeHelper = new MySwipeHelper(getActivity(), recyclerView, 150) {
                    @Override
                    protected void instantiateMyButton(RecyclerView.ViewHolder viewHolder, List<MyButton> buffer) {

                        buffer.add(new MyButton(getActivity(),
                                "",
                                45,
                                R.drawable.favorite_disactivate,
                                Color.parseColor("#F6F6F6"),
                                pos -> Toast.makeText(getActivity(), "Favorite " + CamerasData.get(pos).getName(), Toast.LENGTH_SHORT).show()));
                    }
                };
                CamerasRecyclerAdapter = new CamerasRecyclerAdapter(getActivity(), CamerasData, RoomsList);
                recyclerView.setAdapter(CamerasRecyclerAdapter);
            }

            @Override
            public void onFailure(@NonNull Call<JSONCamerasData> call, @NonNull Throwable t) {
                Toast.makeText(getActivity(), "Ошибка получения данных c камер", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
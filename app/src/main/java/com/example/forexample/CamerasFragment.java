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

import com.example.forexample.Classes.Camera;
import com.example.forexample.Services.Responces.CamerasResponse;
import com.example.forexample.Services.Retrofit.mRetrofit;
import com.example.forexample.UI.RecyclerSwiper;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CamerasFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<Camera> cameraData;
    public CamerasRecyclerAdapter CamerasRecyclerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_cameras, container, false);
        recyclerView = view.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        Call<CamerasResponse> getCamerasCall = mRetrofit.getInstance().getAPI().getCameras();
        getCamerasCall.enqueue(new Callback<CamerasResponse>() {
            @Override
            public void onResponse(@NonNull Call<CamerasResponse> call, @NonNull Response<CamerasResponse> response) {
                assert response.body() != null;
                cameraData = response.body().getData();
                try {
                    RecyclerSwiper mySwipeHelper = new RecyclerSwiper(getActivity(), recyclerView, 150) {
                        @Override
                        protected void instantiateMyButton(RecyclerView.ViewHolder viewHolder, List<MyButton> buffer) {

                            buffer.add(new MyButton(getActivity(),
                                    "",
                                    45,
                                    R.drawable.favorite_button_disactivate,
                                    Color.parseColor("#F6F6F6"),
                                    pos -> Toast.makeText(getActivity(), "Favorite " + cameraData.get(pos).getName(), Toast.LENGTH_SHORT).show()));
                        }
                    };
                } catch (Exception ignored) {
                }
                CamerasRecyclerAdapter = new CamerasRecyclerAdapter(getActivity(), cameraData);
                recyclerView.setAdapter(CamerasRecyclerAdapter);
            }

            @Override
            public void onFailure(@NonNull Call<CamerasResponse> call, @NonNull Throwable t) {
                Toast.makeText(getActivity(), "Ошибка получения данных c камер", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
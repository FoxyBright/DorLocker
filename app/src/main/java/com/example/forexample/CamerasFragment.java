package com.example.forexample;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.forexample.Classes.Camera;
import com.example.forexample.Services.Requests.CamerasRequest;
import com.example.forexample.Services.Retrofit.mRetrofit;
import com.example.forexample.UI.RecyclerSwiper;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CamerasFragment extends Fragment {

    private RecyclerView recycler;
    private List<Camera> cameras;
    public CamerasRecyclerAdapter CamerasRecyclerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cameras, container, false);
        Context context = getActivity();
        recycler = view.findViewById(R.id.recycler);
        TextView error = view.findViewById(R.id.error);
        recycler.setLayoutManager(new LinearLayoutManager(context));
        try {
            RecyclerSwiper mySwipeHelper = new RecyclerSwiper(context, recycler, 150) {
                @Override
                protected void instantiateMyButton(RecyclerView.ViewHolder viewHolder, List<MyButton> buffer) {

                    buffer.add(new MyButton(context,
                            "",
                            45,
                            R.drawable.favorite_button_disactivate,
                            Color.parseColor("#F6F6F6"),
                            pos -> Toast.makeText(context, "Favorite " + cameras.get(pos).getName(), Toast.LENGTH_SHORT).show()));
                }
            };
        } catch (Exception ignored) {
        }

        Call<CamerasRequest> getCamerasCall = mRetrofit.getInstance().getAPI().getCameras();
        getCamerasCall.enqueue(new Callback<CamerasRequest>() {
            @Override
            public void onResponse(Call<CamerasRequest> call, Response<CamerasRequest> response) {
                cameras = response.body().getData();
                CamerasRecyclerAdapter = new CamerasRecyclerAdapter(context, cameras);
                recycler.setAdapter(CamerasRecyclerAdapter);
            }

            @Override
            public void onFailure(Call<CamerasRequest> call, Throwable t) {
                error.setVisibility(View.VISIBLE);
                recycler.setVisibility(View.GONE);
            }
        });

        return view;
    }
}
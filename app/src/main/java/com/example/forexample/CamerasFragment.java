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
import java.util.Objects;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CamerasFragment extends Fragment{

    private RecyclerView recycler;
    public CamerasRecyclerAdapter CamerasRecyclerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cameras, container, false);
        recycler = view.findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        Realm.init(requireActivity());
        RealmConfiguration config = new RealmConfiguration.Builder().allowQueriesOnUiThread(true).allowWritesOnUiThread(true).build();
        List<Camera> cameras = Realm.getInstance(config).where(Camera.class).findAll();
        CamerasRecyclerAdapter = new CamerasRecyclerAdapter(getActivity(), cameras);
        recycler.setAdapter(CamerasRecyclerAdapter);
        try {
            RecyclerSwiper mySwipeHelper = new RecyclerSwiper(getActivity(), recycler, 150) {
                @Override
                protected void instantiateMyButton(RecyclerView.ViewHolder viewHolder, List<MyButton> buffer) {

                    buffer.add(new MyButton(getActivity(),
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
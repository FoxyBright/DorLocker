package com.example.forexample;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.forexample.API.SingletonRetrofitObject;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DoorListFragment extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<Doors> arrayList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void setRecyclerView(Class<? extends Doors> aClass) {
        DoorsRecyclerAdapter recyclerAdapter = new DoorsRecyclerAdapter(getContext(), arrayList);
        recyclerView.setAdapter(recyclerAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_door_list, container, false);

        recyclerView = view.findViewById(R.id.recicler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        Call<Doors> getDoorsCall = SingletonRetrofitObject.getInstance().getJSONApi().getDoors();
        getDoorsCall.enqueue(new Callback<Doors>() {
            @Override
            public void onResponse(Call<Doors> call, Response<Doors> response) {
                  Log.d("Teg", response.body().toString());
                  setRecyclerView(response.body().getClass());
            }

            @Override
            public void onFailure(Call<Doors> call, Throwable t) {
                t.printStackTrace();
                Log.d("Teg", "We have a some problems");
            }
        });

        return view;
    }
}
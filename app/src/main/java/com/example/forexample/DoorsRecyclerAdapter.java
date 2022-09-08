package com.example.forexample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class DoorsRecyclerAdapter extends RecyclerView.Adapter<DoorsRecyclerAdapter.ViewHolder> {

    Context context;
    List<Doors> DataDoors;

    public DoorsRecyclerAdapter(Context context, List<Doors> DataDoors) {
        this.context = context;
        this.DataDoors = DataDoors;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.fragment_door, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.door_name.setText(DataDoors.get(position).getName());
        holder.door_locker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: Клик по замочку
            }
        });
    }

    @Override
    public int getItemCount() {
        return DataDoors.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView door_locker;
        ImageView roundedImageView;
        TextView door_name;
        TextView status_network;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            roundedImageView = itemView.findViewById(R.id.roundedImageView);
            door_name = itemView.findViewById(R.id.door_name);
            status_network = itemView.findViewById(R.id.status_network);
            door_locker = itemView.findViewById(R.id.door_locker);
        }
    }
}

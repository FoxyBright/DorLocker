package com.example.forexample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.forexample.Classes.Door;

import java.util.List;

public class DoorsRecyclerAdapter extends RecyclerView.Adapter<DoorsRecyclerAdapter.ViewHolder> {

    Context context;
    List<Door> dataDoors;

    public DoorsRecyclerAdapter(Context context, List<Door> dataDoors) {
        this.context = context;
        this.dataDoors = dataDoors;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.door_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.door_name.setText(dataDoors.get(position).getName());
        holder.door_locker.setOnClickListener(v -> {
            //TODO: Клик замочка открытия двери
            Toast.makeText(context, "Дверь " + dataDoors.get(holder.getAdapterPosition()).getName() + " открыта", Toast.LENGTH_SHORT).show();
        });
        holder.play_button.setOnClickListener(v -> {
            AppCompatActivity activity = (AppCompatActivity) v.getContext();
            IntercomFragment intercom_frag = new IntercomFragment();
            activity.getSupportFragmentManager().beginTransaction().replace(R.id.main, intercom_frag, "FragmentTransaction").addToBackStack(null).commit();
        });
        if (dataDoors.get(position).getSnapshot() != null) {
            Glide.with(context).load(dataDoors.get(position).getSnapshot()).diskCacheStrategy(DiskCacheStrategy.NONE).into(holder.roundedImageView);
            holder.status_bar.setBackgroundResource(R.drawable.bottom_rounded_corner_rect);
            holder.cam_video.setVisibility(View.VISIBLE);
            holder.status_network.setVisibility(View.VISIBLE);
            holder.roundedImageView.setClipToOutline(true);
            holder.view.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return dataDoors.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView door_locker;
        ImageView play_button;
        ImageView roundedImageView;
        TextView door_name;
        TextView status_network;
        ConstraintLayout cam_video;
        ConstraintLayout status_bar;
        View view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            roundedImageView = itemView.findViewById(R.id.roundedImageView);
            door_name = itemView.findViewById(R.id.door_name);
            status_network = itemView.findViewById(R.id.status_network);
            door_locker = itemView.findViewById(R.id.door_locker);
            play_button = itemView.findViewById(R.id.play_button);
            cam_video = itemView.findViewById(R.id.cam_video);
            status_bar = itemView.findViewById(R.id.status_bar);
            view = itemView.findViewById(R.id.view);
        }
    }
}

package com.example.forexample;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.forexample.Classes.Camera;

import java.util.List;

public class CamerasRecyclerAdapter extends RecyclerView.Adapter<CamerasRecyclerAdapter.ViewHolder> {

    Context context;
    List<Camera> cameraData;

    @SuppressLint("NotifyDataSetChanged")
    public CamerasRecyclerAdapter(Context context, List<Camera> cameraData) {
        this.context = context;
        this.cameraData = cameraData;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.fragment_cameras, parent, false);
        return new ViewHolder(view);
    }

    private void getCamerasData(ViewHolder holder, int position) {
        holder.cam_num.setText(cameraData.get(position).getName());
        Glide.with(context).load(cameraData.get(position).getSnapshot())
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(holder.roundedImageView);
        if (cameraData.get(position).getRec()) {
            holder.rec.setVisibility(View.VISIBLE);
        }
        if (cameraData.get(position).getFavorites()) {
            holder.star.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.roundedImageView.setClipToOutline(true);
        holder.room_name.setText(cameraData.get(position).getRoom());
        holder.room_name.setVisibility(View.VISIBLE);
        getCamerasData(holder, position);
    }

    @Override
    public int getItemCount() {
        return cameraData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView room_name;
        TextView cam_num;
        ImageView play_button;
        ImageView star;
        ImageView rec;
        ImageView roundedImageView;
        LinearLayout cam_linear_layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rec = itemView.findViewById(R.id.rec);
            star = itemView.findViewById(R.id.star);
            play_button = itemView.findViewById(R.id.play_button);
            roundedImageView = itemView.findViewById(R.id.roundedImageView);
            cam_num = itemView.findViewById(R.id.cam_num);
            room_name = itemView.findViewById(R.id.room_name);
            cam_linear_layout = itemView.findViewById(R.id.cam_linear_layout);
        }
    }
}
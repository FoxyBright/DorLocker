package com.example.forexample;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Camera;
import android.util.Log;
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
import com.example.forexample.DataClasses.Cameras;
import com.example.forexample.DataClasses.CamerasData;

import java.util.List;

public class CamerasRecyclerAdapter extends RecyclerView.Adapter<CamerasRecyclerAdapter.ViewHolder> {

    Context context;
    List<Cameras> CamerasData;
    List<String> RoomsData;

    @SuppressLint("NotifyDataSetChanged")
    public CamerasRecyclerAdapter(Context context, List<Cameras> CamerasData, List<String> RoomsData) {
        this.context = context;
        this.CamerasData = CamerasData;
        this.RoomsData = RoomsData;
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
        holder.cam_num.setText(CamerasData.get(position).getName());
        Glide.with(context).load(CamerasData.get(position).getSnapshot())
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(holder.roundedImageView);
        if (CamerasData.get(position).getRec()) {
            holder.rec.setVisibility(View.VISIBLE);
        }
        if (CamerasData.get(position).getFavorites()) {
            holder.star.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.roundedImageView.setClipToOutline(true);


//        if (RoomsData.size() > 0) {
//            try {
        holder.room_name.setText(RoomsData.get(RoomsData.indexOf(CamerasData.get(position).getRoom())));
        holder.room_name.setVisibility(View.VISIBLE);
//                RoomsData.remove(RoomsData.get(RoomsData.indexOf(CamerasData.get(position).getRoom())));
//                CamerasData.remove(CamerasData.get(position));
//            } catch (Exception ignored) {
//            }
//        }
        getCamerasData(holder, position);
    }

    @Override
    public int getItemCount() {
        return CamerasData.size();
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
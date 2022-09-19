package com.example.forexample.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;
import com.example.forexample.Models.Camera;
import com.example.forexample.R;

import java.util.List;

public class CamerasRecyclerAdapter extends RecyclerSwipeAdapter<CamerasRecyclerAdapter.ViewHolder> {

    Context context;
    List<Camera> cameras;
    Camera cameraSettings;

    public CamerasRecyclerAdapter(Context context, List<Camera> cameras) {
        this.context = context;
        this.cameras = cameras;
        this.cameraSettings = new Camera();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cameras_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.roundedImageView.setClipToOutline(true);
        holder.room_name.setText(cameras.get(position).getRoom());
        holder.room_name.setVisibility(View.VISIBLE);

        holder.cam_num.setText(cameras.get(position).getName());
        Glide.with(context).load(cameras.get(position).getSnapshot())
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(holder.roundedImageView);
        if (cameras.get(position).getRec()) {
            holder.rec.setVisibility(View.VISIBLE);
        }
        if (cameras.get(position).getFavorites()) {
            holder.star.setVisibility(View.VISIBLE);
            holder.favorite.setImageResource(R.drawable.favorite_button_activate);
        }
        holder.favorite.setOnClickListener(view -> {
            if (cameras.get(position).getFavorites()) {
                cameraSettings.cameraFavoriteSet(cameras.get(position).getId(), false);
                Toast.makeText(view.getContext(), "Камера " + holder.cam_num.getText().toString() + " удалена из Избранного", Toast.LENGTH_SHORT).show();
            } else {
                cameraSettings.cameraFavoriteSet(cameras.get(position).getId(), true);
                Toast.makeText(view.getContext(), "Камера " + holder.cam_num.getText().toString() +  " добавлена в Избранное", Toast.LENGTH_SHORT).show();
            }
        });

        holder.swipeLayout.setShowMode(SwipeLayout.ShowMode.LayDown);
        holder.swipeLayout.addDrag(SwipeLayout.DragEdge.Right, holder.swipeLayout.findViewById(R.id.leftSwipe));
        mItemManger.bindView(holder.itemView, position);
    }

    @Override
    public int getItemCount() {
        return cameras.size();
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.camSwipe;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView room_name;
        TextView cam_num;
        ImageView play_button;
        ImageView star;
        ImageView favorite;
        ImageView rec;
        ImageView roundedImageView;
        SwipeLayout swipeLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rec = itemView.findViewById(R.id.rec);
            star = itemView.findViewById(R.id.star);
            favorite = itemView.findViewById(R.id.favorite);
            play_button = itemView.findViewById(R.id.play_button);
            roundedImageView = itemView.findViewById(R.id.roundedImageView);
            cam_num = itemView.findViewById(R.id.cam_num);
            room_name = itemView.findViewById(R.id.room_name);
            swipeLayout = itemView.findViewById(R.id.camSwipe);
        }
    }
}
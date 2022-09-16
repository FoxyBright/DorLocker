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
import com.example.forexample.Services.DataBase.Database;

import java.util.List;

public class CamerasRecyclerAdapter extends RecyclerSwipeAdapter<CamerasRecyclerAdapter.ViewHolder> {

    Context context;
    List<Camera> cameraData;

    public CamerasRecyclerAdapter(Context context, List<Camera> cameraData) {
        this.context = context;
        this.cameraData = cameraData;
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
        holder.room_name.setText(cameraData.get(position).getRoom());
        holder.room_name.setVisibility(View.VISIBLE);

        holder.cam_num.setText(cameraData.get(position).getName());
        Glide.with(context).load(cameraData.get(position).getSnapshot())
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(holder.roundedImageView);
        if (cameraData.get(position).getRec()) {
            holder.rec.setVisibility(View.VISIBLE);
        }
        if (cameraData.get(position).getFavorites()) {
            holder.star.setVisibility(View.VISIBLE);
            holder.favorite.setImageResource(R.drawable.favorite_button_activate);
        }
        Database database = Database.getInstance(context);
        holder.favorite.setOnClickListener(view -> {
            if (cameraData.get(position).getFavorites()) {
                holder.star.setVisibility(View.INVISIBLE);
                holder.favorite.setImageResource(R.drawable.favorite_button_disactivate);
                Toast.makeText(view.getContext(), "Камера " + holder.cam_num.getText().toString() + " удалена из Избранного", Toast.LENGTH_SHORT).show();
            } else {
                holder.star.setVisibility(View.VISIBLE);
                holder.favorite.setImageResource(R.drawable.favorite_button_activate);
                Toast.makeText(view.getContext(), "Камера " + holder.cam_num.getText().toString() + " добавлена в Избранное", Toast.LENGTH_SHORT).show();
            }
        });

        holder.swipeLayout.setShowMode(SwipeLayout.ShowMode.LayDown);
        holder.swipeLayout.addDrag(SwipeLayout.DragEdge.Right, holder.swipeLayout.findViewById(R.id.left_swipe));
        mItemManger.bindView(holder.itemView, position);
    }

    @Override
    public int getItemCount() {
        return cameraData.size();
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.cam_swipe;
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
            swipeLayout = itemView.findViewById(R.id.cam_swipe);
        }
    }
}
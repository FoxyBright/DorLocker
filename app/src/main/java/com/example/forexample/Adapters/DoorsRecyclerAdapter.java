package com.example.forexample.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;
import com.example.forexample.Models.Door;
import com.example.forexample.Fragments.IntercomFragment;
import com.example.forexample.R;

import java.util.List;

public class DoorsRecyclerAdapter extends RecyclerSwipeAdapter<DoorsRecyclerAdapter.ViewHolder> {

    Context context;
    List<Door> doors;
    Door doorSettings;

    public DoorsRecyclerAdapter(Context context, List<Door> doors) {
        this.context = context;
        this.doors = doors;
        this.doorSettings = new Door();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.door_item, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(final ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.door_name.setText(doors.get(position).getName());
        holder.door_locker.setOnClickListener(v -> Toast.makeText(context, "Дверь " + doors.get(holder.getAdapterPosition()).getName() + " открыта", Toast.LENGTH_SHORT).show());

        holder.play_button.setOnClickListener(v -> {
            AppCompatActivity activity = (AppCompatActivity) v.getContext();
            IntercomFragment intercom_frag = new IntercomFragment();
            activity.getSupportFragmentManager().beginTransaction().replace(R.id.main, intercom_frag, "FragmentTransaction").addToBackStack(null).commit();
        });

        if (doors.get(position).getFavorites())
            holder.star.setImageResource(R.drawable.favorite_button_activate);

        if (doors.get(position).getSnapshot() != null) {
            Glide.with(context).load(doors.get(position).getSnapshot()).diskCacheStrategy(DiskCacheStrategy.NONE).into(holder.roundedImageView);
            holder.status_bar.setBackgroundResource(R.drawable.bottom_rounded_corner_rect);
            holder.cam_video.setVisibility(View.VISIBLE);
            holder.status_network.setVisibility(View.VISIBLE);
            holder.roundedImageView.setClipToOutline(true);
            holder.view.setVisibility(View.VISIBLE);
        }
        holder.star.setOnClickListener(view -> {
            if (doors.get(position).getFavorites()) {
                doorSettings.doorFavoriteSet(doors.get(position).getId(), false);
                Toast.makeText(view.getContext(), "Дверь " + holder.door_name.getText().toString() + " удалена из Избранного", Toast.LENGTH_SHORT).show();
            } else {
                doorSettings.doorFavoriteSet(doors.get(position).getId(), true);
                Toast.makeText(view.getContext(), "Дверь " + holder.door_name.getText().toString() + " добавлена в Избранное", Toast.LENGTH_SHORT).show();
            }
        });
        holder.edit.setOnClickListener(view -> {
            AlertDialog.Builder editData = new AlertDialog.Builder(context, R.style.AlertDialogTheme);
            editData.setTitle(R.string.newDoorName);
            final EditText doorName = new EditText(context);
            doorName.setInputType(InputType.TYPE_CLASS_TEXT);
            doorName.setTextColor(R.color.text_color);
            doorName.setText(holder.door_name.getText().toString());
            editData.setView(doorName);
            editData.setPositiveButton(R.string.save, (dialogInterface, i) -> {
                doorSettings.renamedDoor(doors.get(position).getId(), doorName.getText().toString());
                Toast.makeText(context, R.string.doorNameChanged, Toast.LENGTH_SHORT).show();
            });
            editData.setNegativeButton(R.string.cancel, (dialogInterface, i) -> dialogInterface.cancel());
            editData.show();
        });

        holder.swipeLayout.setShowMode(SwipeLayout.ShowMode.LayDown);
        holder.swipeLayout.addDrag(SwipeLayout.DragEdge.Right, holder.swipeLayout.findViewById(R.id.leftSwipe));
        mItemManger.bindView(holder.itemView, position);
    }

    @Override
    public int getItemCount() {
        return doors.size();
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.door_swipe;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        SwipeLayout swipeLayout;
        ImageView door_locker;
        ImageView play_button;
        ImageView edit;
        ImageView star;
        ImageView roundedImageView;
        TextView door_name;
        TextView status_network;
        ConstraintLayout cam_video;
        ConstraintLayout status_bar;
        View view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            roundedImageView = itemView.findViewById(R.id.roundedImageView);
            door_name = itemView.findViewById(R.id.doorName);
            status_network = itemView.findViewById(R.id.statusNetwork);
            door_locker = itemView.findViewById(R.id.doorLocker);
            play_button = itemView.findViewById(R.id.play_button);
            cam_video = itemView.findViewById(R.id.camVideo);
            status_bar = itemView.findViewById(R.id.statusBar);
            view = itemView.findViewById(R.id.view);
            swipeLayout = itemView.findViewById(R.id.door_swipe);
            edit = itemView.findViewById(R.id.edit);
            star = itemView.findViewById(R.id.star);
        }
    }
}

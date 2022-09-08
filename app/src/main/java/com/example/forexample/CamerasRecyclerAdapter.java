package com.example.forexample;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CamerasRecyclerAdapter extends RecyclerView.Adapter<CamerasRecyclerAdapter.ViewHolder> {

    ArrayList<Cameras> arrayList = new ArrayList<>();
    public CamerasRecyclerAdapter(ArrayList<Cameras> arrayList){
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.fragment_cameras, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Cameras cameras = arrayList.get(position);
//        holder.title.setText(doors.getTitle());
//        holder.text.setText(doors.getText());
//        holder.img.setImageResource(doors.getImg());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView text;
        ImageView img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

//            title = itemView.findViewById(R.id.title);
//            text = itemView.findViewById(R.id.text);
//            img = itemView.findViewById(R.id.image);
        }
    }
}

package com.example.whitneybb.adapter;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whitneybb.R;

import java.util.LinkedList;

public class StepAdapter extends RecyclerView.Adapter<StepAdapter.ViewHolder> {

    private LinkedList<String> list;
    private LayoutInflater inflater;
    private Context context;
    public AdapterView.OnItemClickListener onItemClickListener;

    public StepAdapter(Context context, LinkedList<String> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View v = inflater.inflate(R.layout.list_layout_bullet, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final boolean[] clicked = {false};
        holder.itemTv.setText(list.get(position));
        holder.itemTv.setOnClickListener(v -> {
            clicked[0] = !clicked[0];
            if (clicked[0]) {
                holder.stepThumbnail.setImageTintList(ColorStateList.valueOf(Color.GREEN));
            } else {
                holder.stepThumbnail.setImageTintList(ColorStateList.valueOf(Color.RED));
            }
        });
        holder.stepThumbnail.setOnClickListener(v -> {
            clicked[0] = !clicked[0];
            if (clicked[0]) {
                holder.stepThumbnail.setImageTintList(ColorStateList.valueOf(Color.GREEN));
            } else {
                holder.stepThumbnail.setImageTintList(ColorStateList.valueOf(Color.RED));
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }


    public void setOnItemClickListener(AdapterView.OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView itemTv;
        private ImageView stepThumbnail;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            stepThumbnail = itemView.findViewById(R.id.stepThumbnail);
            itemTv = itemView.findViewById(R.id.itemTitle);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }
}

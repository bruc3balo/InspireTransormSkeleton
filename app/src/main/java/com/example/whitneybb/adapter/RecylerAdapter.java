package com.example.whitneybb.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whitneybb.R;

import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;


public class RecylerAdapter extends RecyclerView.Adapter<RecylerAdapter.ViewHolder> {

    private LinkedList<Object> list;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private Context mContext;

    public RecylerAdapter(Context context, LinkedList<Object> list) {
        this.mInflater = LayoutInflater.from(context);
        this.list = list;
        this.mContext = context;

    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.white, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        ViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }

    }

}
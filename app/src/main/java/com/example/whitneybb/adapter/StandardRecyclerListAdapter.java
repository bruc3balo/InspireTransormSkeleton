package com.example.whitneybb.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whitneybb.R;

import java.util.LinkedList;

public class StandardRecyclerListAdapter extends RecyclerView.Adapter<StandardRecyclerListAdapter.ViewHolder> {

    private LinkedList<String> list;
    private LayoutInflater inflater;
    private Context context;
    public AdapterView.OnItemClickListener onItemClickListener;

    public StandardRecyclerListAdapter(Context context,LinkedList<String> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View v = inflater.inflate(R.layout.list_layout,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.itemTv.setText(list.get(position));
        holder.removeB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.remove(position);
                notifyDataSetChanged();
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
        private ImageButton removeB;
        private TextView itemTv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            removeB = itemView.findViewById(R.id.removeItem);
            itemTv = itemView.findViewById(R.id.itemText);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }
}

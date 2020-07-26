package com.example.whitneybb.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whitneybb.R;
import com.example.whitneybb.model.AlertsModel;

import org.jetbrains.annotations.NotNull;


public class AlertAdapter extends ListAdapter<AlertsModel,AlertAdapter.ViewHolder> {


    private ItemClickListener mClickListener;
    private OnItemClickListener onItemClickListener;

    public static final DiffUtil.ItemCallback<AlertsModel> ALERT_DIFF_CALLBACK = new DiffUtil.ItemCallback<AlertsModel>() {
        @Override
        public boolean areItemsTheSame(@NonNull AlertsModel oldItem, @NonNull AlertsModel newItem) {
            return false;
        }

        //todo

        @Override
        public boolean areContentsTheSame(@NonNull AlertsModel oldItem, @NonNull AlertsModel newItem) {
            return false;
        }
    };

    public AlertAdapter() {
        super(ALERT_DIFF_CALLBACK);
    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.alerts_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


    }


    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }


    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

    public interface OnItemClickListener {
        void onItemClick(Object object);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        ViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
            if (onItemClickListener != null && getAdapterPosition() != RecyclerView.NO_POSITION) {
                onItemClickListener.onItemClick(getItem(getAdapterPosition()));
            }
        }

    }

}
package com.example.whitneybb.adapter;

import android.app.Application;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whitneybb.R;
import com.example.whitneybb.db.alertdDb.AlertDao;
import com.example.whitneybb.db.alertdDb.AlertsDatabase;
import com.example.whitneybb.model.AlertsModel;
import com.example.whitneybb.ui.alerts.AlertsFragment;
import com.example.whitneybb.ui.alerts.AlertsViewModel;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Text;


public class AlertAdapter extends ListAdapter<AlertsModel,AlertAdapter.ViewHolder> {

    private ItemClickListener mClickListener;
    private OnItemClickListener onItemClickListener;
    private Context context;

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
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.alerts_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(getItem(position).getAlertTitle());
        holder.description.setText(getItem(position).getAlertDescription());
        holder.alertImage.setOnClickListener(v -> {
            if (getItem(position).isAlertOn()){
                Toast.makeText(context, "Alarm is on", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Alarm is off", Toast.LENGTH_SHORT).show();
            }
        });

        setOriginalState(getItem(position).isAlertOn(),holder);

        holder.alertSwitch.setOnClickListener(v -> {
            boolean switchOn = getItem(position).isAlertOn();
            switchOn = !switchOn;
            toggleSwitch(switchOn,holder,position);
        });
    }

    private void setOriginalState(boolean switchOn, ViewHolder holder) {
        if (switchOn) {
            holder.alertSwitch.setChecked(true);
            holder.alertIndicator.setBackgroundColor(Color.GREEN);
        } else {
            holder.alertSwitch.setChecked(false);
            holder.alertIndicator.setBackgroundColor(context.getColor(android.R.color.holo_orange_dark));
        }
    }

    private void toggleSwitch(boolean switchOn, ViewHolder holder, int position){
        if (switchOn) {
            holder.alertSwitch.setChecked(true);
            holder.alertIndicator.setBackgroundColor(Color.GREEN);
            getItem(position).setAlertOn(true);
            AlertsFragment.getViewModel().update(getItem(position));
            Toast.makeText(context, "Alarm set for "+getItem(position).getAlertRingTime(), Toast.LENGTH_SHORT).show();
        } else {
            holder.alertSwitch.setChecked(false);
            holder.alertIndicator.setBackgroundColor(context.getColor(android.R.color.holo_orange_dark));
            getItem(position).setAlertOn(false);
            AlertsFragment.getViewModel().update(getItem(position));
            Toast.makeText(context, "Alarm "+ getItem(position).getAlertTitle() +" is off", Toast.LENGTH_SHORT).show();
        }
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
    TextView title,description;
    SwitchCompat alertSwitch;
    View alertIndicator;
    ImageView alertImage;

        ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.alarmTitle);
            description = itemView.findViewById(R.id.alarmDescription);
            alertSwitch = itemView.findViewById(R.id.alertSwitch);
            alertIndicator = itemView.findViewById(R.id.alertIndicator);
            alertImage = itemView.findViewById(R.id.alertImage);
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
package com.example.whitneybb.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.whitneybb.R;
import com.example.whitneybb.model.DiaryModel;
import com.example.whitneybb.model.GoalsModel;
import com.example.whitneybb.model.ObjectiveModel;
import com.example.whitneybb.model.SummaryModel;

import java.util.LinkedList;

public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.SliderViewHolder> {

    private Context context;
    private LinkedList sliderItems;
    private int position;
    private static int objectiveViewType = 0;
    private static int diaryViewType = 1;
    private static int summaryViewType = 2;
    private static int goalsViewType = 3;
    public static int currentClass;


    public SliderAdapter(Context context, LinkedList modelList) {
        this.sliderItems = modelList;
        this.context = context;
    }

    @NonNull
    @Override
    public SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();

        if (viewType == objectiveViewType) {
            return new SliderViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.vp_objective_layout, parent, false)); //todo you can pause an objective

        } else if (viewType == diaryViewType) {
            return new SliderViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.vp_diary_layout, parent, false));

        } else if (viewType == summaryViewType) {
            return new SliderViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.vp_summary_layout, parent, false));

        } else if (viewType == goalsViewType) {
            return new SliderViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.vp_goals_layout, parent, false));

        } else {
            return new SliderViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.vp_layout, parent, false));
        }

    }

    @Override
    public void onBindViewHolder(@NonNull final SliderViewHolder holder, int position) {
        switch (currentClass) {
            case 0:
                System.out.println("Objective");
                LinkedList<ObjectiveModel> objectiveList = new LinkedList<>(sliderItems);
                break;
            case 1:
                System.out.println("Diary");
                LinkedList<DiaryModel> diaryList = new LinkedList<>(sliderItems);
                break;
            case 2:
                System.out.println("Summary");
                LinkedList<SummaryModel> summaryList = new LinkedList<>(sliderItems);
                break;
            case 3:
                System.out.println("Goals");
                LinkedList<GoalsModel> goalsList = new LinkedList<>(sliderItems);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return sliderItems.size();
    }

    @Override
    public int getItemViewType(int position) {
        int noViewType = 4;
        switch (currentClass) {
            case 0 :
                return objectiveViewType;
            case 1:
                return diaryViewType;
            case 2:
                return summaryViewType;
            case 3:
                return goalsViewType;
        }
        return currentClass;
    }

    public int getCurrentPosition() {
        return position;
    }

    private void updatePosition (int new_position){
        position = new_position;
    }

    static class SliderViewHolder extends RecyclerView.ViewHolder {
        //todo all variables but not initialized
        private ViewPager2 secondViewPager;

        public SliderViewHolder(@NonNull View itemView) {
            super(itemView);
            switch (currentClass) {
                case 0:
                    break;
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
            }

        }

    }

    public int getPosition() {
        Toast.makeText(context, ""+position, Toast.LENGTH_SHORT).show();
        return position;
    }

}

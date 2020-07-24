package com.example.whitneybb.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;


import com.example.whitneybb.MainActivity;
import com.example.whitneybb.R;
import com.example.whitneybb.model.DiaryModel;
import com.example.whitneybb.model.GoalsModel;
import com.example.whitneybb.model.NotesModel;
import com.example.whitneybb.model.ObjectiveModel;

import java.util.List;

public class AllMightyPullAdapter extends ListAdapter<Object, AllMightyPullAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private Context context;
    private List list;
    private ItemClickListener mClickListener;
    private OnItemClickListener onItemClickListener;


    public static final DiffUtil.ItemCallback<Object> DIFF_CALLBACK = new DiffUtil.ItemCallback<Object>() {
        @Override
        public boolean areItemsTheSame(@NonNull Object oldItem, @NonNull Object newItem) {
            if (MainActivity.currentPage == 0) {
                NotesModel olderNotes, newerNotes;
                olderNotes = (NotesModel) oldItem;
                newerNotes = (NotesModel) newItem;
                return false; //todo notes
            } else if (MainActivity.currentPage == 1) {
                GoalsModel olderGoal, newerGoal;
                olderGoal = (GoalsModel) oldItem;
                newerGoal = (GoalsModel) newItem;
                return false; //todo goals
            } else if (MainActivity.currentPage == 2) {
                ObjectiveModel olderObjective, newerObjective;
                olderObjective = (ObjectiveModel) oldItem;
                newerObjective = (ObjectiveModel) newItem;
                return false; //todo objectives
            } else if (MainActivity.currentPage == 3) {
                DiaryModel olderDiary, newerDiary;
                olderDiary = (DiaryModel) oldItem;
                newerDiary = (DiaryModel) newItem;
                return olderDiary.getDiaryId() == newerDiary.getDiaryId();
            } else {
                return false; //todo compare ids for each object for result
            }
        }

        @Override
        public boolean areContentsTheSame(@NonNull Object oldItem, @NonNull Object newItem) {
            if (MainActivity.currentPage == 0) {
                NotesModel olderNotes, newerNotes;
                olderNotes = (NotesModel) oldItem;
                newerNotes = (NotesModel) newItem;
                return false; //todo notes
            } else if (MainActivity.currentPage == 1) {
                GoalsModel olderGoal, newerGoal;
                olderGoal = (GoalsModel) oldItem;
                newerGoal = (GoalsModel) newItem;
                return false; //todo goals
            } else if (MainActivity.currentPage == 2) {
                ObjectiveModel olderObjective, newerObjective;
                olderObjective = (ObjectiveModel) oldItem;
                newerObjective = (ObjectiveModel) newItem;
                return false; //todo objectives
            } else if (MainActivity.currentPage == 3) {
                DiaryModel olderDiary, newerDiary;
                olderDiary = (DiaryModel) oldItem;
                newerDiary = (DiaryModel) newItem;
                return olderDiary.getDiaryId() == newerDiary.getDiaryId() && olderDiary.getEntryHeading().equals(newerDiary.getEntryHeading());
            } else {
                return false; //todo compare all data for each object for result
            }
        }
    };

    public AllMightyPullAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        if (viewType == 0) {
            System.out.println("Notes");
            View v = LayoutInflater.from(context).inflate(R.layout.rv_notes_layout, parent, false);
            return new ViewHolder(v);
        } else if (viewType == 1) {
            System.out.println("Goals");
            View v = LayoutInflater.from(context).inflate(R.layout.vp_goals_layout, parent, false);
            return new ViewHolder(v);
        } else if (viewType == 2) {
            System.out.println("Objectives");
            View v = LayoutInflater.from(context).inflate(R.layout.vp_objective_layout, parent, false);
            return new ViewHolder(v);
        } else if (viewType == 3) {
            System.out.println("Diary");
            View v = LayoutInflater.from(context).inflate(R.layout.vp_diary_layout, parent, false);
            return new ViewHolder(v);
        } else {
            System.out.println("Unknown request");
            View v = LayoutInflater.from(context).inflate(R.layout.white, parent, false);
            return new ViewHolder(v);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        switch (MainActivity.currentPage) {
            case 0:
                NotesModel notesModel = (NotesModel) getItem(position);
                Toast.makeText(context, "Notes", Toast.LENGTH_SHORT).show();
                break;
            case 1:
                GoalsModel goalsModel = (GoalsModel) getItem(position);
                Toast.makeText(context, "Goals", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                ObjectiveModel objectiveModel = (ObjectiveModel) getItem(position);
                Toast.makeText(context, "Objective", Toast.LENGTH_SHORT).show();
                break;
            case 3:
                DiaryModel diaryModel = (DiaryModel) getItem(position);
                holder.diaryTitle.setText(diaryModel.getEntryHeading());
                Toast.makeText(context, "Diary" + diaryModel.getEntryHeading(), Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    public DiaryModel getDiaryAt(int position) {
        return (DiaryModel) getItem(position);
    }

    public NotesModel getNoteAt(int position) {
        return (NotesModel) getItem(position);
    }

    public GoalsModel getGoalAt(int position) {
        return (GoalsModel) getItem(position);
    }

    public ObjectiveModel getObjectivesAt(int position) {
        return (ObjectiveModel) getItem(position);
    }


    @Override
    public int getItemViewType(int position) {
        return MainActivity.currentPage;
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
        TextView diaryTitle;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            diaryTitle = itemView.findViewById(R.id.diaryTitle);
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

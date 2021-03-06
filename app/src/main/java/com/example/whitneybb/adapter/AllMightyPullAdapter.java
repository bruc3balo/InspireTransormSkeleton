package com.example.whitneybb.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.example.whitneybb.MainActivity;
import com.example.whitneybb.R;
import com.example.whitneybb.model.DiaryModel;
import com.example.whitneybb.model.DiaryPageModel;
import com.example.whitneybb.model.GoalsModel;
import com.example.whitneybb.model.NotesModel;
import com.example.whitneybb.model.ObjectiveModel;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import static com.example.whitneybb.model.GoalsModel.LONG_TERM;
import static com.example.whitneybb.model.GoalsModel.MID_TERM;
import static com.example.whitneybb.model.GoalsModel.SHORT_TERM;

public class AllMightyPullAdapter extends ListAdapter<Object, AllMightyPullAdapter.ViewHolder> {


    private Context context;
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
                return olderDiary.getDiaryId().equals(newerDiary.getDiaryId());
            } else {
                return false; //todo compare ids for each object for result
            }
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull Object oldItem, @NonNull Object newItem) {
            if (MainActivity.currentPage == 0) {
                NotesModel olderNotes, newerNotes;
                olderNotes = (NotesModel) oldItem;
                newerNotes = (NotesModel) newItem;
                return olderNotes.getNoteId().equals(newerNotes.getNoteId()) && olderNotes.getNoteContent().equals(newerNotes.getNoteContent()); //todo notes
            } else if (MainActivity.currentPage == 1) {
                GoalsModel olderGoal, newerGoal;
                olderGoal = (GoalsModel) oldItem;
                newerGoal = (GoalsModel) newItem;
                return olderGoal.getGoalId().equals(newerGoal.getGoalId()) && olderGoal.getGoalContent().equals(newerGoal.getGoalContent()) && olderGoal.getGoalNotes().equals(newerGoal.getGoalNotes()) && olderGoal.getGoalUpdatedAt().equals(newerGoal.getGoalUpdatedAt()) && olderGoal.getStepsToGoal().equals(newerGoal.getStepsToGoal()); //todo goals
            } else if (MainActivity.currentPage == 2) {
                ObjectiveModel olderObjective, newerObjective;
                olderObjective = (ObjectiveModel) oldItem;
                newerObjective = (ObjectiveModel) newItem;
                return olderObjective.getObjectiveId().equals(newerObjective.getObjectiveId()) && olderObjective.getObjectiveSteps().equals(newerObjective.getObjectiveSteps()) && olderObjective.getObjectiveRemarks().equals(newerObjective.getObjectiveRemarks()) && olderObjective.getObjectiveLimits().equals(newerObjective.getObjectiveLimits()) && olderObjective.getObjectiveTitle().equals(newerObjective.getObjectiveTitle()); //todo objectives
            } else if (MainActivity.currentPage == 3) {
                DiaryModel olderDiary, newerDiary;
                olderDiary = (DiaryModel) oldItem;
                newerDiary = (DiaryModel) newItem;
                return olderDiary.getDiaryId().equals(newerDiary.getDiaryId()) && olderDiary.getDiaryTitle().equals(newerDiary.getDiaryTitle());
            } else if (MainActivity.currentPage == 6) {
                DiaryPageModel olderPage, newerPage;
                olderPage = (DiaryPageModel) oldItem;
                newerPage = (DiaryPageModel) newItem;
                return olderPage.getEntryId().equals(newerPage.getEntryId()) && olderPage.getEntryBody().equals(newerPage.getEntryBody());
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
        } else if (viewType == 6) {
            System.out.println("Diary Page");
            View v = LayoutInflater.from(context).inflate(R.layout.vp_diary_page_layout, parent, false);
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
                try {
                    NotesModel notesModel = (NotesModel) getItem(position);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case 1:
                try {
                    GoalsModel goalsModel = (GoalsModel) getItem(position);
                    setGoalTerm(goalsModel, holder);
                    holder.goalTitle.setText(goalsModel.getGoalContent());

                    holder.goalStepsRecyclerView.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL, false));
                    StepAdapter adapter = new StepAdapter(context, listFromString(goalsModel.getStepsToGoal()));
                    adapter.notifyDataSetChanged();
                    holder.goalStepsRecyclerView.setAdapter(adapter);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;
            case 2:
                try {
                    ObjectiveModel objectiveModel = (ObjectiveModel) getItem(position);
                    holder.obj_title_tv.setText(objectiveModel.getObjectiveTitle());
                    holder.obj_description_tv.setText(objectiveModel.getAboutObjective());

                    holder.objectiveStepsRv.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL, false));
                    StepAdapter adapterObj = new StepAdapter(context, listFromString(objectiveModel.objectiveSteps));
                    adapterObj.notifyDataSetChanged();
                    holder.objectiveStepsRv.setAdapter(adapterObj);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;
            case 3:
                try {
                    DiaryModel diaryModel = (DiaryModel) getItem(position);
                    // holder.diaryCover.setImageURI(Uri.fromFile(new File(diaryModel.getDiaryCoverUrl())));
                    Glide.with(context).load(diaryModel.getDiaryCoverUrl()).into(holder.diaryCover);
                    holder.diaryTitle.setText(diaryModel.getDiaryTitle());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;
            case 6:
                try {
                    DiaryPageModel page = (DiaryPageModel) getItem(position);
                    holder.pageTitle.setText(page.getEntryTitle());
                    holder.pageContent.setText(page.getEntryBody());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;
            default:
                break;
        }
    }

    private void setGoalTerm(GoalsModel goal, ViewHolder holder) {
        if (goal.getGoalTerm().equals(SHORT_TERM)) {
            holder.goalTermBackgroundCard.setCardBackgroundColor(context.getColor(android.R.color.holo_red_light));
        } else if (goal.getGoalTerm().equals(MID_TERM)) {
            holder.goalTermBackgroundCard.setCardBackgroundColor(context.getColor(android.R.color.holo_orange_light));

        } else if (goal.getGoalTerm().equals(LONG_TERM)) {
            holder.goalTermBackgroundCard.setCardBackgroundColor(context.getColor(android.R.color.holo_green_light));

        } else {
            holder.goalTermBackgroundCard.setCardBackgroundColor(context.getColor(android.R.color.darker_gray));

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

    public DiaryPageModel getPageAt(int position) {
        return (DiaryPageModel) getItem(position);
    }

    public static LinkedList<String> listFromString(String s) {

        int listCount = 0;
        String word = "";
        LinkedList<String> wordsList = new LinkedList<>();
        boolean print = false;

        for (int i = 0; i <= s.length() - 1; i++) { //count list
            System.out.println(s.substring(i, i + 1));
            if (s.substring(i, i + 1).contains("{")) {
                listCount++;
                System.out.println(listCount);

            }
        }

        for (int i = 0; i <= s.length() - 1; i++) { //start of list

            if (s.substring(i, i + 1).contains("{")) { //start of word, counting letters
                print = true;
            } else if (s.substring(i, i + 1).contains("}")) {
                print = false;
            }

            if (print) {
                word = word.concat(s.substring(i + 1, i + 2));
            } else {
                wordsList.add(word.substring(0, word.length() - 1));
                word = "";
            }
        }
        return wordsList;
    }


    @Override
    public int getItemViewType(int position) {
        return MainActivity.currentPage;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

   /* private LinkedList<String> listFromString (String s) {
        int listCount = 0;
        for (int i = 0; i <= s.length() - 1; i++) {
            if (s.substring(i,i+1).contains("{")) {
                listCount++;
                System.out.println(listCount);
            }
        }

        for (int b = 0;b <= listCount;b++) {

        }
        return
    }*/

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
        ImageView diaryCover;

        TextView goalTitle;
        RecyclerView goalStepsRecyclerView;
        CardView goalTermBackgroundCard;

        TextView obj_title_tv, obj_description_tv;
        RecyclerView objectiveStepsRv;

        TextView pageTitle, pageContent;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            diaryTitle = itemView.findViewById(R.id.diaryTitle_tv);
            goalTitle = itemView.findViewById(R.id.goalTitle_tv);
            obj_title_tv = itemView.findViewById(R.id.obj_title_tv);


            goalTermBackgroundCard = itemView.findViewById(R.id.goalTermBackgroundCard);
            obj_description_tv = itemView.findViewById(R.id.obj_description);
            diaryCover = itemView.findViewById(R.id.diaryCover);

            goalStepsRecyclerView = itemView.findViewById(R.id.goalStepsRecyclerView);
            pageTitle = itemView.findViewById(R.id.pageTitle);
            pageContent = itemView.findViewById(R.id.pageContent);

            objectiveStepsRv = itemView.findViewById(R.id.objectiveStepsRv);

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

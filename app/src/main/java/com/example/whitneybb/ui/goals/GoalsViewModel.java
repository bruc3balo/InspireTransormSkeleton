package com.example.whitneybb.ui.goals;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.whitneybb.db.goalsDb.GoalRepository;
import com.example.whitneybb.model.GoalsModel;

import java.util.List;

public class GoalsViewModel extends AndroidViewModel {

    private GoalRepository goalRepository;
    private LiveData<List<GoalsModel>> allGoals;

    public GoalsViewModel(@NonNull Application application) {
        super(application);
        goalRepository = new GoalRepository(application);
        allGoals = goalRepository.getAllGoals();
    }

    public void insert(GoalsModel goal) {
        goalRepository.insert(goal);
    }

    public void delete(GoalsModel goal) {
        goalRepository.delete(goal);
    }

    public void update(GoalsModel diary) {
        goalRepository.update(diary);
    }

    public void deleteAllGoals() {
        goalRepository.deleteAllGoals();
    }

    public LiveData<List<GoalsModel>> getAllGoals() {
        return allGoals;
    }
}
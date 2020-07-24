package com.example.whitneybb.db.goalsDb;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;


import com.example.whitneybb.model.DiaryModel;
import com.example.whitneybb.model.GoalsModel;

import java.util.List;

public class GoalRepository {

    private GoalDao goalDao;
    private LiveData<List<GoalsModel>> allGoals;

    public GoalRepository(Application application) {
        GoalDatabase database = GoalDatabase.getInstance(application);
        goalDao = database.goalDao();
        allGoals = goalDao.getAllGoals();
    }

    public void insert(GoalsModel goal) {
        new InsertGoalsAsyncTask(goalDao).execute(goal);
    }

    public void update(GoalsModel goal) {
        new UpdateGoalsAsyncTask(goalDao).execute(goal);
    }

    public void delete(GoalsModel goal) {
        new DeleteGoalsAsyncTask(goalDao).execute(goal);
    }

    public void deleteAllGoals() {
        new DeleteAllGoalsAsyncTask(goalDao).execute();
    }

    public LiveData<List<GoalsModel>> getAllGoals() {
        return allGoals;
    }

    private static class InsertGoalsAsyncTask extends AsyncTask<GoalsModel, Void, Void> {
        private GoalDao goalDao;

        private InsertGoalsAsyncTask(GoalDao goalDao) {
            this.goalDao = goalDao;
        }

        @Override
        protected Void doInBackground(GoalsModel... goalsModels) {
            try {
                goalDao.insert(goalsModels[0]);
                System.out.println("New Goal");
            } catch (Exception e) {
                goalDao.update(goalsModels[0]);
                System.out.println("Update Goal");
            }
            return null;
        }

    }

    private static class UpdateGoalsAsyncTask extends AsyncTask<GoalsModel, Void, Void> {
        private GoalDao goalDao;

        private UpdateGoalsAsyncTask(GoalDao goalDao) {
            this.goalDao = goalDao;
        }

        @Override
        protected Void doInBackground(GoalsModel... goalsModels) {
            try {
                goalDao.update(goalsModels[0]);
                System.out.println("Update Goal");
            } catch (Exception e) {
                goalDao.insert(goalsModels[0]);
                System.out.println("Insert Goal");
            }
            return null;
        }

    }

    private static class DeleteGoalsAsyncTask extends AsyncTask<GoalsModel, Void, Void> {
        private GoalDao goalDao;

        private DeleteGoalsAsyncTask(GoalDao goalDao) {
            this.goalDao = goalDao;
        }

        @Override
        protected Void doInBackground(GoalsModel... goalsModels) {
            try {
                goalDao.delete(goalsModels[0]);
                System.out.println("Goal Deleted");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

    }

    private static class DeleteAllGoalsAsyncTask extends AsyncTask<GoalsModel, Void, Void> {

        private GoalDao goalDao;

        private DeleteAllGoalsAsyncTask(GoalDao goalDao) {
            this.goalDao = goalDao;
        }

        @Override
        protected Void doInBackground(GoalsModel... goalsModels) {
            goalDao.deleteAllGoals();
            return null;
        }
    }
}

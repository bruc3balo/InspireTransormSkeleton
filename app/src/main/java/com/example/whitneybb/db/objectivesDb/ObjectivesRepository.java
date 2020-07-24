package com.example.whitneybb.db.objectivesDb;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;


import com.example.whitneybb.model.ObjectiveModel;

import java.util.List;

public class ObjectivesRepository {

    private ObjectivesDao objectivesDao;
    private LiveData<List<ObjectiveModel>> allObjectives;

    public ObjectivesRepository(Application application) {
        ObjectivesDatabase database = ObjectivesDatabase.getInstance(application);
        objectivesDao = database.objectivesDao();
        allObjectives = objectivesDao.getAllObjectives();
    }

    public void insert(ObjectiveModel objective) {
        new InsertObjectivesAsyncTask(objectivesDao).execute(objective);
    }

    public void update(ObjectiveModel objective) {
        new UpdateObjectivesAsyncTask(objectivesDao).execute(objective);
    }

    public void delete(ObjectiveModel objective) {
        new DeleteObjectivesAsyncTask(objectivesDao).execute(objective);
    }

    public void deleteAllObjectives() {
        new DeleteAllObjectivesAsyncTask(objectivesDao).execute();
    }

    public LiveData<List<ObjectiveModel>> getAllObjectives() {
        return allObjectives;
    }

    private static class InsertObjectivesAsyncTask extends AsyncTask<ObjectiveModel, Void, Void> {
        private ObjectivesDao objectivesDao;

        private InsertObjectivesAsyncTask(ObjectivesDao objectivesDao) {
            this.objectivesDao = objectivesDao;
        }

        @Override
        protected Void doInBackground(ObjectiveModel... objectiveModels) {
            try {
                objectivesDao.insert(objectiveModels[0]);
                System.out.println("New Objective");
            } catch (Exception e) {
                objectivesDao.update(objectiveModels[0]);
                System.out.println("Update Objective");
            }
            return null;
        }

    }

    private static class UpdateObjectivesAsyncTask extends AsyncTask<ObjectiveModel, Void, Void> {
        private ObjectivesDao objectivesDao;

        private UpdateObjectivesAsyncTask(ObjectivesDao objectivesDao) {
            this.objectivesDao = objectivesDao;
        }

        @Override
        protected Void doInBackground(ObjectiveModel... objectiveModels) {
            try {
                objectivesDao.update(objectiveModels[0]);
                System.out.println("Update Objective");
            } catch (Exception e) {
                objectivesDao.insert(objectiveModels[0]);
                System.out.println("Insert Objective");
            }
            return null;
        }

    }

    private static class DeleteObjectivesAsyncTask extends AsyncTask<ObjectiveModel, Void, Void> {
        private ObjectivesDao objectivesDao;

        private DeleteObjectivesAsyncTask(ObjectivesDao objectivesDao) {
            this.objectivesDao = objectivesDao;
        }

        @Override
        protected Void doInBackground(ObjectiveModel... objectiveModels) {
            try {
                objectivesDao.delete(objectiveModels[0]);
                System.out.println("Delete Objective");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

    }

    private static class DeleteAllObjectivesAsyncTask extends AsyncTask<ObjectiveModel, Void, Void> {

        private ObjectivesDao objectivesDao;

        private DeleteAllObjectivesAsyncTask(ObjectivesDao objectivesDao) {
            this.objectivesDao = objectivesDao;
        }

        @Override
        protected Void doInBackground(ObjectiveModel... objectiveModels) {
            objectivesDao.deleteAllObjectives();
            return null;
        }
    }
}

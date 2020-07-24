package com.example.whitneybb.db.backUpDb;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;


import com.example.whitneybb.model.BackUpModel;
import com.example.whitneybb.model.DiaryModel;

import java.util.List;

public class BackUpRepository {

    private BackUpDao backUpDao;
    private LiveData<List<BackUpModel>> allBackUps;

    public BackUpRepository(Application application) {
        BackUpDatabase database = BackUpDatabase.getInstance(application);
        backUpDao = database.backUpDao();
        allBackUps = backUpDao.getAllBackUps();
    }

    public void insert(BackUpModel backUp) {
        new InsertBackUpAsyncTask(backUpDao).execute(backUp);
    }

    public void update(BackUpModel backUp) {
        new UpdateBackUpAsyncTask(backUpDao).execute(backUp);
    }

    public void delete(BackUpModel backUp) {
        new DeleteBackUpAsyncTask(backUpDao).execute(backUp);
    }

    public void deleteAllBacKUps() {
        new DeleteAllBackUpAsyncTask(backUpDao).execute();
    }

    public LiveData<List<BackUpModel>> getAllBackUps() {
        return allBackUps;
    }

    private static class InsertBackUpAsyncTask extends AsyncTask<BackUpModel, Void, Void> {

        private BackUpDao backUpDao;

        private InsertBackUpAsyncTask(BackUpDao backUpDao) {
            this.backUpDao = backUpDao;
        }

        @Override
        protected Void doInBackground(BackUpModel... backUpModels) {
            try {
                backUpDao.insert(backUpModels[0]);
                System.out.println("New Back Up");
            } catch (Exception e) {
                backUpDao.update(backUpModels[0]);
                System.out.println("Update Back Up");
            }
            return null;
        }

    }

    private static class UpdateBackUpAsyncTask extends AsyncTask<BackUpModel, Void, Void> {

        private BackUpDao backUpDao;

        private UpdateBackUpAsyncTask(BackUpDao backUpDao) {
            this.backUpDao = backUpDao;
        }

        @Override
        protected Void doInBackground(BackUpModel... backUpModels) {
            try {
                backUpDao.update(backUpModels[0]);
                System.out.println("Update Back Up");
            } catch (Exception e) {
                backUpDao.insert(backUpModels[0]);
                System.out.println("Insert Back Up");
            }
            return null;
        }

    }

    private static class DeleteBackUpAsyncTask extends AsyncTask<BackUpModel, Void, Void> {

        private BackUpDao backUpDao;

        private DeleteBackUpAsyncTask(BackUpDao backUpDao) {
            this.backUpDao = backUpDao;
        }

        @Override
        protected Void doInBackground(BackUpModel... backUpModels) {
            try {
                backUpDao.delete(backUpModels[0]);
                System.out.println("Deleted Back Up");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

    }

    private static class DeleteAllBackUpAsyncTask extends AsyncTask<BackUpModel, Void, Void> {

        private BackUpDao backUpDao;

        private DeleteAllBackUpAsyncTask(BackUpDao backUpDao) {
            this.backUpDao = backUpDao;
        }

        @Override
        protected Void doInBackground(BackUpModel... backUpModels) {
            backUpDao.deleteAllBackUps();
            System.out.println("Deleted Back up");
            return null;
        }
    }
}

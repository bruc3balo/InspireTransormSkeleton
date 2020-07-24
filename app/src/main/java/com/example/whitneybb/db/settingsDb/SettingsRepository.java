package com.example.whitneybb.db.settingsDb;

import android.app.Application;
import android.os.AsyncTask;


import androidx.lifecycle.LiveData;

import com.example.whitneybb.model.SettingsModel;

import java.util.List;

public class SettingsRepository {

    private SettingsDao settingsDao;
    private LiveData<List<SettingsModel>> allSettings;

    public SettingsRepository(Application application) {
        SettingsDatabase database = SettingsDatabase.getInstance(application);
        settingsDao = database.settingsDao();
        allSettings = settingsDao.getAllSettings();
    }

    public void insert(SettingsModel settings) {
        new InsertSettingsAsyncTask(settingsDao).execute(settings);
    }

    public void update(SettingsModel settings) {
        new UpdateSettingsAsyncTask(settingsDao).execute(settings);
    }

    public void delete(SettingsModel settings) {
        new DeleteSettingsAsyncTask(settingsDao).execute(settings);
    }

    public void deleteAllBacKUps() {
        new DeleteAllSettingsAsyncTask(settingsDao).execute();
    }

    public LiveData<List<SettingsModel>> getAllBackUps() {
        return allSettings;
    }

    private static class InsertSettingsAsyncTask extends AsyncTask<SettingsModel, Void, Void> {

        private SettingsDao settingsDao;

        private InsertSettingsAsyncTask(SettingsDao settingsDao) {
            this.settingsDao = settingsDao;
        }

        @Override
        protected Void doInBackground(SettingsModel... settingsModels) {
            try {
                settingsDao.insert(settingsModels[0]);
                System.out.println("New Settings");
            } catch (Exception e) {
                settingsDao.update(settingsModels[0]);
                System.out.println("Update Settings");
            }
            return null;
        }

    }

    private static class UpdateSettingsAsyncTask extends AsyncTask<SettingsModel, Void, Void> {

        private SettingsDao settingsDao;

        private UpdateSettingsAsyncTask(SettingsDao settingsDao) {
            this.settingsDao = settingsDao;
        }

        @Override
        protected Void doInBackground(SettingsModel... settingsModels) {
            try {
                settingsDao.update(settingsModels[0]);
                System.out.println("Update Settings");
            } catch (Exception e) {
                settingsDao.insert(settingsModels[0]);
                System.out.println("New Settings");
            }
            return null;
        }

    }

    private static class DeleteSettingsAsyncTask extends AsyncTask<SettingsModel, Void, Void> {

        private SettingsDao settingsDao;

        private DeleteSettingsAsyncTask(SettingsDao settingsDao) {
            this.settingsDao = settingsDao;
        }

        @Override
        protected Void doInBackground(SettingsModel... settingsModels) {
            try {
                settingsDao.delete(settingsModels[0]);
                System.out.println("Deleted Settings");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

    }

    private static class DeleteAllSettingsAsyncTask extends AsyncTask<SettingsModel, Void, Void> {

        private SettingsDao settingsDao;

        private DeleteAllSettingsAsyncTask(SettingsDao settingsDao) {
            this.settingsDao = settingsDao;
        }

        @Override
        protected Void doInBackground(SettingsModel... settingsModels) {
            settingsDao.deleteAllSettings();
            System.out.println("Deleted Settings");
            return null;
        }
    }
}

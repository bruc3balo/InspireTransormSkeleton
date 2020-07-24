package com.example.whitneybb.db.alertdDb;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;


import com.example.whitneybb.model.AlertsModel;
import com.example.whitneybb.model.DiaryModel;

import java.util.List;

public class AlertsRepository {

    private AlertDao alertDao;
    private LiveData<List<AlertsModel>> allAlerts;

    public AlertsRepository(Application application) {
        AlertsDatabase database = AlertsDatabase.getInstance(application);
        alertDao = database.alertDao();
        allAlerts = alertDao.getAllAlerts();
    }

    public void insert(AlertsModel alert) {
        new InsertAlertAsyncTask(alertDao).execute(alert);
    }

    public void update(AlertsModel alert) {
        new UpdateAlertAsyncTask(alertDao).execute(alert);
    }

    public void delete(AlertsModel alert) {
        new DeleteAlertAsyncTask(alertDao).execute(alert);
    }

    public void deleteAllAlerts() {
        new DeleteAllAlertAsyncTask(alertDao).execute();
    }

    public LiveData<List<AlertsModel>> getAllAlerts() {
        return allAlerts;
    }

    private static class InsertAlertAsyncTask extends AsyncTask<AlertsModel, Void, Void> {

        private AlertDao alertDao;

        private InsertAlertAsyncTask(AlertDao alertDao) {
            this.alertDao = alertDao;
        }

        @Override
        protected Void doInBackground(AlertsModel... alertsModels) {
            try {
                alertDao.insert(alertsModels[0]);
                System.out.println("New Alert");
            } catch (Exception e) {
                alertDao.update(alertsModels[0]);
                System.out.println("Update Alert");
            }
            return null;
        }

    }

    private static class UpdateAlertAsyncTask extends AsyncTask<AlertsModel, Void, Void> {

        private AlertDao alertDao;

        private UpdateAlertAsyncTask(AlertDao alertDao) {
            this.alertDao = alertDao;
        }

        @Override
        protected Void doInBackground(AlertsModel... alertsModels) {
            try {
                alertDao.update(alertsModels[0]);
                System.out.println("Update Alert");
            } catch (Exception e) {
                alertDao.insert(alertsModels[0]);
                System.out.println("New Alert");
            }
            return null;
        }

    }

    private static class DeleteAlertAsyncTask extends AsyncTask<AlertsModel, Void, Void> {

        private AlertDao alertDao;

        private DeleteAlertAsyncTask(AlertDao alertDao) {
            this.alertDao = alertDao;
        }

        @Override
        protected Void doInBackground(AlertsModel... alertsModels) {
            try {
                alertDao.delete(alertsModels[0]);
                System.out.println("Delete Alert");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

    }

    private static class DeleteAllAlertAsyncTask extends AsyncTask<AlertsModel, Void, Void> {

        private AlertDao alertDao;

        private DeleteAllAlertAsyncTask(AlertDao alertDao) {
            this.alertDao = alertDao;
        }

        @Override
        protected Void doInBackground(AlertsModel... alertsModels) {
            alertDao.deleteAllAlerts();
            return null;
        }
    }
}

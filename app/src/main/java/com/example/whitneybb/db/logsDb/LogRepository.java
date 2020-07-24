package com.example.whitneybb.db.logsDb;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;


import com.example.whitneybb.model.LogModel;

import java.util.List;

public class LogRepository {

    private LogDao logDao;
    private LiveData<List<LogModel>> allLogs;

    public LogRepository(Application application) {
        LogDatabase database = LogDatabase.getInstance(application);
        logDao = database.logDao();
        allLogs = logDao.getAllLogs();
    }

    public void insert(LogModel log) {
        new InsertLogAsyncTask(logDao).execute(log);
    }

    public void update(LogModel log) {
        new UpdateLogAsyncTask(logDao).execute(log);
    }

    public void delete(LogModel log) {
        new DeleteLogAsyncTask(logDao).execute(log);
    }

    public void deleteAllLogs() {
        new DeleteAllLogsAsyncTask(logDao).execute();
    }

    public LiveData<List<LogModel>> getAllLogs() {
        return allLogs;
    }

    private static class InsertLogAsyncTask extends AsyncTask<LogModel, Void, Void> {
        private LogDao logDao;

        private InsertLogAsyncTask(LogDao logDao) {
            this.logDao = logDao;
        }

        @Override
        protected Void doInBackground(LogModel... logModels) {
            try {
                logDao.insert(logModels[0]);
                System.out.println("New Log");
            } catch (Exception e) {
                logDao.update(logModels[0]);
                System.out.println("Update Log");
            }
            return null;
        }

    }

    private static class UpdateLogAsyncTask extends AsyncTask<LogModel, Void, Void> {
        private LogDao logDao;

        private UpdateLogAsyncTask(LogDao logDao) {
            this.logDao = logDao;
        }

        @Override
        protected Void doInBackground(LogModel... logModels) {
            try {
                logDao.update(logModels[0]);
                System.out.println("Update Log");
            } catch (Exception e) {
                logDao.insert(logModels[0]);
                System.out.println("Insert Log");
            }
            return null;
        }

    }

    private static class DeleteLogAsyncTask extends AsyncTask<LogModel, Void, Void> {
        private LogDao logDao;

        private DeleteLogAsyncTask(LogDao logDao) {
            this.logDao = logDao;
        }

        @Override
        protected Void doInBackground(LogModel... logModels) {
            try {
                logDao.delete(logModels[0]);
                System.out.println("Delete Log");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

    }

    private static class DeleteAllLogsAsyncTask extends AsyncTask<LogModel, Void, Void> {

        private LogDao logDao;

        private DeleteAllLogsAsyncTask(LogDao logDao) {
            this.logDao = logDao;
        }

        @Override
        protected Void doInBackground(LogModel... logModels) {
            logDao.deleteAllLogs();
            System.out.println("Deleted All Logs ");
            return null;
        }
    }
}

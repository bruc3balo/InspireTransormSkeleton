package com.example.whitneybb.db.alertdDb;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.whitneybb.model.AlertsModel;


@Database(entities = {AlertsModel.class}, version = 1)
public abstract class AlertsDatabase extends RoomDatabase {
    private static AlertsDatabase alertsInstance;
    private static Callback roomCallback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDBAsyncTask(alertsInstance).execute();
        }
    };

    public static synchronized AlertsDatabase getInstance(Context context) {
        //synch for only one instance
        if (alertsInstance == null) {
            alertsInstance = Room.databaseBuilder(context.getApplicationContext(), AlertsDatabase.class, "alert_database").fallbackToDestructiveMigration().addCallback(roomCallback).build();
        }
        return alertsInstance;
    }

    public abstract AlertDao alertDao();

    private static class PopulateDBAsyncTask extends AsyncTask<Void, Void, Void> {
        private AlertDao alertDao;

        private PopulateDBAsyncTask(AlertsDatabase db) {
            alertDao = db.alertDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            //only inserted first time

            // diaryDao.insert(new DiaryModel("h1",1));
            // diaryDao.insert(new DiaryModel("h2",2));
            // diaryDao.insert(new DiaryModel("h3",3));
            System.out.println("Inserting data");

            return null;
        }
    }

}

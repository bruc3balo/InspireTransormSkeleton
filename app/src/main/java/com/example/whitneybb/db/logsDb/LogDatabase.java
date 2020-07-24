package com.example.whitneybb.db.logsDb;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.whitneybb.model.LogModel;


@Database(entities = {LogModel.class}, version = 1)
public abstract class LogDatabase extends RoomDatabase {
    private static LogDatabase logInstance;
    private static Callback roomCallback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDBAsyncTask(logInstance).execute();
        }
    };

    public static synchronized LogDatabase getInstance(Context context) {
        //synch for only one instance
        if (logInstance == null) {
            logInstance = Room.databaseBuilder(context.getApplicationContext(), LogDatabase.class, "log_database").fallbackToDestructiveMigration().addCallback(roomCallback).build();
        }
        return logInstance;
    }

    public abstract LogDao logDao();

    private static class PopulateDBAsyncTask extends AsyncTask<Void, Void, Void> {
        private LogDao logDao;

        private PopulateDBAsyncTask(LogDatabase db) {
            logDao = db.logDao();
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

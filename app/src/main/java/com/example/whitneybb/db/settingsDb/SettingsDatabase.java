package com.example.whitneybb.db.settingsDb;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.whitneybb.model.SettingsModel;


@Database(entities = {SettingsModel.class}, version = 1)
public abstract class SettingsDatabase extends RoomDatabase {
    private static SettingsDatabase settingsInstance;
    private static Callback roomCallback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDBAsyncTask(settingsInstance).execute();
        }
    };

    public static synchronized SettingsDatabase getInstance(Context context) {
        //synch for only one instance
        if (settingsInstance == null) {
            settingsInstance = Room.databaseBuilder(context.getApplicationContext(), SettingsDatabase.class, "settings_database").fallbackToDestructiveMigration().addCallback(roomCallback).build();
        }
        return settingsInstance;
    }

    public abstract SettingsDao settingsDao();

    private static class PopulateDBAsyncTask extends AsyncTask<Void, Void, Void> {
        private SettingsDao settingsDao;

        private PopulateDBAsyncTask(SettingsDatabase db) {
            settingsDao = db.settingsDao();
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

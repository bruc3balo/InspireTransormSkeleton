package com.example.whitneybb.db.objectivesDb;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.whitneybb.model.ObjectiveModel;


@Database(entities = {ObjectiveModel.class}, version = 1)
public abstract class ObjectivesDatabase extends RoomDatabase {
    private static ObjectivesDatabase objectivesInstance;
    private static Callback roomCallback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDBAsyncTask(objectivesInstance).execute();
        }
    };

    public static synchronized ObjectivesDatabase getInstance(Context context) {
        //synch for only one instance

        if (objectivesInstance == null) {
            objectivesInstance = Room.databaseBuilder(context.getApplicationContext(), ObjectivesDatabase.class, "objective_database").fallbackToDestructiveMigration().addCallback(roomCallback).build();
        }
        return objectivesInstance;
    }

    public abstract ObjectivesDao objectivesDao();

    private static class PopulateDBAsyncTask extends AsyncTask<Void, Void, Void> {
        private ObjectivesDao objectivesDao;

        private PopulateDBAsyncTask(ObjectivesDatabase db) {
            this.objectivesDao = db.objectivesDao();
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

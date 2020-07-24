package com.example.whitneybb.db.goalsDb;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.whitneybb.model.GoalsModel;


@Database(entities = {GoalsModel.class}, version = 1)
public abstract class GoalDatabase extends RoomDatabase {
    private static GoalDatabase goalInstance;
    private static Callback roomCallback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDBAsyncTask(goalInstance).execute();
        }
    };

    public static synchronized GoalDatabase getInstance(Context context) {
        //synch for only one instance
        if (goalInstance == null) {
            goalInstance = Room.databaseBuilder(context.getApplicationContext(), GoalDatabase.class, "goal_database").fallbackToDestructiveMigration().addCallback(roomCallback).build();
        }
        return goalInstance;
    }

    public abstract GoalDao goalDao();

    private static class PopulateDBAsyncTask extends AsyncTask<Void, Void, Void> {
        private GoalDao goalDao;

        private PopulateDBAsyncTask(GoalDatabase db) {
            goalDao = db.goalDao();
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

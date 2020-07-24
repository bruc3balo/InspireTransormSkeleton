package com.example.whitneybb.db.backUpDb;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.whitneybb.model.BackUpModel;


@Database(entities = {BackUpModel.class}, version = 1)
public abstract class BackUpDatabase extends RoomDatabase {
    private static BackUpDatabase backUpInstance;
    private static Callback roomCallback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDBAsyncTask(backUpInstance).execute();
        }
    };

    public static synchronized BackUpDatabase getInstance(Context context) {
        //synch for only one instance
        if (backUpInstance == null) {
            backUpInstance = Room.databaseBuilder(context.getApplicationContext(), BackUpDatabase.class, "back_up_database").fallbackToDestructiveMigration().addCallback(roomCallback).build();
        }
        return backUpInstance;
    }

    public abstract BackUpDao backUpDao();

    private static class PopulateDBAsyncTask extends AsyncTask<Void, Void, Void> {
        private BackUpDao backUpDao;

        private PopulateDBAsyncTask(BackUpDatabase db) {
            backUpDao = db.backUpDao();
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

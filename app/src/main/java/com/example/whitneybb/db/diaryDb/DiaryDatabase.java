package com.example.whitneybb.db.diaryDb;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.whitneybb.model.DiaryModel;


@Database(entities = {DiaryModel.class}, version = 1)
public abstract class DiaryDatabase extends RoomDatabase {
    private static DiaryDatabase diaryInstance;
    public abstract DiaryDao diaryDao();
    public static synchronized DiaryDatabase getInstance(Context context) {
        //synch for only one instance
        if (diaryInstance == null) {
            diaryInstance = Room.databaseBuilder(context.getApplicationContext(),DiaryDatabase.class,"diary_database").fallbackToDestructiveMigration().addCallback(roomCallback).build();
        }
        return diaryInstance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDBAsyncTask(diaryInstance).execute();
        }
    };

    private static class PopulateDBAsyncTask extends AsyncTask<Void,Void,Void> {
        private DiaryDao diaryDao;
        private PopulateDBAsyncTask(DiaryDatabase db) {
            diaryDao = db.diaryDao();
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

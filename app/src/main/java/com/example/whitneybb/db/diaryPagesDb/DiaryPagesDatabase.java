package com.example.whitneybb.db.diaryPagesDb;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.whitneybb.db.diaryDb.DiaryDao;
import com.example.whitneybb.model.DiaryModel;
import com.example.whitneybb.model.DiaryPageModel;


@Database(entities = {DiaryPageModel.class}, version = 1)
public abstract class DiaryPagesDatabase extends RoomDatabase {
    private static DiaryPagesDatabase diaryPagesInstance;
    public abstract DiaryPagesDao diaryPagesDao();
    public static synchronized DiaryPagesDatabase getInstance(Context context) {
        //synch for only one instance
        if (diaryPagesInstance == null) {
            diaryPagesInstance = Room.databaseBuilder(context.getApplicationContext(), DiaryPagesDatabase.class,"diary_pages_database").fallbackToDestructiveMigration().addCallback(roomCallback).build();
        }
        return diaryPagesInstance;
    }

    private static Callback roomCallback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDBAsyncTask(diaryPagesInstance).execute();
        }
    };

    private static class PopulateDBAsyncTask extends AsyncTask<Void,Void,Void> {
        private DiaryPagesDao diaryPagesDao;
        private PopulateDBAsyncTask(DiaryPagesDatabase db) {
            diaryPagesDao = db.diaryPagesDao();
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

package com.example.whitneybb.db.notesDb;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.whitneybb.model.NotesModel;


@Database(entities = {NotesModel.class}, version = 1)
public abstract class NotesDatabase extends RoomDatabase {
    private static NotesDatabase notesInstance;
    private static Callback roomCallback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDBAsyncTask(notesInstance).execute();
        }
    };

    public static synchronized NotesDatabase getInstance(Context context) {
        //synch for only one instance
        if (notesInstance == null) {
            notesInstance = Room.databaseBuilder(context.getApplicationContext(), NotesDatabase.class, "note_database").fallbackToDestructiveMigration().addCallback(roomCallback).build();
        }
        return notesInstance;
    }

    public abstract NotesDao notesDao();

    private static class PopulateDBAsyncTask extends AsyncTask<Void, Void, Void> {
        private NotesDao notesDao;

        private PopulateDBAsyncTask(NotesDatabase db) {
            this.notesDao = db.notesDao();
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

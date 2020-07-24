package com.example.whitneybb.db.notesDb;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;


import com.example.whitneybb.model.NotesModel;

import java.util.List;

public class NotesRepository {

    private NotesDao notesDao;
    private LiveData<List<NotesModel>> allNotes;

    public NotesRepository(Application application) {
        NotesDatabase database = NotesDatabase.getInstance(application);
        notesDao = database.notesDao();
        allNotes = notesDao.getAllNotes();
    }

    public void insert(NotesModel note) {
        new InsertNotesAsyncTask(notesDao).execute(note);
    }

    public void update(NotesModel note) {
        new UpdateNotesAsyncTask(notesDao).execute(note);
    }

    public void delete(NotesModel note) {
        new DeleteNotesAsyncTask(notesDao).execute(note);
    }

    public void deleteAllNotes() {
        new DeleteAllNotesAsyncTask(notesDao).execute();
    }

    public LiveData<List<NotesModel>> getAllNotes() {
        return allNotes;
    }

    private static class InsertNotesAsyncTask extends AsyncTask<NotesModel, Void, Void> {
        private NotesDao notesDao;

        private InsertNotesAsyncTask(NotesDao notesDao) {
            this.notesDao = notesDao;
        }

        @Override
        protected Void doInBackground(NotesModel... notesModels) {
            try {
                notesDao.insert(notesModels[0]);
                System.out.println("New Note");
            } catch (Exception e) {
                notesDao.update(notesModels[0]);
                System.out.println("Update Note");
            }
            return null;
        }

    }

    private static class UpdateNotesAsyncTask extends AsyncTask<NotesModel, Void, Void> {
        private NotesDao notesDao;

        private UpdateNotesAsyncTask(NotesDao notesDao) {
            this.notesDao = notesDao;
        }

        @Override
        protected Void doInBackground(NotesModel... notesModels) {
            try {
                notesDao.update(notesModels[0]);
                System.out.println("Update Note");
            } catch (Exception e) {
                notesDao.insert(notesModels[0]);
                System.out.println("Insert Note");
            }
            return null;
        }

    }

    private static class DeleteNotesAsyncTask extends AsyncTask<NotesModel, Void, Void> {
        private NotesDao notesDao;

        private DeleteNotesAsyncTask(NotesDao notesDao) {
            this.notesDao = notesDao;
        }

        @Override
        protected Void doInBackground(NotesModel... notesModels) {
            try {
                notesDao.delete(notesModels[0]);
                System.out.println("Note Deleted");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

    }

    private static class DeleteAllNotesAsyncTask extends AsyncTask<NotesModel, Void, Void> {

        private NotesDao notesDao;

        private DeleteAllNotesAsyncTask(NotesDao notesDao) {
            this.notesDao = notesDao;
        }

        @Override
        protected Void doInBackground(NotesModel... notesModels) {
            notesDao.deleteAllNotes();
            return null;
        }
    }
}

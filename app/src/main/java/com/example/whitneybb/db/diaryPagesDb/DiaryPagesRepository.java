package com.example.whitneybb.db.diaryPagesDb;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.whitneybb.db.diaryDb.DiaryDao;
import com.example.whitneybb.db.diaryDb.DiaryDatabase;
import com.example.whitneybb.model.DiaryModel;
import com.example.whitneybb.model.DiaryPageModel;

import java.util.List;

public class DiaryPagesRepository {

    private DiaryPagesDao diaryPagesDao;
    private LiveData<List<DiaryPageModel>> allDiaryPages;

    public DiaryPagesRepository(Application application) {
        DiaryPagesDatabase database = DiaryPagesDatabase.getInstance(application);
        diaryPagesDao = database.diaryPagesDao();
        allDiaryPages = diaryPagesDao.getAllDiaryPages();

    }

    public void insert(DiaryPageModel page) {
        new InsertDiaryPageAsyncTask(diaryPagesDao).execute(page);
    }

    public void update(DiaryPageModel page) {
        new UpdateDiaryPageAsyncTask(diaryPagesDao).execute(page);
    }

    public void delete(DiaryPageModel page) {
        new DeleteDiaryPageAsyncTask(diaryPagesDao).execute(page);
    }

    public void deleteAllDiaryPages() {
        new DeleteAllDiaryPagesAsyncTask(diaryPagesDao).execute();
    }

    public LiveData<List<DiaryPageModel>> getAllDiaryPages() {
        return allDiaryPages;
    }

    private static class InsertDiaryPageAsyncTask extends AsyncTask<DiaryPageModel, Void, Void> {

        private DiaryPagesDao diaryPagesDao;
        private InsertDiaryPageAsyncTask(DiaryPagesDao diaryPagesDao) {
            this.diaryPagesDao = diaryPagesDao;
        }

        @Override
        protected Void doInBackground(DiaryPageModel... diaryPageModel) {
            try {
                diaryPagesDao.insert(diaryPageModel[0]);
                System.out.println("New diary page");
            } catch (Exception e) {
                diaryPagesDao.update(diaryPageModel[0]);
                System.out.println("Update diary page");
            }
            return null;
        }

    }

    private static class UpdateDiaryPageAsyncTask extends AsyncTask<DiaryPageModel, Void, Void> {

        private DiaryPagesDao diaryPagesDao;
        private UpdateDiaryPageAsyncTask(DiaryPagesDao diaryPagesDao) {
            this.diaryPagesDao = diaryPagesDao;
        }

        @Override
        protected Void doInBackground(DiaryPageModel... diaryPageModel) {
            try {
                diaryPagesDao.update(diaryPageModel[0]);
                System.out.println("Update diary page");
            } catch (Exception e) {
                diaryPagesDao.insert(diaryPageModel[0]);
                System.out.println("New diary page");
            }
            return null;
        }

    }

    private static class DeleteDiaryPageAsyncTask extends AsyncTask<DiaryPageModel, Void, Void> {

        private DiaryPagesDao diaryPagesDao;
        private DeleteDiaryPageAsyncTask(DiaryPagesDao diaryPagesDao) {
            this.diaryPagesDao = diaryPagesDao;
        }

        @Override
        protected Void doInBackground(DiaryPageModel... diaryPageModel) {
            try {
                diaryPagesDao.delete(diaryPageModel[0]);
                System.out.println("Deleted diary page");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

    }

    private static class DeleteAllDiaryPagesAsyncTask extends AsyncTask<DiaryPageModel, Void, Void> {

        private DiaryPagesDao diaryPagesDao;

        private DeleteAllDiaryPagesAsyncTask(DiaryPagesDao diaryPagesDao) {
            this.diaryPagesDao = diaryPagesDao;
        }

        @Override
        protected Void doInBackground(DiaryPageModel... diaryPageModel) {
            diaryPagesDao.deleteAllDiaryPages();
            return null;
        }
    }

}

package com.example.whitneybb.db.diaryDb;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;


import com.example.whitneybb.model.DiaryModel;

import java.util.List;

public class DiaryRepository {

    private DiaryDao diaryDao;
    private LiveData<List<DiaryModel>> allDiaries;

    public DiaryRepository(Application application) {
        DiaryDatabase database = DiaryDatabase.getInstance(application);
        diaryDao = database.diaryDao();
        allDiaries = diaryDao.getAllDiaries();
    }

    public void insert(DiaryModel diary) {
        new InsertDiaryAsyncTask(diaryDao).execute(diary);
    }

    public void update(DiaryModel diary) {
        new UpdateDiaryAsyncTask(diaryDao).execute(diary);
    }

    public void delete(DiaryModel diary) {
        new DeleteDiaryAsyncTask(diaryDao).execute(diary);
    }

    public void deleteAllDiaries() {
        new DeleteAllDiaryAsyncTask(diaryDao).execute();
    }

    public LiveData<List<DiaryModel>> getAllDiaries() {
        return allDiaries;
    }

    private static class InsertDiaryAsyncTask extends AsyncTask<DiaryModel, Void, Void> {

        private DiaryDao diaryDao;

        private InsertDiaryAsyncTask(DiaryDao diaryDao) {
            this.diaryDao = diaryDao;
        }

        @Override
        protected Void doInBackground(DiaryModel... diaryModels) {
            try {
                diaryDao.insert(diaryModels[0]);
                System.out.println("New diary");
            } catch (Exception e) {
                diaryDao.update(diaryModels[0]);
                System.out.println("Update diary");
            }
            return null;
        }

    }

    private static class UpdateDiaryAsyncTask extends AsyncTask<DiaryModel, Void, Void> {

        private DiaryDao diaryDao;

        private UpdateDiaryAsyncTask(DiaryDao diaryDao) {
            this.diaryDao = diaryDao;
        }

        @Override
        protected Void doInBackground(DiaryModel... diaryModels) {
            try {
                try {
                    diaryDao.update(diaryModels[0]);
                } catch (Exception e) {
                    diaryDao.insert(diaryModels[0]);
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Insert and Update Failed on Diary " + diaryModels[0].getDiaryId());
            }

            return null;
        }
    }

    private static class DeleteDiaryAsyncTask extends AsyncTask<DiaryModel, Void, Void> {

        private DiaryDao diaryDao;

        private DeleteDiaryAsyncTask(DiaryDao diaryDao) {
            this.diaryDao = diaryDao;
        }

        @Override
        protected Void doInBackground(DiaryModel... diaryModels) {
            diaryDao.delete(diaryModels[0]);
            return null;
        }
    }

    private static class DeleteAllDiaryAsyncTask extends AsyncTask<DiaryModel, Void, Void> {

        private DiaryDao diaryDao;

        private DeleteAllDiaryAsyncTask(DiaryDao diaryDao) {
            this.diaryDao = diaryDao;
        }

        @Override
        protected Void doInBackground(DiaryModel... diaryModels) {
            diaryDao.deleteAllDiaries();
            return null;
        }
    }
}

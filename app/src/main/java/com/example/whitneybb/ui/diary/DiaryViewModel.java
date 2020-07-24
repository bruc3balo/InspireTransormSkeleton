package com.example.whitneybb.ui.diary;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import com.example.whitneybb.db.diaryDb.DiaryRepository;
import com.example.whitneybb.model.DiaryModel;

import java.util.List;

public class DiaryViewModel extends AndroidViewModel {

    private DiaryRepository repository;
    private LiveData<List<DiaryModel>> allDiaries;

    public DiaryViewModel(@NonNull Application application) {
        super(application);
        repository = new DiaryRepository(application);
        allDiaries = repository.getAllDiaries();
    }

    public void insert(DiaryModel diary) {
        repository.insert(diary);
    }

    public void delete(DiaryModel diary) {
        repository.delete(diary);
    }

    public void deleteAllDiaries() {
        repository.deleteAllDiaries();
    }

    public void update(DiaryModel diary) {
        repository.update(diary);
    }

    public LiveData<List<DiaryModel>> getAllDiaries() {
        return allDiaries;
    }
}
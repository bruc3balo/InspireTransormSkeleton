package com.example.whitneybb.ui.diary.pages;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.whitneybb.db.diaryDb.DiaryRepository;
import com.example.whitneybb.db.diaryPagesDb.DiaryPagesRepository;
import com.example.whitneybb.model.DiaryModel;
import com.example.whitneybb.model.DiaryPageModel;

import java.util.List;
import java.util.Objects;

public class DiaryPagesViewModel extends AndroidViewModel {

    private DiaryPagesRepository repository;
     private LiveData<List<DiaryPageModel>> allDiaryPages;
    private MutableLiveData<DiaryPageModel> diaryPages = new MutableLiveData<>();

    public DiaryPagesViewModel (@NonNull Application application) {
        super(application);
        repository = new DiaryPagesRepository(application);
        allDiaryPages = repository.getAllDiaryPages();

    }

    public void insert(DiaryPageModel page) {
        repository.insert(page);
    }

    public void update(DiaryPageModel page) {
        repository.update(page);
    }

    public void delete(DiaryPageModel page) {
        repository.delete(page);
    }

    public void deleteAllDiaryPages() {
        repository.deleteAllDiaryPages();
    }

    public LiveData<List<DiaryPageModel>> getAllDiaryPages() {
        return allDiaryPages;
    }

    public LiveData<DiaryPageModel> getDiaryPagesWithId(String id) {
        try{
            System.out.println("size is "+ Objects.requireNonNull(allDiaryPages.getValue()).size());

            for (int i = 0;i <= Objects.requireNonNull(allDiaryPages.getValue()).size();i++) {
                if (allDiaryPages.getValue().get(i).getDiaryId().equals(id)) {
                    diaryPages.setValue(allDiaryPages.getValue().get(i));
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return diaryPages;
    }
}
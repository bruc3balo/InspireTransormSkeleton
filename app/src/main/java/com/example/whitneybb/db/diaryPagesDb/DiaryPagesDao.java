package com.example.whitneybb.db.diaryPagesDb;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.whitneybb.model.DiaryModel;
import com.example.whitneybb.model.DiaryPageModel;

import java.util.List;

@Dao
public interface DiaryPagesDao {
    @Insert
    void insert(DiaryPageModel page);
    @Update
    void update(DiaryPageModel page);
    @Delete
    void delete(DiaryPageModel page);
    @Query("DELETE FROM diary_pages_table")
    void deleteAllDiaryPages();
    @Query("SELECT * FROM diary_pages_table ORDER BY createdAt ASC")
    LiveData<List<DiaryPageModel>> getAllDiaryPages();

}

package com.example.whitneybb.db.diaryDb;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import com.example.whitneybb.model.DiaryModel;

import java.util.List;

@Dao
public interface DiaryDao {
    @Insert
    void insert(DiaryModel diary);
    @Update
    void update(DiaryModel diary);
    @Delete
    void delete(DiaryModel diary);
    @Query("DELETE FROM diary_table")
    void deleteAllDiaries();
    @Query("SELECT * FROM diary_table ORDER BY createdAt ASC")
    LiveData<List<DiaryModel>> getAllDiaries();
}

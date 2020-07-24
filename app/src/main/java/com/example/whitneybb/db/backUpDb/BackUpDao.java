package com.example.whitneybb.db.backUpDb;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import com.example.whitneybb.model.BackUpModel;

import java.util.List;

@Dao
public interface BackUpDao {
    @Insert
    void insert(BackUpModel backUp);

    @Update
    void update(BackUpModel backUp);

    @Delete
    void delete(BackUpModel backUp);

    @Query("DELETE FROM back_up_table")
    void deleteAllBackUps();

    @Query("SELECT * FROM back_up_table ORDER BY backUpId DESC")
    LiveData<List<BackUpModel>> getAllBackUps();
}

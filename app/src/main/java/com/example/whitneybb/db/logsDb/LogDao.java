package com.example.whitneybb.db.logsDb;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import com.example.whitneybb.model.LogModel;

import java.util.List;

@Dao
public interface LogDao {
    @Insert
    void insert(LogModel log);

    @Update
    void update(LogModel log);

    @Delete
    void delete(LogModel log);

    @Query("DELETE FROM log_table")
    void deleteAllLogs();

    @Query("SELECT * FROM log_table ")
        //ORDER BY diaryId DESC
    LiveData<List<LogModel>> getAllLogs();
}

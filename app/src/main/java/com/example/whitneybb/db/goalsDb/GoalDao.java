package com.example.whitneybb.db.goalsDb;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import com.example.whitneybb.model.GoalsModel;

import java.util.List;

@Dao
public interface GoalDao {
    @Insert
    void insert(GoalsModel goal);

    @Update
    void update(GoalsModel goal);

    @Delete
    void delete(GoalsModel goal);

    @Query("DELETE FROM goal_table")
    void deleteAllGoals();

    @Query("SELECT * FROM goal_table ")
        //ORDER BY diaryId DESC
    LiveData<List<GoalsModel>> getAllGoals();
}

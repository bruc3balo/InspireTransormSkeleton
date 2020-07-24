package com.example.whitneybb.db.objectivesDb;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import com.example.whitneybb.model.ObjectiveModel;

import java.util.List;

@Dao
public interface ObjectivesDao {
    @Insert
    void insert(ObjectiveModel objective);

    @Update
    void update(ObjectiveModel objective);

    @Delete
    void delete(ObjectiveModel objective);

    @Query("DELETE FROM objective_table")
    void deleteAllObjectives();

    @Query("SELECT * FROM objective_table ")
        //ORDER BY diaryId DESC
    LiveData<List<ObjectiveModel>> getAllObjectives();
}

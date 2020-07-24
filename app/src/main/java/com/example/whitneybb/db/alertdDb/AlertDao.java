package com.example.whitneybb.db.alertdDb;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import com.example.whitneybb.model.AlertsModel;

import java.util.List;

@Dao
public interface AlertDao {
    @Insert
    void insert(AlertsModel alert);

    @Update
    void update(AlertsModel alert);

    @Delete
    void delete(AlertsModel alert);

    @Query("DELETE FROM alerts_table")
    void deleteAllAlerts();

    @Query("SELECT * FROM alerts_table ORDER BY alertId DESC")
    LiveData<List<AlertsModel>> getAllAlerts();
}

package com.example.whitneybb.db.settingsDb;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import com.example.whitneybb.model.SettingsModel;

import java.util.List;

@Dao
public interface SettingsDao {
    @Insert
    void insert(SettingsModel settings);

    @Update
    void update(SettingsModel settings);

    @Delete
    void delete(SettingsModel settings);

    @Query("DELETE FROM settings_table")
    void deleteAllSettings();

    @Query("SELECT * FROM settings_table ORDER BY settingId DESC")
    LiveData<List<SettingsModel>> getAllSettings();
}

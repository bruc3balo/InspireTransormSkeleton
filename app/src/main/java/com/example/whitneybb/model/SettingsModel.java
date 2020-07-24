package com.example.whitneybb.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "settings_table")
public class SettingsModel {
    @PrimaryKey(autoGenerate = false)
    @NotNull
    private int settingId;


    public SettingsModel(int settingId) {
        this.settingId = settingId;
    }

    public int getSettingId() {
        return settingId;
    }

    public void setSettingId(int settingId) {
        this.settingId = settingId;
    }
}

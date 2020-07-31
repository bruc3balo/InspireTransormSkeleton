package com.example.whitneybb.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "back_up_table")
public class BackUpModel {
    @PrimaryKey
    @NotNull
    private int backUpId;
    public static final String BACK_UP_ID = "backUpId";

    public BackUpModel(int backUpId) {
        this.backUpId = backUpId;
    }

    public int getBackUpId() {
        return backUpId;
    }

    public void setBackUpId(int backUpId) {
        this.backUpId = backUpId;
    }
}

package com.example.whitneybb.model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

//todo create database
@Entity(tableName = "diary_pages_table")
public class DiaryPageModel {

    private int diaryId;
    @PrimaryKey
    @NotNull
    private String entryId;
    private String entryBody;
    private String createdAt;
    private String updatedAt;

    public DiaryPageModel() {

    }

    public DiaryPageModel(int diaryId) {
        this.diaryId = diaryId;
    }

    public DiaryPageModel(int diaryId, @NotNull String entryId, String entryBody, String createdAt, String updatedAt) {
        this.diaryId = diaryId;
        this.entryId = entryId;
        this.entryBody = entryBody;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getDiaryId() {
        return diaryId;
    }

    public void setDiaryId(int diaryId) {
        this.diaryId = diaryId;
    }

    @NotNull
    public String getEntryId() {
        return entryId;
    }

    public void setEntryId(@NotNull String entryId) {
        this.entryId = entryId;
    }

    public String getEntryBody() {
        return entryBody;
    }

    public void setEntryBody(String entryBody) {
        this.entryBody = entryBody;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
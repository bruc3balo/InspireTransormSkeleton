package com.example.whitneybb.model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;


@Entity(tableName = "diary_pages_table")
public class DiaryPageModel {

    private String diaryId;
    @PrimaryKey
    @NotNull
    private String entryId;
    public static final String ENTRY_ID = "entryId";
    private String entryTitle;
    public static final String ENTRY_TITLE = "entryTitle";
    private String entryBody;
    public static final String ENTRY_BODY = "entryBody";
    private String createdAt;
    private String updatedAt;

    public DiaryPageModel() {

    }

    public DiaryPageModel (String diaryId, @NotNull String entryId, String entryTitle, String entryBody, String createdAt, String updatedAt) {
        this.diaryId = diaryId;
        this.entryId = entryId;
        this.entryTitle = entryTitle;
        this.entryBody = entryBody;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getEntryTitle() {
        return entryTitle;
    }

    public void setEntryTitle(String entryTitle) {
        this.entryTitle = entryTitle;
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

    public String getDiaryId() {
        return diaryId;
    }

    public void setDiaryId(String diaryId) {
        this.diaryId = diaryId;
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
package com.example.whitneybb.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "diary_table")
public class DiaryModel {
    private String diaryTitle;
    public static final String DIARY_TITLE = "diaryTitle";
    @PrimaryKey
    @NonNull
    private String diaryId; //todo changeID toString
    public static final String DIARY_ID = "diaryId";
    private String diaryPassword;
    public static final String DIARY_PASSWORD = "diaryPassword";
    private String createdAt;
    public static final String CREATED_AT = "createdAt";
    private String updatedAt;
    public static final String UPDATED_AT = "updatedAt";
    private boolean passwordProtected;
    public static final String DIARY_PROTECTED_PASSWORD = "passwordProtected";
     //todo add entry protection;
    private boolean dailyScheduleEntry;
    public static final String DIARY_SCHEDULE = "dailyScheduleEntry";
    private String dairyReminderTime;
    public static final String DIARY_REMINDER_TIME = "dairyReminderTime";
    private String diaryOwner;
    public static final String DIARY_OWNER = "diaryOwner";
    private String diaryAbout;
    public static final String ABOUT_DIARY = "diaryAbout";
    private String diaryCoverUrl;
    public static final String DIARY_COVER = "diaryCoverUrl";


    /*
    * Keys To Successfully Writing a Diary
A diary is a personal journey and should not be compared to any other writings, but here are a few way you can get the most out of your experience.

Be honest
Your diary is for your eyes only, so be honest with yourself. Don’t hide anything or hold back.

Be frequent
The more often you write, the better.

Be natural
Don’t try to write a certain way, just be yourself.*/

    public DiaryModel() {
    }



    public DiaryModel(String diaryTitle, @NotNull String diaryId, String diaryPassword, String createdAt, String updatedAt, boolean passwordProtected, boolean dailyScheduleEntry, String dairyReminderTime, String diaryOwner, String diaryAbout, String diaryCoverUrl) {
        this.diaryTitle = diaryTitle;
        this.diaryId = diaryId;
        this.diaryPassword = diaryPassword;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.passwordProtected = passwordProtected;
        this.dailyScheduleEntry = dailyScheduleEntry;
        this.dairyReminderTime = dairyReminderTime;
        this.diaryOwner = diaryOwner;
        this.diaryAbout = diaryAbout;
        this.diaryCoverUrl = diaryCoverUrl;
    }

    public String getDiaryTitle() {
        return diaryTitle;
    }

    public void setDiaryTitle(String diaryTitle) {
        this.diaryTitle = diaryTitle;
    }

    public DiaryModel(@NonNull String diaryId) {
        this.diaryId = diaryId;
    }



    public String getDiaryAbout() {
        return diaryAbout;
    }

    public void setDiaryAbout(String diaryAbout) {
        this.diaryAbout = diaryAbout;
    }

    public String getDiaryCoverUrl() {
        return diaryCoverUrl;
    }



    public void setDiaryCoverUrl(String diaryCoverUrl) {
        this.diaryCoverUrl = diaryCoverUrl;
    }

    public String getDiaryPassword() {
        return diaryPassword;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }


    public boolean isPasswordProtected() {
        return passwordProtected;
    }


    public boolean isDailyScheduleEntry() {
        return dailyScheduleEntry;
    }

    public String getDiaryOwner() {
        return diaryOwner;
    }

    public String getDiaryId() {
        return diaryId;
    }

    public void setDiaryId(@NotNull String diaryId) {
        this.diaryId = diaryId;
    }

    public void setDiaryPassword(String diaryPassword) {
        this.diaryPassword = diaryPassword;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setPasswordProtected(boolean passwordProtected) {
        this.passwordProtected = passwordProtected;
    }

    public void setDailyScheduleEntry(boolean dailyScheduleEntry) {
        this.dailyScheduleEntry = dailyScheduleEntry;
    }

    public void setDiaryOwner(String diaryOwner) {
        this.diaryOwner = diaryOwner;
    }


    public String getDairyReminderTime() {
        return dairyReminderTime;
    }

    public void setDairyReminderTime(String dairyReminderTime) {
        this.dairyReminderTime = dairyReminderTime;
    }
}

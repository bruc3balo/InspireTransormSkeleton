package com.example.whitneybb.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity(tableName = "diary_table")
public class DiaryModel {
    private String entryHeading;
    @PrimaryKey(autoGenerate = false)
    @NonNull
    private int diaryId; //todo changeID toString
    private String diaryPassword;
    private String createdAt;
    @ColumnInfo(name = "Last Modified Date")
    private String updatedAt;
    private String entryBody;
    private boolean passwordProtected;
    private String password; // add entry protection;
    private boolean dailyScheduleEntry;
    private String dairyReminderTime;
    private String diaryOwner;
    private String diaryAbout;
    private String diaryCoverUrl;
    @Ignore
    private List<DiaryModel> diaryEntries;

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

    public DiaryModel(String entryHeading, @NonNull int diaryId, String diaryPassword, String createdAt, String updatedAt, String diaryAbout, String entryBody, boolean passwordProtected, String password, boolean dailyScheduleEntry, String diaryOwner, String dairyReminderTime) {
        this.entryHeading = entryHeading;
        this.diaryId = diaryId;
        this.diaryPassword = diaryPassword;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.entryBody = entryBody;
        this.passwordProtected = passwordProtected;
        this.password = password;
        this.dailyScheduleEntry = dailyScheduleEntry;
        this.diaryOwner = diaryOwner;
        this.diaryAbout = diaryAbout;
        this.dairyReminderTime = dairyReminderTime;
    }


    public DiaryModel(@NonNull int diaryId) {
        this.diaryId = diaryId;
    }

    public DiaryModel(String entryHeading, @NonNull int diaryId) {
        this.entryHeading = entryHeading;
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

    public String getEntryHeading() {
        return entryHeading;
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


    public String getEntryBody() {
        return entryBody;
    }

    public boolean isPasswordProtected() {
        return passwordProtected;
    }

    public String getPassword() {
        return password;
    }

    public boolean isDailyScheduleEntry() {
        return dailyScheduleEntry;
    }

    public String getDiaryOwner() {
        return diaryOwner;
    }



    public List<DiaryModel> getDiaryEntries() {
        return diaryEntries;
    }

    public void setDiaryEntries(List<DiaryModel> diaryEntries) {
        this.diaryEntries = diaryEntries;
    }

    public int getDiaryId() {
        return diaryId;
    }

    public void setDiaryId(int diaryId) {
        this.diaryId = diaryId;
    }

    public void setEntryHeading(String entryHeading) {
        this.entryHeading = entryHeading;
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


    public void setEntryBody(String entryBody) {
        this.entryBody = entryBody;
    }

    public void setPasswordProtected(boolean passwordProtected) {
        this.passwordProtected = passwordProtected;
    }

    public void setPassword(String password) {
        this.password = password;
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

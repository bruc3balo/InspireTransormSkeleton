package com.example.whitneybb.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "alerts_table")

public class AlertsModel {
    String alertRingTime;
    public static final String ALERT_RING_TIME = "alertRingTime";
    boolean alertRepeat;
    public static final String ALERT_REPEAT = "alertRepeat";
    String snoozeTime;
    public static final String SNOOZE_TIME = "snoozeTime";
    String alertTitle;
    public static final String ALERT_TITLE = "alertTitle";
    String alertDescription;
    public static final String ALERT_DESCRIPTION = "alertDescription";
    String stoppedAt;
    public static final String STOPPED_AT = "stoppedAt";
    String repeatDays;
    public static final String REPEAT_DAYS = "repeatDays";
    boolean alertOn;
    public static final String ALERT_ON = "alertOn";
    int snoozeCount;
    public static final String SNOOZE_COUNT = "snoozeCount";
    String createdAt;

    String updatedAt;
    @PrimaryKey
    @NonNull
    private String alertId;

    public static final String ALERT_ID = "alertId";

    public static final String SUNDAY = "Sunday", MONDAY = "Monday", TUESDAY = "Tuesday", WEDNESDAY = "Wednesday",THURSDAY ="Thursday",FRIDAY = "Friday",SATURDAY = "Saturday";
    public static final String REPEAT_ALARM = "REPEAT_ONCE",REPEAT_ON_DAY= "REPEAT_ON_DAY",ALL_DAY ="ALL_DAY",ONE_TIME_ALARM = "ONE_TIME_ALARM";

    public AlertsModel() {
    }

    public AlertsModel(String alertRingTime, boolean alertRepeat, String snoozeTime, String alertTitle, String alertDescription, String stoppedAt, String repeatDays, boolean alertOn, int snoozeCount, String createdAt, String updatedAt, @NonNull String alertId) {
        this.alertRingTime = alertRingTime;
        this.alertRepeat = alertRepeat;
        this.snoozeTime = snoozeTime;
        this.alertTitle = alertTitle;
        this.alertDescription = alertDescription;
        this.stoppedAt = stoppedAt;
        this.repeatDays = repeatDays;
        this.alertOn = alertOn;
        this.snoozeCount = snoozeCount;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.alertId = alertId;
    }

    public String getRepeatDays() {
        return repeatDays;
    }

    public void setRepeatDays(String repeatDays) {
        this.repeatDays = repeatDays;
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

    public String getAlertRingTime() {
        return alertRingTime;
    }

    public void setAlertRingTime(String alertRingTime) {
        this.alertRingTime = alertRingTime;
    }

    public boolean isAlertRepeat() {
        return alertRepeat;
    }

    public void setAlertRepeat(boolean alertRepeat) {
        this.alertRepeat = alertRepeat;
    }

    public String getSnoozeTime() {
        return snoozeTime;
    }

    public void setSnoozeTime(String snoozeTime) {
        this.snoozeTime = snoozeTime;
    }

    public String getAlertTitle() {
        return alertTitle;
    }

    public void setAlertTitle(String alertTitle) {
        this.alertTitle = alertTitle;
    }

    public String getAlertDescription() {
        return alertDescription;
    }

    public void setAlertDescription(String alertDescription) {
        this.alertDescription = alertDescription;
    }

    public String getStoppedAt() {
        return stoppedAt;
    }

    public void setStoppedAt(String stoppedAt) {
        this.stoppedAt = stoppedAt;
    }

    public boolean isAlertOn() {
        return alertOn;
    }

    public void setAlertOn(boolean alertOn) {
        this.alertOn = alertOn;
    }

    public int getSnoozeCount() {
        return snoozeCount;
    }

    public void setSnoozeCount(int snoozeCount) {
        this.snoozeCount = snoozeCount;
    }

    public String getAlertId() {
        return alertId;
    }

    public void setAlertId(String alertId) {
        this.alertId = alertId;
    }
}

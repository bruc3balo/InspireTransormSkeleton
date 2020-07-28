package com.example.whitneybb.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "alerts_table")

public class AlertsModel {
    String alertRingTime;
    boolean alertRepeat;
    String snoozeTime;
    String alertTitle;
    String alertDescription;
    String stoppedAt;
    boolean alertOn;
    int snoozeCount;
    @PrimaryKey(autoGenerate = false)
    @NonNull
    private int alertId; //todo change to int

    public static final String SUNDAY = "Sunday", MONDAY = "Monday", TUESDAY = "Tuesday", WEDNESDAY = "Wednesday",THURSDAY ="Thursday",FRIDAY = "Friday",SATURDAY = "Saturday";
    public static final String REPEAT_ALARM = "REPEAT_ONCE",REPEAT_ON_DAY= "REPEAT_ON_DAY",ALL_DAY ="ALL_DAY",ONE_TIME_ALARM = "ONE_TIME_ALARM";

    public AlertsModel() {
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

    public int getAlertId() {
        return alertId;
    }

    public void setAlertId(int alertId) {
        this.alertId = alertId;
    }
}

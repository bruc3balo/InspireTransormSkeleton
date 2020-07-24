package com.example.whitneybb.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "log_table")
public class LogModel {
    @PrimaryKey(autoGenerate = false)
    @NonNull
    private int logId;
    private String logCategory;
    private String logType;
    private String logData;
    private String loggersAccount;
    private String deviceInfo;

    public LogModel(int logId, String logCategory, String logType, String logData, String loggersAccount, String deviceInfo) {
        this.logId = logId;
        this.logCategory = logCategory;
        this.logType = logType;
        this.logData = logData;
        this.loggersAccount = loggersAccount;
        this.deviceInfo = deviceInfo;
    }

    public int getLogId() {
        return logId;
    }

    public void setLogId(int logId) {
        this.logId = logId;
    }

    public String getLogCategory() {
        return logCategory;
    }

    public void setLogCategory(String logCategory) {
        this.logCategory = logCategory;
    }

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    public String getLogData() {
        return logData;
    }

    public void setLogData(String logData) {
        this.logData = logData;
    }

    public String getLoggersAccount() {
        return loggersAccount;
    }

    public void setLoggersAccount(String loggersAccount) {
        this.loggersAccount = loggersAccount;
    }

    public String getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
    }
}

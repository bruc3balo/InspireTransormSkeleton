package com.example.whitneybb.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "log_table")
public class LogModel {
    @PrimaryKey
    @NonNull
    private String logId;
    public static final String LOG_ID = "logId";
    private String logCategory;
    public static final String LOG_CATEGORY = "logCategory";
    private String logType;
    public static final String LOG_TYPE = "logType";
    private String logData;
    public static final String LOG_DATA = "logData";
    private String loggersAccount;
    public static final String LOGGER_ACCOUNT = "loggersAccount";
    private String deviceInfo;
    public static final String DEVICE_INFO = "deviceInfo";


    public static final String OBJECTIVE_LOG = "OBJ", ALERT_LOG = "ALT", GOAL_LOG = "GOL" , DIARY_LOG = "DRY", NOTES_LOG = "NTS" , SETTINGS_LOG = "STN" , BACK_UP_LOG = "BCK", DIARY_PAGE_LOG = "PGE",UNKNOWN_LOG = "UNK";

    public LogModel(@NonNull String logId, String logCategory, String logType, String logData, String loggersAccount, String deviceInfo) {
        this.logId = logId;
        this.logCategory = logCategory;
        this.logType = logType;
        this.logData = logData;
        this.loggersAccount = loggersAccount;
        this.deviceInfo = deviceInfo;
    }

    @NonNull
    public String getLogId() {
        return logId;
    }

    public void setLogId(@NonNull String logId) {
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

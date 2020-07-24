package com.example.whitneybb.ui.alerts;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.whitneybb.db.alertdDb.AlertsRepository;
import com.example.whitneybb.db.objectivesDb.ObjectivesRepository;
import com.example.whitneybb.model.AlertsModel;
import com.example.whitneybb.model.ObjectiveModel;

import java.util.List;

public class AlertsViewModel extends AndroidViewModel {

    private AlertsRepository alertsRepository;
    private LiveData<List<AlertsModel>> allAlerts;

    public AlertsViewModel(@NonNull Application application) {
        super(application);
        alertsRepository = new AlertsRepository(application);
        allAlerts = alertsRepository.getAllAlerts();
    }

    public void insert(AlertsModel alertsModel) {
        alertsRepository.insert(alertsModel);
    }

    public void delete(AlertsModel alertsModel) {
        alertsRepository.delete(alertsModel);
    }

    public void update(AlertsModel alertsModel) {
        alertsRepository.update(alertsModel);
    }

    public void deleteAllAlerts() {
        alertsRepository.deleteAllAlerts();
    }

    public LiveData<List<AlertsModel>> getAllAlerts() {
        return allAlerts;
    }
}
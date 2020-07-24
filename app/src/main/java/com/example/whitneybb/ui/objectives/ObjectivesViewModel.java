package com.example.whitneybb.ui.objectives;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.whitneybb.db.objectivesDb.ObjectivesRepository;
import com.example.whitneybb.model.ObjectiveModel;

import java.util.List;

public class ObjectivesViewModel extends AndroidViewModel {

    private ObjectivesRepository objectivesRepository;
    private LiveData<List<ObjectiveModel>> allObjectives;

    public ObjectivesViewModel(@NonNull Application application) {
        super(application);
        objectivesRepository = new ObjectivesRepository(application);
        allObjectives = objectivesRepository.getAllObjectives();
    }

    public void insert(ObjectiveModel objective) {
        objectivesRepository.insert(objective);
    }

    public void delete(ObjectiveModel objective) {
        objectivesRepository.delete(objective);
    }

    public void update(ObjectiveModel objective) {
        objectivesRepository.update(objective);
    }

    public void deleteAllObjectives() {
        objectivesRepository.deleteAllObjectives();
    }

    public LiveData<List<ObjectiveModel>> getAllObjectives() {
        return allObjectives;
    }
}
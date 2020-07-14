package com.example.whitneybb.ui.objectives;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ObjectivesViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ObjectivesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
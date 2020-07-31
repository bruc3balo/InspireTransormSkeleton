package com.example.whitneybb.utils.randomDuties;

import androidx.lifecycle.ViewModelProvider;

import com.example.whitneybb.model.LogModel;
import com.example.whitneybb.ui.objectives.ObjectivesViewModel;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;
import java.util.Random;

public abstract class DoWork {

    public DoWork() {

    }

    public int giveNumber() {
        Random r = new Random();
        return r.nextInt(1000);
    }

    public String giveNewId (String title,String cat) {
        String uid = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();
        return title + "-" + uid + "-" + cat;
    }


}

package com.example.whitneybb.utils.randomDuties;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public interface IdGenerator {
    String getId(String s, String cat);

    String uid = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();

    String block = "-"+uid+"-";

}

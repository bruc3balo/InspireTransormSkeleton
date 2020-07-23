package com.example.whitneybb.login.fragments.create;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.whitneybb.model.UserModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.auth.User;

import java.util.Objects;

import static com.example.whitneybb.login.fragments.create.CreateAccountFragment.USERS_COLLECTION;
import static com.example.whitneybb.login.fragments.create.CreateAccountFragment.USER_DATA;

public class CreateAccountViewModel extends ViewModel {
    private FirebaseAuth mAuth;
    private MutableLiveData<UserModel> userList;

    public CreateAccountViewModel() {
        mAuth= FirebaseAuth.getInstance();
        userList = new MutableLiveData<>();
        getAllUsers();
    }

    private void getAllUsers () {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference userCollectionReference = db.collection(USERS_COLLECTION);
        userCollectionReference.document(USER_DATA).collection(Objects.requireNonNull(mAuth.getCurrentUser()).getUid()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() { //todo get all users
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {

                    for (QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult())) {
                            System.out.println(document.getId() + " => " + document.getData().toString());
                    }

                } else {

                }
            }
        });

    }

    public LiveData<UserModel> getUserList (){
        return userList;
    }
}
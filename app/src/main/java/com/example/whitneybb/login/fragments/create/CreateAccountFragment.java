package com.example.whitneybb.login.fragments.create;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.whitneybb.MainActivity;
import com.example.whitneybb.R;
import com.example.whitneybb.model.UserModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class CreateAccountFragment extends Fragment implements DatePicker.OnDateChangedListener {

    private CreateAccountViewModel mViewModel;
    private FirebaseAuth mAuth;
    private String dateOfBirth = "";
    public static final String USERS_COLLECTION = "Users Collection";
    public static final String USER_DATA = "User Data";


    public static CreateAccountFragment newInstance() {
        return new CreateAccountFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.create_account_fragment, container, false);
        final TextView showDatePickerTv = v.findViewById(R.id.showDatePickerTv);
        showDatePickerTv.setTextColor(Color.BLACK);
        final DatePicker creationDatePicker = v.findViewById(R.id.creationDatePicker);

        Button createB = v.findViewById(R.id.createButton);
        final EditText email, password;
        email = v.findViewById(R.id.emailCreationField);
        password = v.findViewById(R.id.passwordCreationField);

        createB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateForm(email, password, showDatePickerTv);
            }
        });

        mAuth = FirebaseAuth.getInstance();

        TextWatcher emailWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        email.addTextChangedListener(emailWatcher);

        TextWatcher passwordWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //todo check requirements
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        password.addTextChangedListener(passwordWatcher);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            creationDatePicker.setOnDateChangedListener(this);
        } else {
            Toast.makeText(requireContext(), "Minimum android version not met for date picker", Toast.LENGTH_SHORT).show();
        }
        creationDatePicker.setVisibility(View.GONE);
        showDatePickerTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleDatePicker(showDatePickerTv, creationDatePicker);
            }
        });
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        mViewModel = new ViewModelProvider(this).get(CreateAccountViewModel.class);
    }

    private void validateForm(EditText e1, EditText e2, final TextView t1) {
        if (e1.getText().toString().isEmpty()) {
            e1.setError("Email please");
            e1.requestFocus();
        } else if (e2.getText().toString().isEmpty()) {
            e2.setError("Password please");
            e2.requestFocus();
        } else if (dateOfBirth.equals("")) {
            Toast.makeText(requireContext(), "Pick a date of Birth", Toast.LENGTH_SHORT).show();
            t1.setTextColor(Color.RED);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    t1.setTextColor(Color.BLACK);
                }
            }, 300);
        } else {
            createFirebaseAccount(e1.getText().toString(), e2.getText().toString());
        }
    }

    private void toggleDatePicker(TextView textView, DatePicker datePicker) {
        if (datePicker.getVisibility() == View.VISIBLE) {
            textView.setTextColor(Color.BLACK);
            textView.setText("Click to change date");
            textView.setBackgroundColor(Color.TRANSPARENT);
            datePicker.setVisibility(View.GONE);
        } else {
            textView.setBackgroundColor(Color.LTGRAY);
            textView.setTextColor(Color.RED);
            textView.setText("Click to close date picker");
            datePicker.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        dateOfBirth = dayOfMonth + "/" + monthOfYear + "/" + year; // dd/mm/yyyy
        Toast.makeText(requireContext(), dateOfBirth, Toast.LENGTH_SHORT).show();
    }

    private void createFirebaseAccount(final String s1, String s2) {
        mAuth.createUserWithEmailAndPassword(s1, s2).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    try {
                        saveDetailsToDatabase(s1, Objects.requireNonNull(mAuth.getCurrentUser()).getUid());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println("Account creation succeeded");
                } else {
                    System.out.println("Account creation failed");
                }
            }
        });

    }

    private void saveDetailsToDatabase(String s1, String uid) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference userCollectionReference = db.collection(USERS_COLLECTION);
        userCollectionReference.document(USER_DATA).collection(Objects.requireNonNull(mAuth.getCurrentUser()).getUid()).add(new UserModel(s1, uid, dateOfBirth)).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                if (task.isSuccessful()) {
                    System.out.println("Details Saved");
                    finalizeAccountCreation();
                } else {
                    System.out.println("Details not saved");
                }
            }
        });
    }

    private void finalizeAccountCreation() {
        startActivity(new Intent(requireActivity(), MainActivity.class));
        requireActivity().finish();
    }

}
package com.example.whitneybb.login.fragments.login;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.whitneybb.MainActivity;
import com.example.whitneybb.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginFragment extends Fragment {

    private LoginViewModel mViewModel;
    private FirebaseAuth mAuth;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.login_fragment, container, false);

        mAuth = FirebaseAuth.getInstance();

        final EditText emailF = v.findViewById(R.id.emailLoginField);
        final EditText passwordF = v.findViewById(R.id.passwordLoginField);
        Button loginButton = v.findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateForm(emailF,passwordF);
            }
        });

        TextWatcher emailWatcher = new TextWatcher() {
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
        emailF.addTextChangedListener(emailWatcher);

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
        passwordF.addTextChangedListener(passwordWatcher);

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
    }


    private void validateForm(EditText e1,EditText e2){
        if (e1.getText().toString().isEmpty()) {
            e1.setError("Email required");
            e1.requestFocus();
        } else if (e2.getText().toString().isEmpty()) {
            e2.setError("Password required");
            e2.requestFocus();
        } else {
            signInUser(e1.getText().toString(),e2.getText().toString());
        }
    }


    private void signInUser(String s1, String s2) {
        mAuth.signInWithEmailAndPassword(s1,s2).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                   if (task.isSuccessful()) {
                        finalizeLogin();
                        System.out.println("Üser Logged in ");
                   } else {
                        System.out.println("Log in failed");
                   }
            }
        });
    }

    private void finalizeLogin () {
        startActivity(new Intent(requireActivity(), MainActivity.class));
        requireActivity().finish();
    }

}
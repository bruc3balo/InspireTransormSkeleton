package com.example.whitneybb.utils.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.whitneybb.R;
import com.example.whitneybb.login.LoginActivity;
import com.google.firebase.FirebaseApp;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        FirebaseApp.initializeApp(this);

        int SPLASH_TIME_OUT = 1000;

        new Handler().postDelayed(() -> endSplashScreen(), SPLASH_TIME_OUT);
    }

    private void endSplashScreen ( ) {
        startActivity(new Intent(this, LoginActivity.class));
    }


}
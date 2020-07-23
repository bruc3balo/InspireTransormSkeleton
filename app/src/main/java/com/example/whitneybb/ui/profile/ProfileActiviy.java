package com.example.whitneybb.ui.profile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.whitneybb.R;
import com.example.whitneybb.login.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;

public class ProfileActiviy extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_activiy);
    }

    public void updateUser(View view) {
      FirebaseAuth auth = FirebaseAuth.getInstance();
      auth.signOut();
      if (auth.getCurrentUser() == null) {
          startActivity(new Intent(this, LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
          finish();
      } else {
          Toast.makeText(this, ""+auth.getCurrentUser().getEmail(), Toast.LENGTH_SHORT).show();
      }
    }
}
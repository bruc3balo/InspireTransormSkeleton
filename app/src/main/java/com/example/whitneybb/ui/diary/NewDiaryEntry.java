package com.example.whitneybb.ui.diary;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.preference.SwitchPreferenceCompat;

import com.example.whitneybb.R;

public class NewDiaryEntry extends AppCompatActivity implements View.OnClickListener {

    public static final String EXTRA_ID = "com.example.offlinenotes.ui.diary.EXTRA_ID";
    public static final String EXTRA_TITLE = "com.example.offlinenotes.ui.diary.EXTRA_TITLE";

    private EditText idField, titleField,passwordFieldDiary,confirmPasswordFieldDiary;
    private Button createDiary;
    private SwitchCompat passwordSwitch;
    private boolean passwordOn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_diary_entry);

        Toolbar toolbar = findViewById(R.id.diaryToolbar);
        setSupportActionBar(toolbar);

        idField = findViewById(R.id.idField);
        titleField = findViewById(R.id.titleField);
        createDiary = findViewById(R.id.createDiary);

        createDiary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveDiary();
            }
        });

        passwordSwitch = findViewById(R.id.passwordSwitch);
        passwordSwitch.setOnClickListener(this);

        passwordFieldDiary = findViewById(R.id.passwordFieldDiary);
        passwordFieldDiary.setVisibility(View.GONE);
        confirmPasswordFieldDiary = findViewById(R.id.confirmPasswordFieldDiary);
        confirmPasswordFieldDiary.setVisibility(View.GONE);

        passwordOn = false;

        getWindow().setStatusBarColor(Color.BLACK);

    }

    private void saveDiary() {
        String title = titleField.getText().toString();
        int id = Integer.parseInt(idField.getText().toString());

        if (title.trim().isEmpty() || String.valueOf(id).isEmpty()) {

            Toast.makeText(this, "Insert fields in value", Toast.LENGTH_SHORT).show();

            if (title.isEmpty()) {
                titleField.setError("Empty");
                titleField.requestFocus();
            }

            if (String.valueOf(id).isEmpty()) {
                idField.setError("Empty");
                idField.requestFocus();
            }
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE, title);
        data.putExtra(EXTRA_ID, id);

        setResult(RESULT_OK, data);
        finish();

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        //todo exit conditions
        final Dialog exit_dialog = new Dialog(this);
        exit_dialog.setContentView(R.layout.draft_dialog);
        Button remindMe, yes, no;
        remindMe = exit_dialog.findViewById(R.id.remindMeExit);
        no = exit_dialog.findViewById(R.id.noExit);
        yes = exit_dialog.findViewById(R.id.yesExit);

        exit_dialog.show();

        remindMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(NewDiaryEntry.this, "Remind me", Toast.LENGTH_SHORT).show();
            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exit_dialog.cancel();
                finish();
            }
        });
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(NewDiaryEntry.this, "Saving", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        super.onBackPressed();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public int checkSelfPermission(String permission) {
        return super.checkSelfPermission(permission);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.passwordSwitch:
                passwordOn = !passwordOn;
                if (passwordOn) {
                    passwordFieldDiary.setVisibility(View.VISIBLE);
                    confirmPasswordFieldDiary.setVisibility(View.VISIBLE);
                } else {
                    passwordFieldDiary.setVisibility(View.GONE);
                    confirmPasswordFieldDiary.setVisibility(View.GONE);
                }
            default:break;
        }
    }
}
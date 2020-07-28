package com.example.whitneybb.ui.diary;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.preference.SwitchPreferenceCompat;

import com.example.whitneybb.R;
import com.example.whitneybb.model.DiaryModel;
import com.google.firebase.auth.FirebaseAuth;

import java.io.IOException;
import java.util.Calendar;
import java.util.Objects;

public class NewDiaryEntry extends AppCompatActivity implements View.OnClickListener {

    public static final String EXTRA_ID = "EXTRA_ID";
    public static final String EXTRA_TITLE = "EXTRA_TITLE";

    private EditText aboutField,titleField,passwordFieldDiary,confirmPasswordFieldDiary;
    private String imagePath = "";
    private boolean passwordOn,dailyReminder;
    private ImageView diaryCoverImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_diary_entry);

        getStoragePermission();

        Toolbar toolbar = findViewById(R.id.diaryToolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> {
            setResult(RESULT_CANCELED, null);
            finish();
        });
        
        titleField = findViewById(R.id.titleField);
        aboutField = findViewById(R.id.aboutField);
        diaryCoverImage = findViewById(R.id.diaryCoverImage);
        Button createDiary = findViewById(R.id.createDiary);

        createDiary.setOnClickListener(v -> validateForm());

        SwitchCompat passwordSwitch = findViewById(R.id.passwordSwitch);
        passwordSwitch.setOnClickListener(this);

        SwitchCompat reminderSwitch = findViewById(R.id.reminderSwitch);
        reminderSwitch.setOnClickListener(this);

        passwordFieldDiary = findViewById(R.id.passwordFieldDiary);
        passwordFieldDiary.setVisibility(View.GONE);
        confirmPasswordFieldDiary = findViewById(R.id.confirmPasswordFieldDiary);
        confirmPasswordFieldDiary.setVisibility(View.GONE);

        passwordOn = false;
        dailyReminder = false;

        getWindow().setStatusBarColor(Color.BLACK);

    }

    private void validateForm() {
        DiaryModel diary = new DiaryModel();
        diary.setDiaryOwner(FirebaseAuth.getInstance().getCurrentUser().getEmail());
        diary.setCreatedAt(Calendar.getInstance().getTime().toString());
        diary.setUpdatedAt(Calendar.getInstance().getTime().toString());

        if (titleField.getText().toString().isEmpty()) {
        titleField.setError("Fill title");
        titleField.requestFocus();
        } else if (aboutField.getText().toString().isEmpty()) {
        aboutField.setError("Fill about ");
        aboutField.requestFocus();
        } else if (passwordOn) {
            diary.setPasswordProtected(passwordOn);
            if (passwordFieldDiary.getText().toString().isEmpty()) {
                passwordFieldDiary.setError("Fill");
                passwordFieldDiary.requestFocus();
            } else if (!confirmPasswordFieldDiary.getText().toString().equals(passwordFieldDiary.getText().toString())){
                confirmPasswordFieldDiary.setError("Passwords don't match");
                confirmPasswordFieldDiary.requestFocus();
            } else {
                diary.setPassword(confirmPasswordFieldDiary.getText().toString());
                if (imagePath.equals("")) {
                    Toast.makeText(this, "No Image selected", Toast.LENGTH_SHORT).show();
                    diary.setDiaryCoverUrl("");
                } else {
                    diary.setDiaryCoverUrl(imagePath);
                }
                diary.setDailyScheduleEntry(dailyReminder);
                diary.setDiaryAbout(aboutField.getText().toString());
                diary.setEntryHeading(titleField.getText().toString());
                toastObject(diary);
            }
        } else {
            diary.setPasswordProtected(passwordOn);
            diary.setPassword("");
            if (imagePath.equals("")) {
                Toast.makeText(this, "No Image selected", Toast.LENGTH_SHORT).show();
                diary.setDiaryCoverUrl("");
            } else {
                diary.setDiaryCoverUrl(imagePath);
            }
            diary.setDailyScheduleEntry(dailyReminder);
            diary.setDiaryAbout(aboutField.getText().toString());
            diary.setEntryHeading(titleField.getText().toString());
            toastObject(diary);
        }
    }

    private void toastObject(DiaryModel diary) {
        Toast.makeText(this, "The Diary is called ,'" +diary.getEntryHeading()+ "' and the owner is "+diary.getDiaryOwner() +" and it's about "+diary.getDiaryAbout()+" and was created at "+diary.getCreatedAt()+ " also last modified at "+diary.getUpdatedAt() + ". The diary is private ? "+diary.isPasswordProtected() + " with password : "+diary.getDiaryPassword()+". You will be reminded ? "+diary.isDailyScheduleEntry() +" at "+diary.getDairyReminderTime() + " with image "+diary.getDiaryCoverUrl() , Toast.LENGTH_SHORT).show();
    }

    private void saveDiary(){
        String title = titleField.getText().toString();

        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE, title);

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

        remindMe.setOnClickListener(v -> Toast.makeText(NewDiaryEntry.this, "Remind me", Toast.LENGTH_SHORT).show());
        no.setOnClickListener(v -> {
            exit_dialog.cancel();
            finish();
        });
        yes.setOnClickListener(v -> {
            Toast.makeText(NewDiaryEntry.this, "Saving", Toast.LENGTH_SHORT).show();
            finish();
        });

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
        if (requestCode == 1) {

           if (resultCode == RESULT_OK) {
               try {
                   Bitmap bitmap = MediaStore.Images.Media.getBitmap(Objects.requireNonNull(this).getContentResolver(), Objects.requireNonNull(data).getData());
                   diaryCoverImage.setImageBitmap(bitmap);
                   TextView photoPath = findViewById(R.id.photoPath);
                   photoPath.setText(getPath(data.getData()));
                   imagePath = getPath(data.getData());
                   Toast.makeText(this, "height : "+bitmap.getHeight() +" width : "+bitmap.getWidth(), Toast.LENGTH_SHORT).show();
               } catch (IOException e) {
                   e.printStackTrace();
               }
           } else {
               Toast.makeText(this, "Failed to get photo", Toast.LENGTH_SHORT).show();
           }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private void getStoragePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
            return;

        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},2);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 2) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permission not granted", Toast.LENGTH_SHORT).show();
            }
        }

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

            case R.id.reminderSwitch:
                dailyReminder = !dailyReminder;
                if (dailyReminder) {
                    Toast.makeText(this, ""+dailyReminder, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, ""+dailyReminder, Toast.LENGTH_SHORT).show();
                }
                break;
            default:break;
        }
    }

    public void getDiaryPhoto(View view) {
        startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI),1);
    }

    private String getPath(Uri uri){
        //todo fix getting image intent

        Cursor cursor = Objects.requireNonNull(this).getContentResolver().query(uri,null,null,null,null);
        assert cursor != null;
        cursor.moveToFirst();
        String document_id = cursor.getString(0);

        document_id = document_id.substring(document_id.lastIndexOf(":") + 1);
        cursor.close();

        cursor = this.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,null,MediaStore.Images.Media._ID + "= ?",new String[]{document_id},null);

        assert cursor != null;
        cursor.moveToFirst();

        String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
        cursor.close();

        return path;
    }

}
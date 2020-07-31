package com.example.whitneybb.ui.notes;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import com.example.whitneybb.R;
import com.example.whitneybb.adapter.StandardRecyclerListAdapter;
import com.example.whitneybb.model.LogModel;
import com.example.whitneybb.model.NotesModel;
import com.example.whitneybb.utils.randomDuties.IdGenerator;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.Objects;

import static com.example.whitneybb.model.DiaryModel.CREATED_AT;
import static com.example.whitneybb.model.DiaryModel.UPDATED_AT;
import static com.example.whitneybb.model.NotesModel.NOTE_COLOR;
import static com.example.whitneybb.model.NotesModel.NOTE_CONTENT;
import static com.example.whitneybb.model.NotesModel.NOTE_HIGH_PRIORITY;
import static com.example.whitneybb.model.NotesModel.NOTE_ID;
import static com.example.whitneybb.model.NotesModel.NOTE_LOW_PRIORITY;
import static com.example.whitneybb.model.NotesModel.NOTE_MID_PRIORITY;
import static com.example.whitneybb.model.NotesModel.NOTE_OWNER;
import static com.example.whitneybb.model.NotesModel.NOTE_PASSWORD;
import static com.example.whitneybb.model.NotesModel.NOTE_PRIORITY;
import static com.example.whitneybb.model.NotesModel.NOTE_PRIVATE;
import static com.example.whitneybb.model.NotesModel.NOTE_TITLE;


public class NewNotesEntry extends AppCompatActivity implements IdGenerator, RadioGroup.OnCheckedChangeListener {
    private EditText noteBody, noteTitle, passwordF, cPasswordF;
    private String noteColor = "";
    private int notePriority = 0;
    private boolean reminderOn, privacyOn;
    private SwitchCompat noteReminderSwitch, passwordSwitchNote;
    private RadioGroup group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_notes_entry);

        noteBody = findViewById(R.id.noteBody);
        noteTitle = findViewById(R.id.noteTitle);

        passwordF = findViewById(R.id.passwordFieldNote);
        passwordF.setVisibility(View.GONE);
        cPasswordF = findViewById(R.id.confirmPasswordFieldNote);
        cPasswordF.setVisibility(View.GONE);

        noteReminderSwitch = findViewById(R.id.noteReminderSwitch);
        passwordSwitchNote = findViewById(R.id.passwordSwitchNote);

        group = findViewById(R.id.priorityGroup);
        group.setOnCheckedChangeListener(this);


        reminderOn = false;
        privacyOn = false;
        noteReminderSwitch.setOnClickListener(v -> {
            reminderOn = !reminderOn;
            if (reminderOn) {
                noteReminderSwitch.setChecked(true);
            } else {
                noteReminderSwitch.setChecked(false);
            }
        });
        passwordSwitchNote.setOnClickListener(v -> {
            privacyOn = !privacyOn;
            if (privacyOn) {
                passwordSwitchNote.setChecked(true);
                passwordF.setVisibility(View.VISIBLE);
                cPasswordF.setVisibility(View.VISIBLE);
            } else {
                passwordSwitchNote.setChecked(false);
                passwordF.setVisibility(View.GONE);
                cPasswordF.setVisibility(View.GONE);
            }
        });

        getWindow().setStatusBarColor(Color.BLACK);
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


    public void validateForm(View view) {
        if (noteTitle.getText().toString().isEmpty()) {
            noteTitle.setError("Insert a title");
            noteTitle.requestFocus();
        } else if (noteBody.getText().toString().isEmpty()) {
            noteBody.setError("Insert a body");
            noteBody.requestFocus();
        } else if (noteColor.equals("")) {
            Toast.makeText(this, "Pick a color", Toast.LENGTH_SHORT).show();
        } else if (privacyOn) {
            if (passwordF.getText().toString().isEmpty()) {
                passwordF.setError("Set password");
                passwordF.requestFocus();
            } else if (!cPasswordF.getText().toString().equals(passwordF.getText().toString())) {
                cPasswordF.setError("Miss match in password");
                cPasswordF.requestFocus();
            } else {
                NotesModel note = new NotesModel();

                String noteId = noteTitle.getText().toString().replaceAll(" ","") ;
                String time = Calendar.getInstance().getTime().toString();

                note.setNoteContent(noteBody.getText().toString());
                note.setNoteTitle(noteTitle.getText().toString());
                note.setNoteId(getId(noteId, LogModel.NOTES_LOG));

                note.setCreatedAt(time);
                note.setNoteColor(noteColor);
                note.setNoteOwner(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid());

                note.setUpdatedAt(time);
                note.setNotePriority(notePriority);
                note.setNotePrivate(true);
                note.setNotePassword(cPasswordF.getText().toString());

                    saveNote(note);
            }
        } else {
            NotesModel note = new NotesModel();

            String noteId = noteTitle.getText().toString().replaceAll(" ","") ;
            String time = Calendar.getInstance().getTime().toString();

            note.setNoteContent(noteBody.getText().toString());
            note.setNoteTitle(noteTitle.getText().toString());
            note.setNoteId(getId(noteId, LogModel.NOTES_LOG));

            note.setCreatedAt(time);
            note.setNoteColor(noteColor);
            note.setNoteOwner(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid());

            note.setUpdatedAt(time);
            note.setNotePriority(notePriority);
            note.setNotePrivate(false);
            note.setNotePassword(cPasswordF.getText().toString());

            saveNote(note);
        }
    }

    private void saveNote(NotesModel note) {
        Intent data = new Intent();

        data.putExtra(NOTE_CONTENT,note.getNoteContent());
        data.putExtra(NOTE_TITLE,note.getNoteTitle());

        data.putExtra(NOTE_ID,note.getNoteId());
        data.putExtra(CREATED_AT,note.getCreatedAt());
        data.putExtra(NOTE_COLOR,note.getNoteColor());

        data.putExtra(NOTE_OWNER,note.getNoteOwner());
        data.putExtra(UPDATED_AT,note.getUpdatedAt());
        data.putExtra(NOTE_PRIORITY,note.getNotePriority());

        data.putExtra(NOTE_PRIVATE,note.isNotePrivate());
        data.putExtra(NOTE_PASSWORD,note.getNotePassword());

        setResult(RESULT_OK, data);
        finish();
    }

    public void setWhiteNote(View view) {
        noteColor = "#ffffff";
    }

    public void setBlackNote(View view) {
        noteColor = "#000000";
    }

    public void setYellowNote(View view) {
        noteColor = "#FFFC33";
    }

    public void setGreenTile(View view) {
        noteColor = "#ff99cc00";
    }

    public void setRedNote(View view) {
        noteColor = "#ffcc0000";
    }

    public void setGrayTile(View view) {
        noteColor = "#aaa";
    }

    public void setBlueTile(View view) {
        noteColor = "#ff0099cc";
    }

    public void setPurpleTile(View view) {
        noteColor = "#ffaa66cc";
    }

    public void setOrangeTile(View view) {
        noteColor = "#ffff8800";
    }

    public void setYuckGreenTile(View view) {
        noteColor = "#ff669900";
    }

    public void setPinkTile(View view) {
        noteColor = "#EA6868";
    }

    @Override
    public String getId(String s, String cat) {
        return s+IdGenerator.block+cat;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.lowRadio:
                notePriority = NOTE_LOW_PRIORITY;

            case R.id.midRadio:
                notePriority = NOTE_MID_PRIORITY;
                break;

            case R.id.highRadio:
                notePriority = NOTE_HIGH_PRIORITY;
                break;
        }
    }
}
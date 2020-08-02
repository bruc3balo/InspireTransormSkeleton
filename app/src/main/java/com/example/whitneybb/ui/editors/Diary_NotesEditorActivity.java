package com.example.whitneybb.ui.editors;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.whitneybb.R;
import com.example.whitneybb.model.DiaryPageModel;
import com.example.whitneybb.model.NotesModel;
import com.example.whitneybb.model.ObjectiveModel;
import com.example.whitneybb.ui.diary.pages.DiaryPagesViewModel;
import com.example.whitneybb.ui.notes.NotesViewModel;
import com.example.whitneybb.ui.objectives.ObjectivesViewModel;
import com.example.whitneybb.utils.randomDuties.LinedEditText;

import java.util.Calendar;
import java.util.List;
import java.util.Objects;

import static com.example.whitneybb.login.LoginActivity.truncate;
import static com.example.whitneybb.model.DiaryModel.DIARY_ID;
import static com.example.whitneybb.model.DiaryPageModel.ENTRY_ID;
import static com.example.whitneybb.model.NotesModel.NOTE_ID;
import static com.example.whitneybb.model.ObjectiveModel.OBJECTIVE_ID;


public class Diary_NotesEditorActivity extends AppCompatActivity {

    public static final String EDITOR_SPECIFIC = "";
    public static final String D = "DIARY", N = "NOTES", O = "OBJECTIVE";
    private LinedEditText editorField;
    private EditText titleText;
    private String nId = "", eId = "", oId = "";

    private HorizontalScrollView buttonsTray;
    private ImageButton saveB, zoomOut, zoomIn, sizeUp, sizeDown, trayColor, textColorChange, buttonChange, fontChange, spellCheck, editorB,delete;
    private int textSize = 15, textColorCount = 0, backGroundColorCount = 0, zoomInt = 40, buttonColor = 0, editorBg = 0;
    String time = truncate(Calendar.getInstance().getTime().toString(), 16);
    private boolean backPressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        backPressed = false;

        titleText = findViewById(R.id.titleText);
        editorField = findViewById(R.id.editorTextField);
        editorField.setTextSize(textSize);

        //buttons
        saveB = findViewById(R.id.saveButton);
        zoomOut = findViewById(R.id.zoomOut);
        zoomIn = findViewById(R.id.zoomIn);

        sizeUp = findViewById(R.id.textSizeUo);
        sizeDown = findViewById(R.id.textSizeDown);
        trayColor = findViewById(R.id.changeLineBackground);

        textColorChange = findViewById(R.id.textColorChange);
        buttonChange = findViewById(R.id.changeButtonsColor);
        fontChange = findViewById(R.id.fontChange);

        spellCheck = findViewById(R.id.spellCheck);
        editorB = findViewById(R.id.changeEditorBg);
        delete = findViewById(R.id.delete_from_editor);



        //tray
        buttonsTray = findViewById(R.id.buttonsTray);
        Intent data = getIntent();

        switch (Objects.requireNonNull(Objects.requireNonNull(data.getExtras()).get(EDITOR_SPECIFIC)).toString()) {
            case D:

                eId = data.getStringExtra(ENTRY_ID);
                DiaryPagesViewModel diaryPagesViewModel = new ViewModelProvider(this).get(DiaryPagesViewModel.class);
                diaryPagesViewModel.getAllDiaryPages().observe(this, diaryPageModels -> {
                    for (int i = 0; i <= diaryPageModels.size() - 1; i++) {
                        if (diaryPageModels.get(i).getEntryId().equals(eId)) {
                            editorField.setText(diaryPageModels.get(i).getEntryBody());
                            titleText.setText(diaryPageModels.get(i).getEntryTitle());
                            int finalI = i;
                            saveB.setOnClickListener(v -> saveDiary(diaryPageModels.get(finalI), diaryPagesViewModel));
                            delete.setOnClickListener(v -> {diaryPagesViewModel.delete(diaryPageModels.get(finalI));finish();});
                        }
                    }
                });


                break;

            case N:

                nId = data.getStringExtra(NOTE_ID);
                Toast.makeText(this, nId, Toast.LENGTH_SHORT).show();
                NotesViewModel notesViewModel = new ViewModelProvider(this).get(NotesViewModel.class);
                notesViewModel.getAllNotes().observe(this, notesModels -> {
                    for (int i = 0; i <= notesModels.size() - 1; i++) {
                        if (notesModels.get(i).getNoteId().equals(nId)) {
                            editorField.setText(notesModels.get(i).getNoteContent());
                            titleText.setText(notesModels.get(i).getNoteTitle());
                            int finalI = i;
                            saveB.setOnClickListener(v -> saveNote(notesModels.get(finalI), notesViewModel));
                            delete.setOnClickListener(v -> {notesViewModel.update(notesModels.get(finalI));finish();});

                        }
                    }

                });

                break;

            case O:
                oId = data.getStringExtra(OBJECTIVE_ID);
                Toast.makeText(this, oId, Toast.LENGTH_SHORT).show();
                ObjectivesViewModel objectivesViewModel = new ViewModelProvider(this).get(ObjectivesViewModel.class);
                objectivesViewModel.getAllObjectives().observe(this, objectiveModels -> {
                    for (int i = 0; i <= objectiveModels.size() - 1; i++) {
                        if (objectiveModels.get(i).getObjectiveId().equals(oId)) {
                            editorField.setText(objectiveModels.get(i).getObj_notes());
                            titleText.setText(objectiveModels.get(i).getObjectiveTitle());
                            int finalI = i;
                            saveB.setOnClickListener(v -> saveObjectiveNote(objectiveModels.get(finalI), objectivesViewModel));
                            delete.setOnClickListener(v -> {objectiveModels.get(finalI).setObj_notes("");objectivesViewModel.update(objectiveModels.get(finalI));finish();});
                        }
                    }
                });
                break;
            default:

                break;
        }

        switch (EDITOR_SPECIFIC) {
            case D:

                break;

            case N:
                break;

            case O:
                break;
            default:
                break;
        }

        getWindow().setStatusBarColor(Color.BLACK);



    }


    //todo 8.FONT

    @Override
    public void onBackPressed() {
        if (!backPressed) {
            Toast.makeText(this, "Changes not saved. \n Are you sure you want to exit?", Toast.LENGTH_SHORT).show();
        } else {
            setResult(RESULT_CANCELED);
        }
        super.onBackPressed();
    }

    public void saveNote(NotesModel note, NotesViewModel vm) {
        note.setNoteContent(Objects.requireNonNull(editorField.getText()).toString());
        note.setNoteTitle(titleText.getText().toString());
        note.setUpdatedAt(time);
        vm.update(note);
        setResult(RESULT_OK);
        finish();
    }

    private void saveDiary(DiaryPageModel page, DiaryPagesViewModel vm) {
        page.setEntryTitle(titleText.getText().toString());
        page.setEntryBody(Objects.requireNonNull(editorField.getText()).toString());
        page.setUpdatedAt(time);
        vm.update(page);
        setResult(RESULT_OK);
        finish();
    }

    private void saveObjectiveNote(ObjectiveModel obj, ObjectivesViewModel vm) {
        obj.setObjectiveTitle(titleText.getText().toString());
        obj.setObj_notes(Objects.requireNonNull(editorField.getText()).toString());
        obj.setUpdatedAt(time);
        vm.update(obj);
        setResult(RESULT_OK);
        finish();
    }

    public void spellCheck(View view) {
    }

    public void changeTextColor(View view) {
        textColorCount++;
        if (textColorCount == NotesModel.getColorList().length) {
            textColorCount = 0;
        }
        editorField.setTextColor(ColorStateList.valueOf(Color.parseColor(NotesModel.getColorList()[textColorCount])));
        textColorChange.setImageTintList(ColorStateList.valueOf(Color.parseColor(NotesModel.getColorList()[textColorCount])));
    }

    public void changeFont(View view) {
    }

    public void setTextSizeDown(View view) {
        textSize--;
        editorField.setTextSize(textSize);
    }

    public void setTextSizeUp(View view) {
        textSize++;
        editorField.setTextSize(textSize);
    }

    public void zoomOutPage(View view) {
        zoomInt--;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            editorField.setLineHeight(zoomInt);
        }

    }

    public void zoomInPage(View view) {
        zoomInt++;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            editorField.setLineHeight(zoomInt);
        }
    }

    public void changeTrayColor(View view) {
        backGroundColorCount++;
        if (backGroundColorCount == NotesModel.getColorList().length) {
            backGroundColorCount = 0;
        }
        buttonsTray.setBackgroundColor(Color.parseColor(NotesModel.getColorList()[backGroundColorCount]));
    }

    public void changeButtonColor(View view) {
        buttonColor++;
        if (buttonColor == NotesModel.getColorList().length) {
            buttonColor = 0;
        }

        spellCheck.setBackgroundColor(Color.parseColor(NotesModel.getColorList()[buttonColor]));
        saveB.setBackgroundColor(Color.parseColor(NotesModel.getColorList()[buttonColor]));
        zoomOut.setBackgroundColor(Color.parseColor(NotesModel.getColorList()[buttonColor]));
        zoomIn.setBackgroundColor(Color.parseColor(NotesModel.getColorList()[buttonColor]));

        sizeUp.setBackgroundColor(Color.parseColor(NotesModel.getColorList()[buttonColor]));
        sizeDown.setBackgroundColor(Color.parseColor(NotesModel.getColorList()[buttonColor]));
        trayColor.setBackgroundColor(Color.parseColor(NotesModel.getColorList()[buttonColor]));

        textColorChange.setBackgroundColor(Color.parseColor(NotesModel.getColorList()[buttonColor]));
        buttonChange.setBackgroundColor(Color.parseColor(NotesModel.getColorList()[buttonColor]));
        fontChange.setBackgroundColor(Color.parseColor(NotesModel.getColorList()[buttonColor]));

        editorB.setBackgroundColor(Color.parseColor(NotesModel.getColorList()[buttonColor]));
        delete.setBackgroundColor(Color.parseColor(NotesModel.getColorList()[buttonColor]));

    }

    public void editorBackground(View view) {

        editorBg++;
        if (editorBg == NotesModel.getColorList().length) {
            editorBg = 0;
        }

        editorField.setBackgroundColor(Color.parseColor(NotesModel.getColorList()[editorBg]));
        editorB.setImageTintList(ColorStateList.valueOf(Color.parseColor(NotesModel.getColorList()[editorBg])));
    }
}
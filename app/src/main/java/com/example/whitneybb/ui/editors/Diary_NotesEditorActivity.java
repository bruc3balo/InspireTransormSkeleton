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
import com.example.whitneybb.ui.diary.pages.DiaryPagesViewModel;
import com.example.whitneybb.ui.notes.NotesViewModel;
import com.example.whitneybb.utils.randomDuties.LinedEditText;

import java.util.Calendar;
import java.util.List;
import java.util.Objects;

import static com.example.whitneybb.login.LoginActivity.truncate;
import static com.example.whitneybb.model.DiaryModel.DIARY_ID;
import static com.example.whitneybb.model.DiaryPageModel.ENTRY_ID;
import static com.example.whitneybb.model.NotesModel.NOTE_ID;


public class Diary_NotesEditorActivity extends AppCompatActivity {

    public static final String EDITOR_SPECIFIC = "";
    private LinedEditText editorField;
    private EditText titleText;
    private String nId = "", eId = "";
    private boolean diarySelected;
    private HorizontalScrollView buttonsTray;
    private ImageButton saveB,zoomOut,zoomIn,sizeUp,sizeDown,trayColor,textColorChange,buttonChange,fontChange,spellCheck,editorB;
    private int textSize = 15,textColorCount = 0,backGroundColorCount = 0,zoomInt = 40,buttonColor = 0,editorBg= 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);
        diarySelected = false;

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


        //tray
        buttonsTray = findViewById(R.id.buttonsTray);

        Intent data = getIntent();

        switch (Objects.requireNonNull(Objects.requireNonNull(data.getExtras()).get(EDITOR_SPECIFIC)).toString()) {
            case "DIARY":
                diarySelected = true;
                eId = data.getStringExtra(ENTRY_ID);
                DiaryPagesViewModel diaryPagesViewModel = new ViewModelProvider(this).get(DiaryPagesViewModel.class);
                diaryPagesViewModel.getAllDiaryPages().observe(this, diaryPageModels -> {
                    for (int i = 0; i <= diaryPageModels.size() - 1; i++) {
                        if (diaryPageModels.get(i).getEntryId().equals(eId)) {
                            editorField.setText(diaryPageModels.get(i).getEntryBody());
                            titleText.setText(diaryPageModels.get(i).getEntryTitle());
                            int finalI = i;
                            saveB.setOnClickListener(v -> saveDiary(diaryPageModels.get(finalI),diaryPagesViewModel));
                        }
                    }
                });



                break;

            case "NOTES":
                diarySelected = false;
                nId = data.getStringExtra(NOTE_ID);
                Toast.makeText(this, nId, Toast.LENGTH_SHORT).show();
                NotesViewModel notesViewModel = new ViewModelProvider(this).get(NotesViewModel.class);
                notesViewModel.getAllNotes().observe(this, notesModels -> {
                    for (int i = 0; i <= notesModels.size() - 1; i++) {
                        if (notesModels.get(i).getNoteId().equals(nId)) {
                            editorField.setText(notesModels.get(i).getNoteContent());
                            titleText.setText(notesModels.get(i).getNoteTitle());
                            int finalI = i;
                            saveB.setOnClickListener(v -> saveNote(notesModels.get(finalI),notesViewModel));

                        }
                    }

                });

                break;
            default:
                break;
        }

        if (diarySelected) {

        } else {

        }


        getWindow().setStatusBarColor(Color.BLACK);

    }


    //todo 1.SAVE  8.FONT

    @Override
    public void onBackPressed() {
        if (diarySelected) {

        } else {

        }
        super.onBackPressed();
    }

    public void saveNote(NotesModel note,NotesViewModel vm) {
        note.setNoteContent(Objects.requireNonNull(editorField.getText()).toString());
        note.setNoteTitle(titleText.getText().toString());
        note.setUpdatedAt(truncate(Calendar.getInstance().getTime().toString(),16));
        vm.update(note);
        finish();
    }

    private void saveDiary(DiaryPageModel page,DiaryPagesViewModel vm){
        page.setEntryTitle(titleText.getText().toString());
        page.setEntryBody(Objects.requireNonNull(editorField.getText()).toString());
        page.setUpdatedAt(truncate(Calendar.getInstance().getTime().toString(),16));
        vm.update(page);
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
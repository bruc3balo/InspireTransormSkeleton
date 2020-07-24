package com.example.whitneybb.ui.editors;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.whitneybb.R;


public class Diary_NotesEditorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        getWindow().setStatusBarColor(Color.BLACK);
    }
}
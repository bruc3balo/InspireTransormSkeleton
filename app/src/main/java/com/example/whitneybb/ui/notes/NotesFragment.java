package com.example.whitneybb.ui.notes;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.whitneybb.MainActivity;
import com.example.whitneybb.R;
import com.example.whitneybb.adapter.GridAdapter;
import com.example.whitneybb.model.NotesModel;
import com.example.whitneybb.ui.editors.Diary_NotesEditorActivity;
import com.google.android.material.snackbar.Snackbar;

import java.util.LinkedList;
import java.util.List;

import static com.example.whitneybb.MainActivity.smartFab;
import static com.example.whitneybb.model.NotesModel.NOTE_ID;
import static com.example.whitneybb.ui.editors.Diary_NotesEditorActivity.EDITOR_SPECIFIC;

public class NotesFragment extends Fragment {

    private NotesViewModel mViewModel;
    LinkedList<String> color_list = new LinkedList<>();
    LinkedList<String> titles = new LinkedList<>();
    private LinkedList<NotesModel> notesList = new LinkedList<>();

    private GridAdapter notesAdapter;

    public static NotesFragment newInstance() {
        return new NotesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.notes_fragment, container, false);

        MainActivity.currentPage = 0;
        smartFab(MainActivity.currentPage);

        GridView gridView = v.findViewById(R.id.notesGrid);
        notesAdapter = new GridAdapter(requireContext(), color_list, titles);
        gridView.setAdapter(notesAdapter);
        gridView.setOnItemClickListener((parent, view, position, id) -> {
            startActivity(new Intent(requireContext(), Diary_NotesEditorActivity.class).putExtra(NOTE_ID,notesList.get(position).getNoteId()).putExtra(EDITOR_SPECIFIC, "NOTES"));
        });

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(NotesViewModel.class);

        mViewModel.getAllNotes().observe(getViewLifecycleOwner(), notesModels -> {

            titles.clear();
            color_list.clear();
            notesList.clear();
            notesList.addAll(notesModels);
            for (int i = 0; i <= notesList.size() - 1; i++) {
                titles.add(notesList.get(i).getNoteTitle());
                notesAdapter.notifyDataSetChanged();
            }

            for (int i = 0; i <= notesList.size() - 1; i++) {
                color_list.add(notesList.get(i).getNoteColor());
                notesAdapter.notifyDataSetChanged();
            }

            notesAdapter.notifyDataSetChanged();

        });

    }


}
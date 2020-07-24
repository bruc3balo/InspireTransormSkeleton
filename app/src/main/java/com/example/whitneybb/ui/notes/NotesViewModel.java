package com.example.whitneybb.ui.notes;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.whitneybb.db.goalsDb.GoalRepository;
import com.example.whitneybb.db.notesDb.NotesRepository;
import com.example.whitneybb.model.GoalsModel;
import com.example.whitneybb.model.NotesModel;

import java.util.List;

public class NotesViewModel extends AndroidViewModel {

    private NotesRepository notesRepository;
    private LiveData<List<NotesModel>> allNotes;

    public NotesViewModel(@NonNull Application application) {
        super(application);
        notesRepository = new NotesRepository(application);
        allNotes = notesRepository.getAllNotes();
    }

    public void insert(NotesModel note) {
        notesRepository.insert(note);
    }

    public void delete(NotesModel note) {
        notesRepository.delete(note);
    }

    public void update(NotesModel note) {
        notesRepository.update(note);
    }

    public void deleteAllNotes() {
        notesRepository.deleteAllNotes();
    }

    public LiveData<List<NotesModel>> getAllNotes() {
        return allNotes;
    }
}
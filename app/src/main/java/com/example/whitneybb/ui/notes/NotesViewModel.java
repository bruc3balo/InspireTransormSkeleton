package com.example.whitneybb.ui.notes;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.whitneybb.db.goalsDb.GoalRepository;
import com.example.whitneybb.db.notesDb.NotesRepository;
import com.example.whitneybb.model.DiaryPageModel;
import com.example.whitneybb.model.GoalsModel;
import com.example.whitneybb.model.NotesModel;

import java.util.List;
import java.util.Objects;

public class NotesViewModel extends AndroidViewModel {

    private NotesRepository notesRepository;
    private LiveData<List<NotesModel>> allNotes;
    private MutableLiveData<NotesModel> noteMutable;

    public NotesViewModel(@NonNull Application application) {
        super(application);
        notesRepository = new NotesRepository(application);
        allNotes = notesRepository.getAllNotes();
        noteMutable = new MutableLiveData<>();
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

    public LiveData<NotesModel> getNoteWithId(String id) {
        try {
            //  System.out.println("size is "+ Objects.requireNonNull(allNotes.getValue()).size());
            for (int i = 0;i <= Objects.requireNonNull(allNotes.getValue()).size() - 1;i++) {
                if (allNotes.getValue().get(i).getNoteId().equals(id)) {
                    noteMutable.setValue(allNotes.getValue().get(i));
                }
            }

        } catch (Exception e){
            e.printStackTrace();
        }

        return noteMutable;
    }
}
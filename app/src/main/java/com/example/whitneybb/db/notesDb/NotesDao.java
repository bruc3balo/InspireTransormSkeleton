package com.example.whitneybb.db.notesDb;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import com.example.whitneybb.model.NotesModel;

import java.util.List;

@Dao
public interface NotesDao {
    @Insert
    void insert(NotesModel note);

    @Update
    void update(NotesModel note);

    @Delete
    void delete(NotesModel note);

    @Query("DELETE FROM notes_table")
    void deleteAllNotes();

    @Query("SELECT * FROM notes_table ")
        //ORDER BY diaryId DESC
    LiveData<List<NotesModel>> getAllNotes();
}

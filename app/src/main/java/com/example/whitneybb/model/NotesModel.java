package com.example.whitneybb.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "notes_table")
public class NotesModel {

    @PrimaryKey(autoGenerate = false)
    @NotNull
    private int noteId; //todo change to string
    private String noteTitle;
    private String noteContent;
    private String createdAt;
    private String updatedAt;
    private String noteOwner;
    private String noteColor;
    private int notePriority;
    private boolean notePrivate;

    public NotesModel() {
    }

    public int getNoteId() {
        return noteId;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public String getNoteContent() {
        return noteContent;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public String getNoteOwner() {
        return noteOwner;
    }

    public String getNoteColor() {
        return noteColor;
    }

    public int getNotePriority() {
        return notePriority;
    }

    public boolean isNotePrivate() {
        return notePrivate;
    }

    public void setNoteColor(String noteColor) {
        this.noteColor = noteColor;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public void setNoteContent(String noteContent) {
        this.noteContent = noteContent;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setNoteOwner(String noteOwner) {
        this.noteOwner = noteOwner;
    }

    public void setNotePriority(int notePriority) {
        this.notePriority = notePriority;
    }

    public void setNotePrivate(boolean notePrivate) {
        this.notePrivate = notePrivate;
    }
}

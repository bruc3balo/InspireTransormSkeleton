package com.example.whitneybb.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "notes_table")
public class NotesModel {

    @PrimaryKey
    @NotNull
    private String noteId;
    public static final String NOTE_ID = "noteId";
    private String noteTitle;
    public static final String NOTE_TITLE = "noteTitle";
    private String noteContent;
    public static final String NOTE_CONTENT = "noteContent";
    private String createdAt;
    private String updatedAt;
    private String noteOwner;
    public static final String NOTE_OWNER = "noteOwner";
    private String noteColor;
    public static final String NOTE_COLOR = "noteColor";
    private int notePriority;
    public static final String NOTE_PRIORITY = "notePriority";
    public static final int NOTE_HIGH_PRIORITY = 2;
    public static final int NOTE_LOW_PRIORITY = 0;
    public static final int NOTE_MID_PRIORITY = 1;
    private boolean notePrivate;
    public static final String NOTE_PRIVATE = "notePrivate";
    private String notePassword;
    public static final String NOTE_PASSWORD = "notePassword";

    public NotesModel() {
    }

    public NotesModel(@NotNull String noteId, String noteTitle, String noteContent, String createdAt, String updatedAt, String noteOwner, String noteColor, int notePriority, boolean notePrivate, String notePassword) {
        this.noteId = noteId;
        this.noteTitle = noteTitle;
        this.noteContent = noteContent;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.noteOwner = noteOwner;
        this.noteColor = noteColor;
        this.notePriority = notePriority;
        this.notePrivate = notePrivate;
        this.notePassword = notePassword;
    }

    public String getNotePassword() {
        return notePassword;
    }

    public void setNotePassword(String notePassword) {
        this.notePassword = notePassword;
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

    @NotNull
    public String getNoteId() {
        return noteId;
    }

    public void setNoteId(@NotNull String noteId) {
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

package com.mateuszjanczak.notepad.notes.service;

import com.mateuszjanczak.notepad.notes.dto.NoteDto;
import com.mateuszjanczak.notepad.notes.model.Note;

import java.util.ArrayList;

public interface NotesService {

    ArrayList<Note> findAll();
    Note findById(String id);
    Note save(NoteDto noteDto);
    Note edit(NoteDto noteDto, String id);
    void delete(String id);

}

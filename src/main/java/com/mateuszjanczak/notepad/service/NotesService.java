package com.mateuszjanczak.notepad.service;

import com.mateuszjanczak.notepad.domain.dto.NoteDto;
import com.mateuszjanczak.notepad.domain.model.Note;

import java.util.ArrayList;

public interface NotesService {

    Note add(NoteDto noteDto);
    ArrayList<Note> getAll();
    Note get(int id);
    Note edit(NoteDto noteDto, int id);
    void remove(int id);
}

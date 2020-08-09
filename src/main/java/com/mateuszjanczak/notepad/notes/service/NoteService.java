package com.mateuszjanczak.notepad.notes.service;

import com.mateuszjanczak.notepad.notes.dto.NoteRequest;
import com.mateuszjanczak.notepad.notes.entity.Note;

import java.util.List;

public interface NoteService {

    List<Note> findAll();
    Note findById(String id);
    Note save(NoteRequest noteRequest);
    Note edit(NoteRequest noteRequest, String id);
    void delete(String id);

}

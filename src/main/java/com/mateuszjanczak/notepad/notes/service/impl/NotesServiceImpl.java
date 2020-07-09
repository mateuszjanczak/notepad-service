package com.mateuszjanczak.notepad.notes.service.impl;

import com.mateuszjanczak.notepad.notes.exceptions.NoteNotFoundException;
import com.mateuszjanczak.notepad.notes.dto.NoteDto;
import com.mateuszjanczak.notepad.notes.model.Note;
import com.mateuszjanczak.notepad.notes.service.NotesService;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.stream.IntStream;

@Service
public class NotesServiceImpl implements NotesService {

    ArrayList<Note> notes;

    public NotesServiceImpl() {
        this.notes = new ArrayList<>();
        IntStream.range(1,11)
                .forEach(this::generate);
    }

    @Override
    public ArrayList<Note> findAll() {
        return notes;
    }

    @Override
    public Note findById(String id){
        return notes.stream()
                .filter(note -> note.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoteNotFoundException(id));
    }

    @Override
    public Note save(NoteDto noteDto) {
        Note note = new Note(noteDto);
        notes.add(note);
        return note;
    }

    @Override
    public Note edit(NoteDto noteDto, String id){
        return notes.stream()
                .filter(note -> note.getId().equals(id))
                .peek(note -> {
                    note.setTitle(noteDto.getTitle());
                    note.setContent(noteDto.getContent());
                })
                .findFirst()
                .orElseThrow(() -> new NoteNotFoundException(id));
    }

    @Override
    public void delete(String id) {
        notes.remove (
                notes.stream()
                .filter(note -> note.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoteNotFoundException(id))
        );
    }

    private void generate(int id){
        NoteDto noteDto = new NoteDto();
        noteDto.setTitle("Heading " + id);
        noteDto.setContent("Lorem ipsum...");
        save(noteDto);
    }

}

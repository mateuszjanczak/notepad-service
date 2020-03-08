package com.mateuszjanczak.notepad.service.impl;


import com.mateuszjanczak.notepad.domain.dto.NoteDto;
import com.mateuszjanczak.notepad.domain.model.Note;
import com.mateuszjanczak.notepad.service.NotesService;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Random;

@Service
public class NotesServiceImpl implements NotesService {

    ArrayList<Note> notes;

    public NotesServiceImpl() {
        this.notes = new ArrayList<>();
        for(int i=0; i<10; i++){
            generate();
        }
    }

    private void generate(){
        NoteDto noteDto = new NoteDto();
        noteDto.setTitle("Heading");
        noteDto.setContent("Lorem ipsum...");
        add(noteDto);
    }

    @Override
    public Note add(NoteDto noteDto) {
        Random random = new Random();
        int id = random.nextInt(99999);
        Note note = new Note(noteDto, id);
        notes.add(note);
        return note;
    }

    @Override
    public ArrayList<Note> getAll() {
        return notes;
    }

    public Note get(int id){
        for (Note note : notes) {
            if (note.getId() == id) {
                return note;
            }
        }
        return null;
    }

    @Override
    public Note edit(NoteDto noteDto, int id){
        for (Note note : notes) {
            if (note.getId() == id) {
                note.setTitle(noteDto.getTitle());
                note.setContent(noteDto.getContent());
                return note;
            }
        }
        return null;
    }

    @Override
    public Integer remove(int id) {
        for (Note note : notes) {
            if (note.getId() == id) {
                notes.remove(note);
                return id;
            }
        }
        return null;
    }
}

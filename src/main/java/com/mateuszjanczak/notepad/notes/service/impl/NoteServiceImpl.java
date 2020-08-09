package com.mateuszjanczak.notepad.notes.service.impl;

import com.mateuszjanczak.notepad.users.exception.InsufficientPermissionsException;
import com.mateuszjanczak.notepad.notes.dto.NoteRequest;
import com.mateuszjanczak.notepad.notes.entity.Note;
import com.mateuszjanczak.notepad.notes.exception.NoteNotFoundException;
import com.mateuszjanczak.notepad.notes.repository.NoteRepository;
import com.mateuszjanczak.notepad.notes.service.NoteService;
import com.mateuszjanczak.notepad.users.entity.User;
import com.mateuszjanczak.notepad.users.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;
    private final AuthService authService;

    @Autowired
    public NoteServiceImpl(NoteRepository noteRepository, AuthService authService) {
        this.noteRepository = noteRepository;
        this.authService = authService;
    }

    @Override
    public List<Note> findAll() {
        return noteRepository.findAll();
    }

    @Override
    public Note findById(String id) {
        return noteRepository.findById(id).orElseThrow(() -> new NoteNotFoundException(id));
    }

    @Override
    public Note save(NoteRequest noteRequest) {
        User user = authService.getLoggedUser();
        Note note = new Note(noteRequest, user);
        return noteRepository.save(note);
    }

    @Override
    public Note edit(NoteRequest noteRequest, String id) {
        Note note = this.findById(id);
        User user = authService.getLoggedUser();
        if(isUnauthorized(note, user)) throw new InsufficientPermissionsException();
        note.setTitle(noteRequest.getTitle());
        note.setContent(noteRequest.getContent());
        return noteRepository.save(note);
    }

    @Override
    public void delete(String id) {
        Note note = this.findById(id);
        User user = authService.getLoggedUser();
        if(isUnauthorized(note, user)) throw new InsufficientPermissionsException();
        noteRepository.delete(note);
    }

    private boolean isUnauthorized(Note note, User user) {
        return !note.getUser().equals(user);
    }

    /*ArrayList<Note> notes;

    public NoteServiceImpl() {
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
    public Note save(NoteRequest noteRequest) {
        Note note = new Note(noteRequest);
        notes.add(note);
        return note;
    }

    @Override
    public Note edit(NoteRequest noteRequest, String id){
        return notes.stream()
                .filter(note -> note.getId().equals(id))
                .peek(note -> {
                    note.setTitle(noteRequest.getTitle());
                    note.setContent(noteRequest.getContent());
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
        NoteRequest noteRequest = new NoteRequest();
        noteRequest.setTitle("Heading " + id);
        noteRequest.setContent("Lorem ipsum...");
        save(noteRequest);
    }*/

}

package com.mateuszjanczak.notepad.notes.web.rest;

import com.mateuszjanczak.notepad.notes.dto.NoteRequest;
import com.mateuszjanczak.notepad.notes.dto.NoteResponse;
import com.mateuszjanczak.notepad.notes.entity.Note;
import com.mateuszjanczak.notepad.notes.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @CrossOrigin
    @GetMapping("/notes")
    public ResponseEntity<List<NoteResponse>> getAllNotes(){
        final List<NoteResponse> notes = noteService.findAll().stream().map(Note::toResponse).collect(Collectors.toList());
        return new ResponseEntity<>(notes, HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping(value = "/notes/{id}")
    public ResponseEntity<NoteResponse> getNoteById(@PathVariable String id){
        final NoteResponse note = noteService.findById(id).toResponse();
        return new ResponseEntity<>(note, HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping(value = "/notes")
    public ResponseEntity<NoteResponse> createNote(@RequestBody @Valid NoteRequest noteRequest){
        final NoteResponse note = noteService.save(noteRequest).toResponse();
        return new ResponseEntity<>(note, HttpStatus.OK);
    }

    @CrossOrigin
    @PatchMapping(value = "/notes/{id}")
    public ResponseEntity<NoteResponse> updateNote(@RequestBody @Valid NoteRequest noteRequest, @PathVariable String id){
        final NoteResponse note = noteService.edit(noteRequest, id).toResponse();
        return new ResponseEntity<>(note, HttpStatus.OK);
    }

    @CrossOrigin
    @DeleteMapping(value = "/notes/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable String id){
        noteService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

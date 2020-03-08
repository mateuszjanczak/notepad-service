package com.mateuszjanczak.notepad.rest;

import com.mateuszjanczak.notepad.domain.dto.NoteDto;
import com.mateuszjanczak.notepad.domain.model.Note;
import com.mateuszjanczak.notepad.service.NotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/notes")
public class NotesApiController {

    @Autowired
    private NotesService notesService;

    @CrossOrigin
    @GetMapping
    public ResponseEntity<ArrayList<Note>> getNotes(){
        final ArrayList<Note> notes = notesService.getAll();
        return new ResponseEntity<>(notes, HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping(value = "/{id}")
    public ResponseEntity<Note> getNote(@PathVariable int id){
        final Note note = notesService.get(id);
        return new ResponseEntity<>(note, HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping(value = "/")
    public ResponseEntity<Note> addNote(@RequestBody NoteDto noteDto){
        final Note note = notesService.add(noteDto);
        return new ResponseEntity<>(note, HttpStatus.OK);
    }

    @CrossOrigin
    @PatchMapping(value = "/{id}")
    public ResponseEntity<Note> editNote(@RequestBody NoteDto noteDto, @PathVariable int id){
        final Note note = notesService.edit(noteDto, id);
        return new ResponseEntity<>(note, HttpStatus.OK);
    }

    @CrossOrigin
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Integer> removeNote(@PathVariable int id){
        int removedId = notesService.remove(id);
        return new ResponseEntity<>(removedId, HttpStatus.OK);
    }
}

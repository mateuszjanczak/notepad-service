package com.mateuszjanczak.notepad.notes.controller;

import com.mateuszjanczak.notepad.notes.dto.NoteDto;
import com.mateuszjanczak.notepad.notes.model.Note;
import com.mateuszjanczak.notepad.notes.service.NotesService;
import com.mateuszjanczak.notepad.users.dao.UserDao;
import com.mateuszjanczak.notepad.users.model.Role;
import com.mateuszjanczak.notepad.users.model.RoleName;
import com.mateuszjanczak.notepad.users.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api")
public class NotesController {

    @Autowired
    private NotesService notesService;

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @CrossOrigin
    @GetMapping("/test")
    public ResponseEntity<User> test() {
        List<Role> userRole = Arrays.asList(new Role(RoleName.ROLE_USER), new Role(RoleName.ROLE_ADMIN));
        User mati = new User("mati", passwordEncoder.encode("mati"), "mati@localhost", true, userRole);
        this.userDao.save(mati);
        return new ResponseEntity<>(mati, HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/notes")
    public ResponseEntity<ArrayList<Note>> getAllNotes(){
        final ArrayList<Note> notes = notesService.findAll();
        return new ResponseEntity<>(notes, HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping(value = "/notes/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable String id){
        final Note note = notesService.findById(id);
        return new ResponseEntity<>(note, HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping(value = "/notes")
    public ResponseEntity<Note> createNote(@RequestBody @Valid NoteDto noteDto){
        final Note note = notesService.save(noteDto);
        return new ResponseEntity<>(note, HttpStatus.OK);
    }

    @CrossOrigin
    @PatchMapping(value = "/notes/{id}")
    public ResponseEntity<Note> updateNote(@RequestBody @Valid NoteDto noteDto, @PathVariable String id){
        final Note note = notesService.edit(noteDto, id);
        return new ResponseEntity<>(note, HttpStatus.OK);
    }

    @CrossOrigin
    @DeleteMapping(value = "/notes/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable String id){
        notesService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

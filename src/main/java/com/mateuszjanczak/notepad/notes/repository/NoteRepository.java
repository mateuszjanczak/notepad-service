package com.mateuszjanczak.notepad.notes.repository;

import com.mateuszjanczak.notepad.notes.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, String> {
}

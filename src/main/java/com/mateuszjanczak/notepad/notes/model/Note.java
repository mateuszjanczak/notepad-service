package com.mateuszjanczak.notepad.notes.model;

import com.mateuszjanczak.notepad.notes.dto.NoteDto;
import java.util.UUID;

public class Note {

    private String id;
    private String title;
    private String content;

    public Note(NoteDto noteDto) {
        String id = UUID.randomUUID().toString();
        this.setId(id);
        this.setTitle(noteDto.getTitle());
        this.setContent(noteDto.getContent());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}

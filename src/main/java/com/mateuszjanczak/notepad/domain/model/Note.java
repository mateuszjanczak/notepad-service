package com.mateuszjanczak.notepad.domain.model;

import com.mateuszjanczak.notepad.domain.dto.NoteDto;

public class Note {
    private int id;
    private String title;
    private String content;

    public Note(NoteDto noteDto, int id) {
        setId(id);
        setTitle(noteDto.getTitle());
        setContent(noteDto.getContent());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

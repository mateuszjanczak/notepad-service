package com.mateuszjanczak.notepad.notes.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class NoteRequest implements Serializable {

    @NotNull(message = "Title cannot be null")
    private String title;

    @NotNull(message = "Content cannot be null")
    private String content;

    public NoteRequest() {
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

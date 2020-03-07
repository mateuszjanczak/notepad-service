package com.mateuszjanczak.notepad.domain.dto;
import java.io.Serializable;

public class NoteDto implements Serializable {
    private int id;
    private String title;
    private String content;

    public NoteDto() {
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

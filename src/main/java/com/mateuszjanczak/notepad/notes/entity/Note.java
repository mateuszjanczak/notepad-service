package com.mateuszjanczak.notepad.notes.entity;

import com.mateuszjanczak.notepad.notes.dto.NoteRequest;
import com.mateuszjanczak.notepad.notes.dto.NoteResponse;
import com.mateuszjanczak.notepad.users.entity.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private String id;

    @NotNull
    @Column(name = "TITLE")
    private String title;

    @NotNull
    @Column(name = "CONTENT")
    private String content;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    public Note() {
    }

    public Note(NoteRequest noteRequest, User user) {
        this.setTitle(noteRequest.getTitle());
        this.setContent(noteRequest.getContent());
        this.setUser(user);
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public NoteResponse toResponse() {
        return new NoteResponse(id, title, content);
    }
}

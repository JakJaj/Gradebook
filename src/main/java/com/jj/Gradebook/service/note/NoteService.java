package com.jj.Gradebook.service.note;

import com.jj.Gradebook.entity.Note;

import java.util.List;

public interface NoteService {
    List<Note> findAll();
    Note findById(Long id);
    Note save(Note note);
    void deleteById(Long id);
}

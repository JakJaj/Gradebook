package com.jj.Gradebook.controller;

import com.jj.Gradebook.entity.Note;
import com.jj.Gradebook.service.note.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class NoteController {

    private NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/notes")
    public List<Note> findAll(){
        return noteService.findAll();
    }

    @GetMapping("/notes/{id}")
    public Note findById(@PathVariable Long id){
        return noteService.findById(id);
    }

    @PostMapping("/notes")
    public Note save(@RequestBody Note note){
        return noteService.save(note);
    }

    @DeleteMapping("/notes/{id}")
    public void deleteById(@PathVariable Long id){
        noteService.deleteById(id);
    }
}

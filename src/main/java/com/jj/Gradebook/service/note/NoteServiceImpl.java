package com.jj.Gradebook.service.note;

import com.jj.Gradebook.dao.NoteRepository;
import com.jj.Gradebook.entity.Note;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class NoteServiceImpl implements NoteService{

    private NoteRepository noteRepository;

    @Override
    public List<Note> findAll() {
        return noteRepository.findAll();
    }

    @Override
    public Note findById(Long id) {
        Optional<Note> result = noteRepository.findById(id);

        Note note;
        if(result.isPresent()){
            note = result.get();
        }
        else { //TODO: FIND BETTER APPROACH
            throw new RuntimeException("No note with id - " + id);
        }
        return note;
    }

    @Override
    @Transactional
    public Note save(Note note) {
        return noteRepository.save(note);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        noteRepository.deleteById(id);
    }
}

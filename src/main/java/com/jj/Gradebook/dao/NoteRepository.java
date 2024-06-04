package com.jj.Gradebook.dao;

import com.jj.Gradebook.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note,Integer> {
}

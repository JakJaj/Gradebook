package com.jj.Gradebook.dao;

import com.jj.Gradebook.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findNotesByStudent_StudentId(Long studentID);
    void deleteAllByStudent_StudentClass_ClassId(Long classID);

    void deleteNotesByStudent_StudentId(Long studentID);
    void deleteAllByCourse_Teacher_TeacherId(Long teacherID);

    List<Note> findNotesByStudent_StudentClass_ClassId(Long classID);
}

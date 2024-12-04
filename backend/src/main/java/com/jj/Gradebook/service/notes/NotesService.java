package com.jj.Gradebook.service.notes;

import com.jj.Gradebook.controller.response.notes.StudentNotesResponse;
import com.jj.Gradebook.dao.NoteRepository;
import com.jj.Gradebook.dao.StudentRepository;
import com.jj.Gradebook.dto.NoteDTO;
import com.jj.Gradebook.dto.TimetableEntryDTO;
import com.jj.Gradebook.entity.Note;
import com.jj.Gradebook.entity.Student;
import com.jj.Gradebook.exceptions.NoSuchEntityException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotesService {

    private final StudentRepository studentRepository;
    private final NoteRepository noteRepository;

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    private final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
    public StudentNotesResponse getAllStudentsNotes(Long studentID) {

        Student student = studentRepository.findById(studentID).orElseThrow(() -> new NoSuchEntityException(String.format("No student with id - %d", studentID)));

        List<Note> notes = noteRepository.findNotesByStudent_StudentId(student.getStudentId());

        List<NoteDTO> noteDTOList = notes.stream()
                .map(note -> NoteDTO.builder()
                        .noteID(note.getNoteId())
                        .description(note.getDescription())
                        .date(dateFormat.format(note.getDateTime()))
                        .studentID(note.getStudent().getStudentId())
                        .timetableEntry(TimetableEntryDTO.builder()
                                .timetableID(note.getTimetable().getTimetableId())
                                .courseName(note.getTimetable().getCourse().getCourseName())
                                .classID(student.getStudentClass().getClassId())
                                .startTime(note.getTimetable().getStartTime().toString())
                                .endTime(note.getTimetable().getEndTime().toString())
                                .classroom(note.getTimetable().getClassroomNumber())
                                .teacherName(note.getTimetable().getCourse().getTeacher().getFirstName() + " " + note.getTimetable().getCourse().getTeacher().getLastName())
                                .build())
                        .build())
                .toList();

        return StudentNotesResponse.builder()
                .status("Success")
                .message("Successfully returning ")
                .notes(noteDTOList)
                .build();
    }
}

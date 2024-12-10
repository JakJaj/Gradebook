package com.jj.Gradebook.service.notes;

import com.jj.Gradebook.controller.request.notes.CreateNoteRequest;
import com.jj.Gradebook.controller.request.notes.UpdateNoteDetailsRequest;
import com.jj.Gradebook.controller.response.notes.NoteResponse;
import com.jj.Gradebook.controller.response.notes.StudentNotesResponse;
import com.jj.Gradebook.dao.NoteRepository;
import com.jj.Gradebook.dao.StudentRepository;
import com.jj.Gradebook.dao.TimetableRepository;
import com.jj.Gradebook.dto.NoteDTO;
import com.jj.Gradebook.dto.TimetableEntryDTO;
import com.jj.Gradebook.entity.Note;
import com.jj.Gradebook.entity.Student;
import com.jj.Gradebook.entity.Timetable;
import com.jj.Gradebook.exceptions.DateFormatException;
import com.jj.Gradebook.exceptions.NoSuchEntityException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotesService {

    private final StudentRepository studentRepository;
    private final NoteRepository noteRepository;
    private final TimetableRepository timetableRepository;

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

    public StudentNotesResponse createNewNote(Long studentID, CreateNoteRequest request) {
        Student student = studentRepository.findById(studentID).orElseThrow(() -> new NoSuchEntityException(String.format("No user with id - %d", studentID)));
        Timetable timetable = timetableRepository.findById(request.getTimetableID()).orElseThrow(() -> new NoSuchEntityException(String.format("No timetable with id - %d", request.getTimetableID())));

        try {
            Note note = noteRepository.save(Note.builder()
                    .student(student)
                    .timetable(timetable)
                    .description(request.getDescription())
                    .dateTime(dateFormat.parse(request.getDate()))
                    .build());

            return StudentNotesResponse.builder()
                    .status("Success")
                    .message("Successfully returning created note")
                    .notes(List.of(NoteDTO.builder()
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
                            .build()))
                    .build();
        }catch (ParseException ex){
            throw new DateFormatException("Wrong date format");
        }
    }

    public NoteResponse updateNoteDetails(Long studentID, UpdateNoteDetailsRequest request) {
        Student student = studentRepository.findById(studentID).orElseThrow(() -> new NoSuchEntityException(String.format("No student with id - %d", studentID)));
        Note note = noteRepository.findById(request.getNoteID()).orElseThrow(() -> new NoSuchEntityException(String.format("No note with id - %d",request.getNoteID())));

        if (!note.getNoteId().equals(request.getNoteID())) throw new NoSuchEntityException("Selected note isn't a note of picked student");

        try {
            Note savedNote = noteRepository.save(Note.builder()
                    .noteId(note.getNoteId())
                    .student(student)
                    .timetable(note.getTimetable())
                    .description(request.getDescription())
                    .dateTime(dateFormat.parse(request.getDate()))
                    .build());

            return NoteResponse.builder()
                    .status("Success")
                    .message(String.format("Successfully updated note with id - %d", savedNote.getNoteId()))
                    .note(NoteDTO.builder()
                            .noteID(savedNote.getNoteId())
                            .description(savedNote.getDescription())
                            .date(dateFormat.format(savedNote.getDateTime()))
                            .studentID(note.getStudent().getStudentId())
                            .timetableEntry(TimetableEntryDTO.builder()
                                    .timetableID(savedNote.getTimetable().getTimetableId())
                                    .courseName(savedNote.getTimetable().getCourse().getCourseName())
                                    .classID(student.getStudentClass().getClassId())
                                    .startTime(savedNote.getTimetable().getStartTime().toString())
                                    .endTime(savedNote.getTimetable().getEndTime().toString())
                                    .classroom(savedNote.getTimetable().getClassroomNumber())
                                    .teacherName(savedNote.getTimetable().getCourse().getTeacher().getFirstName() + " " + savedNote.getTimetable().getCourse().getTeacher().getLastName())
                                    .build())
                            .build())
                    .build();
        }catch (ParseException ex){
            throw new DateFormatException("Wrong date format");
        }
    }

    public StudentNotesResponse deleteNote(Long studentID, Long noteID) {
        Student student = studentRepository.findById(studentID).orElseThrow(() -> new NoSuchEntityException(String.format("No student with id - %d", studentID)));
        Note note = noteRepository.findById(noteID).orElseThrow(() -> new NoSuchEntityException(String.format("No note with id - %d", noteID)));
        if (!note.getStudent().getStudentId().equals(student.getStudentId())) throw new NoSuchEntityException("Selected note isn't a note of picked student");

        noteRepository.delete(note);

        return getAllStudentsNotes(studentID);
    }
}

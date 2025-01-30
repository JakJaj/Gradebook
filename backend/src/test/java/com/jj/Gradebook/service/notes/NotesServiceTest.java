package com.jj.Gradebook.service.notes;

import com.jj.Gradebook.controller.request.notes.CreateNoteRequest;
import com.jj.Gradebook.controller.request.notes.UpdateNoteDetailsRequest;
import com.jj.Gradebook.controller.response.notes.NoteResponse;
import com.jj.Gradebook.controller.response.notes.StudentNotesResponse;
import com.jj.Gradebook.dao.CoursesRepository;
import com.jj.Gradebook.dao.NoteRepository;
import com.jj.Gradebook.dao.StudentRepository;
import com.jj.Gradebook.entity.Course;
import com.jj.Gradebook.entity.Note;
import com.jj.Gradebook.entity.Student;
import com.jj.Gradebook.entity.Teacher;
import com.jj.Gradebook.exceptions.DateFormatException;
import com.jj.Gradebook.exceptions.NoSuchEntityException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class NotesServiceTest {

    @Mock
    private StudentRepository studentRepository;
    @Mock
    private NoteRepository noteRepository;
    @Mock
    private CoursesRepository coursesRepository;

    @InjectMocks
    private NotesService notesService;

    private Student student;
    private Course course;
    private Note note;
    private Teacher teacher;
    private SimpleDateFormat dateFormat;

    @BeforeEach
    void setUp() {
        dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        teacher = Teacher.builder()
                .teacherId(1L)
                .firstName("John")
                .lastName("Doe")
                .build();

        course = Course.builder()
                .courseId(1L)
                .courseName("Math")
                .teacher(teacher)
                .build();

        student = Student.builder()
                .studentId(1L)
                .firstName("Jane")
                .lastName("Smith")
                .build();

        note = Note.builder()
                .noteId(1L)
                .title("Test Note")
                .description("Test Description")
                .dateTime(new Date())
                .student(student)
                .course(course)
                .build();
    }

    @Test
    void getAllStudentsNotes_Success() {
        // Given
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        when(noteRepository.findNotesByStudent_StudentId(1L)).thenReturn(Arrays.asList(note));

        // When
        StudentNotesResponse response = notesService.getAllStudentsNotes(1L);

        // Then
        assertNotNull(response);
        assertEquals("Success", response.getStatus());
        assertEquals(1, response.getNotes().size());
        assertEquals(note.getTitle(), response.getNotes().get(0).getTitle());
        assertEquals(note.getDescription(), response.getNotes().get(0).getDescription());
    }

    @Test
    void getAllStudentsNotes_StudentNotFound() {
        // Given
        when(studentRepository.findById(1L)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(NoSuchEntityException.class, () -> notesService.getAllStudentsNotes(1L));
    }

    @Test
    void createNewNote_Success() {
        // Given
        CreateNoteRequest request = CreateNoteRequest.builder()
                .title("New Note")
                .description("New Description")
                .date("01-01-2024")
                .courseID(1L)
                .build();

        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        when(coursesRepository.findById(1L)).thenReturn(Optional.of(course));
        when(noteRepository.save(any(Note.class))).thenReturn(note);

        // When
        StudentNotesResponse response = notesService.createNewNote(1L, request);

        // Then
        assertNotNull(response);
        assertEquals("Success", response.getStatus());
        assertEquals(1, response.getNotes().size());
        verify(noteRepository).save(any(Note.class));
    }

    @Test
    void createNewNote_InvalidDateFormat() {
        // Given
        CreateNoteRequest request = CreateNoteRequest.builder()
                .title("New Note")
                .description("New Description")
                .date("invalid-date")
                .courseID(1L)
                .build();

        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        when(coursesRepository.findById(1L)).thenReturn(Optional.of(course));

        // When & Then
        assertThrows(DateFormatException.class, () -> notesService.createNewNote(1L, request));
    }

    @Test
    void updateNoteDetails_Success() {
        // Given
        UpdateNoteDetailsRequest request = UpdateNoteDetailsRequest.builder()
                .noteID(1L)
                .title("Updated Note")
                .description("Updated Description")
                .date("01-01-2024")
                .build();

        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        when(noteRepository.findById(1L)).thenReturn(Optional.of(note));
        when(noteRepository.save(any(Note.class))).thenReturn(note);

        // When
        NoteResponse response = notesService.updateNoteDetails(1L, request);

        // Then
        assertNotNull(response);
        assertEquals("Success", response.getStatus());
        verify(noteRepository).save(any(Note.class));
    }

    @Test
    void updateNoteDetails_NoteNotFound() {
        // Given
        UpdateNoteDetailsRequest request = UpdateNoteDetailsRequest.builder()
                .noteID(1L)
                .title("Updated Note")
                .description("Updated Description")
                .date("01-01-2024")
                .build();

        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        when(noteRepository.findById(1L)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(NoSuchEntityException.class, () -> notesService.updateNoteDetails(1L, request));
    }

    @Test
    void deleteNote_Success() {
        // Given
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        when(noteRepository.findById(1L)).thenReturn(Optional.of(note));
        when(noteRepository.findNotesByStudent_StudentId(1L)).thenReturn(Arrays.asList());

        // When
        StudentNotesResponse response = notesService.deleteNote(1L, 1L);

        // Then
        assertNotNull(response);
        assertEquals("Success", response.getStatus());
        assertEquals(0, response.getNotes().size());
        verify(noteRepository).delete(note);
    }

    @Test
    void deleteNote_WrongStudent() {
        // Given
        Student differentStudent = Student.builder()
                .studentId(2L)
                .build();
        Note noteWithDifferentStudent = Note.builder()
                .noteId(1L)
                .student(differentStudent)
                .build();

        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        when(noteRepository.findById(1L)).thenReturn(Optional.of(noteWithDifferentStudent));

        // When & Then
        assertThrows(NoSuchEntityException.class, () -> notesService.deleteNote(1L, 1L));
        verify(noteRepository, never()).delete(any());
    }
}
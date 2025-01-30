package com.jj.Gradebook.service.students;

import com.jj.Gradebook.controller.request.students.UpdateStudentDetailsRequest;
import com.jj.Gradebook.controller.response.BaseResponse;
import com.jj.Gradebook.controller.response.parents.ParentsResponse;
import com.jj.Gradebook.controller.response.students.StudentResponse;
import com.jj.Gradebook.controller.response.students.StudentsResponse;
import com.jj.Gradebook.dao.*;
import com.jj.Gradebook.dto.StudentDTO;
import com.jj.Gradebook.entity.Class;
import com.jj.Gradebook.entity.Parent;
import com.jj.Gradebook.entity.Student;
import com.jj.Gradebook.entity.Student_Parent.StudentParent;
import com.jj.Gradebook.entity.Student_Parent.StudentParentId;
import com.jj.Gradebook.entity.User;
import com.jj.Gradebook.exceptions.DateFormatException;
import com.jj.Gradebook.exceptions.NoSuchEntityException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StudentsServiceTest {

    @Mock
    private StudentRepository studentRepository;
    @Mock
    private ClassRepository classRepository;
    @Mock
    private GradeRepository gradeRepository;
    @Mock
    private AttendanceRepository attendanceRepository;
    @Mock
    private NoteRepository noteRepository;
    @Mock
    private StudentParentRepository studentParentRepository;
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private StudentsService studentsService;

    private Student student;
    private Class studentClass;
    private User user;
    private Parent parent;
    private StudentParent studentParent;

    @BeforeEach
    void setUp() {
        user = User.builder()
                .userId(1L)
                .email("student@test.com")
                .pesel("12345678901")
                .build();

        studentClass = Class.builder()
                .classId(1L)
                .className("1A")
                .build();

        student = Student.builder()
                .studentId(1L)
                .firstName("John")
                .lastName("Doe")
                .dateOfBirth(new Date())
                .city("Test City")
                .street("Test Street")
                .houseNumber("123")
                .studentClass(studentClass)
                .user(user)
                .build();

        parent = Parent.builder()
                .parentId(1L)
                .firstName("Parent")
                .lastName("Name")
                .build();

        studentParent = StudentParent.builder()
                .id(new StudentParentId(student.getStudentId(), parent.getParentId()))
                .student(student)
                .parent(parent)
                .build();
    }

    @Test
    void getAllStudents_Success() {
        // Given
        when(studentRepository.findAll()).thenReturn(Arrays.asList(student));

        // When
        StudentsResponse response = studentsService.getAllStudents();

        // Then
        assertNotNull(response);
        assertEquals("Success", response.getStatus());
        assertEquals(1, response.getStudents().size());
        assertEquals(student.getFirstName(), response.getStudents().get(0).getFirstName());
        assertEquals(student.getLastName(), response.getStudents().get(0).getLastName());
    }

    @Test
    void getStudentByID_Success() {
        // Given
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));

        // When
        StudentResponse response = studentsService.getStudentByID(1L);

        // Then
        assertNotNull(response);
        assertEquals("Success", response.getStatus());
        assertEquals(student.getFirstName(), response.getStudent().getFirstName());
        assertEquals(student.getLastName(), response.getStudent().getLastName());
    }

    @Test
    void getStudentByID_NotFound() {
        // Given
        when(studentRepository.findById(1L)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(NoSuchEntityException.class, () -> studentsService.getStudentByID(1L));
    }

    @Test
    void getParentsOfStudents_Success() {
        // Given
        Set<StudentParent> studentParents = new HashSet<>();
        studentParents.add(studentParent);
        student.setStudentParents(studentParents);

        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));

        // When
        ParentsResponse response = studentsService.getParentsOfStudents(1L);

        // Then
        assertNotNull(response);
        assertEquals("Success", response.getStatus());
        assertEquals(1, response.getParents().size());
        assertEquals(parent.getFirstName(), response.getParents().get(0).getFirstName());
    }

    @Test
    void updateStudentDetails_Success() {
        // Given
        StudentDTO studentDTO = StudentDTO.builder()
                .studentID(1L)
                .classID(1L)
                .firstName("Updated")
                .lastName("Name")
                .dateOfBirth("01-01-2000")
                .city("New City")
                .street("New Street")
                .houseNumber("456")
                .email("new@test.com")
                .pesel("98765432100")
                .build();

        UpdateStudentDetailsRequest request = new UpdateStudentDetailsRequest();
        request.setStudent(studentDTO);

        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        when(classRepository.findById(1L)).thenReturn(Optional.of(studentClass));
        when(studentRepository.save(any(Student.class))).thenReturn(student);

        // When
        StudentResponse response = studentsService.updateStudentDetails(request);

        // Then
        assertNotNull(response);
        assertEquals("Success", response.getStatus());
        verify(studentRepository).save(any(Student.class));
    }

    @Test
    void updateStudentDetails_InvalidDate() {
        // Given
        StudentDTO studentDTO = StudentDTO.builder()
                .studentID(1L)
                .classID(1L)
                .dateOfBirth("invalid-date")
                .build();

        UpdateStudentDetailsRequest request = new UpdateStudentDetailsRequest();
        request.setStudent(studentDTO);

        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        when(classRepository.findById(1L)).thenReturn(Optional.of(studentClass));

        // When & Then
        assertThrows(DateFormatException.class, () -> studentsService.updateStudentDetails(request));
    }

    @Test
    void deleteStudent_Success() {
        // Given
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        when(studentRepository.save(any(Student.class))).thenReturn(student);

        // When
        BaseResponse response = studentsService.deleteStudent(1L);

        // Then
        assertNotNull(response);
        assertEquals("Success", response.getStatus());
        verify(gradeRepository).deleteGradesByStudent_StudentId(1L);
        verify(attendanceRepository).deleteAttendancesByStudent_StudentId(1L);
        verify(noteRepository).deleteNotesByStudent_StudentId(1L);
        verify(studentParentRepository).deleteStudentParentsByStudent_StudentId(1L);
        verify(userRepository).deleteById(1L);
        verify(studentRepository).delete(any(Student.class));
    }

    @Test
    void deleteStudent_NotFound() {
        // Given
        when(studentRepository.findById(1L)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(NoSuchEntityException.class, () -> studentsService.deleteStudent(1L));
        verify(gradeRepository, never()).deleteGradesByStudent_StudentId(any());
        verify(attendanceRepository, never()).deleteAttendancesByStudent_StudentId(any());
        verify(noteRepository, never()).deleteNotesByStudent_StudentId(any());
        verify(studentParentRepository, never()).deleteStudentParentsByStudent_StudentId(any());
        verify(userRepository, never()).deleteById(any());
    }

    @Test
    void addStudentToClass_Success() {
        // Given
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        when(classRepository.findById(1L)).thenReturn(Optional.of(studentClass));
        when(studentRepository.save(any(Student.class))).thenReturn(student);

        // When
        BaseResponse response = studentsService.addStudentToClass(1L, 1L);

        // Then
        assertNotNull(response);
        assertEquals("Success", response.getStatus());
        verify(studentRepository).save(any(Student.class));
    }

    @Test
    void addStudentToClass_StudentNotFound() {
        // Given
        when(studentRepository.findById(1L)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(NoSuchEntityException.class, () -> studentsService.addStudentToClass(1L, 1L));
        verify(studentRepository, never()).save(any(Student.class));
    }

    @Test
    void addStudentToClass_ClassNotFound() {
        // Given
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        when(classRepository.findById(1L)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(NoSuchEntityException.class, () -> studentsService.addStudentToClass(1L, 1L));
        verify(studentRepository, never()).save(any(Student.class));
    }
}
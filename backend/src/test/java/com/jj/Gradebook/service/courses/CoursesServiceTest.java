package com.jj.Gradebook.service.courses;

import com.jj.Gradebook.controller.request.courses.AddNewCourseRequest;
import com.jj.Gradebook.controller.request.courses.UpdateCourseRequest;
import com.jj.Gradebook.dao.*;
import com.jj.Gradebook.entity.*;
import com.jj.Gradebook.entity.Class;
import com.jj.Gradebook.exceptions.NoSuchEntityException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CoursesServiceTest {

    @Mock
    private CoursesRepository coursesRepository;
    @Mock
    private TeacherRepository teacherRepository;
    @Mock
    private GradeRepository gradeRepository;
    @Mock
    private ClassRepository classRepository;
    @Mock
    private StudentRepository studentRepository;
    @Mock
    private AttendanceRepository attendanceRepository;
    @Mock
    private NoteRepository noteRepository;

    @InjectMocks
    private CoursesService coursesService;

    private SimpleDateFormat dateFormat;
    private Teacher teacher;
    private Course course;
    private Class classEntity;
    private Student student;
    private Date testDate;

    @BeforeEach
    void setUp() throws ParseException {
        dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        testDate = dateFormat.parse("01-01-2024");

        teacher = Teacher.builder()
                .teacherId(1L)
                .firstName("John")
                .lastName("Doe")
                .dateOfBirth(testDate)
                .dateOfEmployment(testDate)
                .build();

        course = Course.builder()
                .courseId(1L)
                .courseName("Mathematics")
                .description("Advanced Math")
                .teacher(teacher)
                .build();

        classEntity = Class.builder()
                .classId(1L)
                .className("Class A")
                .build();

        student = Student.builder()
                .studentId(1L)
                .firstName("Jane")
                .lastName("Smith")
                .studentClass(classEntity)
                .build();
    }

    @Test
    void getAllCourses_Success() {
        // Given
        when(coursesRepository.findAll()).thenReturn(List.of(course));

        // When
        var response = coursesService.getAllCourses();

        // Then
        assertNotNull(response);
        assertEquals("Success", response.getStatus());
        assertEquals(1, response.getCourses().size());
        assertEquals(course.getCourseName(), response.getCourses().get(0).getCourseName());
        verify(coursesRepository).findAll();
    }

    @Test
    void getCourseByCourseID_Success() {
        // Given
        when(coursesRepository.findById(1L)).thenReturn(Optional.of(course));

        // When
        var response = coursesService.getCourseByCourseID(1L);

        // Then
        assertNotNull(response);
        assertEquals("Success", response.getStatus());
        assertEquals(course.getCourseName(), response.getCourse().getCourseName());
        verify(coursesRepository).findById(1L);
    }

    @Test
    void getCourseByCourseID_NotFound() {
        // Given
        when(coursesRepository.findById(1L)).thenReturn(Optional.empty());

        // When/Then
        assertThrows(NoSuchEntityException.class, () -> coursesService.getCourseByCourseID(1L));
        verify(coursesRepository).findById(1L);
    }

    @Test
    void getCoursesOfTeacher_Success() {
        // Given
        when(teacherRepository.findById(1L)).thenReturn(Optional.of(teacher));
        when(coursesRepository.findCourseByTeacher_TeacherId(1L)).thenReturn(List.of(course));

        // When
        var response = coursesService.getCoursesOfTeacher(1L);

        // Then
        assertNotNull(response);
        assertEquals("Success", response.getStatus());
        assertEquals(1, response.getCourses().size());
        verify(teacherRepository).findById(1L);
        verify(coursesRepository).findCourseByTeacher_TeacherId(1L);
    }

    @Test
    void addNewCourse_Success() {
        // Given
        AddNewCourseRequest request = new AddNewCourseRequest();
        request.setTeacherID(1L);
        request.setCourseName("New Course");
        request.setCourseDescription("Description");

        when(teacherRepository.findById(1L)).thenReturn(Optional.of(teacher));
        when(coursesRepository.save(any(Course.class))).thenReturn(course);

        // When
        var response = coursesService.addNewCourse(request);

        // Then
        assertNotNull(response);
        assertEquals("Success", response.getStatus());
        verify(teacherRepository).findById(1L);
        verify(coursesRepository).save(any(Course.class));
    }

    @Test
    void updateCourse_Success() {
        // Given
        UpdateCourseRequest request = new UpdateCourseRequest();
        request.setCourseID(1L);
        request.setTeacherID(1L);
        request.setCourseName("Updated Course");
        request.setCourseDescription("Updated Description");

        when(coursesRepository.findById(1L)).thenReturn(Optional.of(course));
        when(teacherRepository.findById(1L)).thenReturn(Optional.of(teacher));
        when(coursesRepository.save(any(Course.class))).thenReturn(course);

        // When
        var response = coursesService.updateCourse(request);

        // Then
        assertNotNull(response);
        assertEquals("Success", response.getStatus());
        verify(coursesRepository).findById(1L);
        verify(teacherRepository).findById(1L);
        verify(coursesRepository).save(any(Course.class));
    }

    @Test
    void deleteCourse_Success() {
        // Given
        when(coursesRepository.findById(1L)).thenReturn(Optional.of(course));
        doNothing().when(coursesRepository).delete(any(Course.class));

        // When
        var response = coursesService.deleteCourse(1L);

        // Then
        assertNotNull(response);
        assertEquals("Success", response.getStatus());
        verify(coursesRepository).findById(1L);
        verify(coursesRepository).delete(any(Course.class));
    }

    @Test
    void getGradesInCourseOfClass_Success() {
        // Given
        Grade grade = Grade.builder()
                .gradeId(1L)
                .student(student)
                .course(course)
                .mark(5)
                .magnitude(1)
                .description("Test grade")
                .date(testDate)
                .build();

        when(classRepository.findById(1L)).thenReturn(Optional.of(classEntity));
        when(studentRepository.findStudentsByStudentClass_ClassId(1L)).thenReturn(List.of(student));
        when(gradeRepository.findGradesByStudent_StudentClass_ClassId(1L)).thenReturn(List.of(grade));

        // When
        var response = coursesService.getGradesInCourseOfClass(1L, 1L);

        // Then
        assertNotNull(response);
        assertEquals("Success", response.getStatus());
        assertTrue(response.getStudentsGrades().containsKey(1L));
        verify(classRepository).findById(1L);
        verify(studentRepository).findStudentsByStudentClass_ClassId(1L);
        verify(gradeRepository).findGradesByStudent_StudentClass_ClassId(1L);
    }

    @Test
    void getAttendanceInCourseOfStudent_Success() {
        // Given
        Attendance attendance = Attendance.builder()
                .attendanceId(1L)
                .student(student)
                .course(course)
                .dateTime(testDate)
                .status("PRESENT")
                .build();

        when(classRepository.findById(1L)).thenReturn(Optional.of(classEntity));
        when(studentRepository.findStudentsByStudentClass_ClassId(1L)).thenReturn(List.of(student));
        when(attendanceRepository.findAttendancesByStudent_StudentClass_ClassId(1L)).thenReturn(List.of(attendance));

        // When
        var response = coursesService.getAttendanceInCourseOfStudent(1L, 1L);

        // Then
        assertNotNull(response);
        assertEquals("Success", response.getStatus());
        assertTrue(response.getStudentsAttendance().containsKey(1L));
        verify(classRepository).findById(1L);
        verify(studentRepository).findStudentsByStudentClass_ClassId(1L);
        verify(attendanceRepository).findAttendancesByStudent_StudentClass_ClassId(1L);
    }

    @Test
    void getNotesInCourseOfClass_Success() {
        // Given
        Note note = Note.builder()
                .noteId(1L)
                .student(student)
                .course(course)
                .dateTime(testDate)
                .title("Test Note")
                .description("Test Description")
                .build();

        when(classRepository.findById(1L)).thenReturn(Optional.of(classEntity));
        when(studentRepository.findStudentsByStudentClass_ClassId(1L)).thenReturn(List.of(student));
        when(noteRepository.findNotesByStudent_StudentClass_ClassId(1L)).thenReturn(List.of(note));

        // When
        var response = coursesService.getNotesInCourseOfClass(1L, 1L);

        // Then
        assertNotNull(response);
        assertEquals("Success", response.getStatus());
        assertTrue(response.getStudentsNotes().containsKey(1L));
        verify(classRepository).findById(1L);
        verify(studentRepository).findStudentsByStudentClass_ClassId(1L);
        verify(noteRepository).findNotesByStudent_StudentClass_ClassId(1L);
    }
}
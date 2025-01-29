package com.jj.Gradebook.service.classes;

import com.jj.Gradebook.controller.request.classes.CreateClassRequest;
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
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.Year;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClassesServiceTest {

    @Mock
    private ClassRepository classRepository;
    @Mock
    private TimetableRepository timetableRepository;
    @Mock
    private StudentRepository studentRepository;
    @Mock
    private TeacherRepository teacherRepository;
    @Mock
    private AttendanceRepository attendanceRepository;
    @Mock
    private NoteRepository noteRepository;
    @Mock
    private CoursesRepository coursesRepository;

    @InjectMocks
    private ClassesService classesService;

    private SimpleDateFormat dateFormat;
    private Teacher teacher;
    private Class classEntity;
    private Student student;
    private Course course;
    private Timetable timetable;
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

        classEntity = Class.builder()
                .classId(1L)
                .className("Class A")
                .startYear(Year.of(2024))
                .teacher(teacher)
                .build();

        student = Student.builder()
                .studentId(1L)
                .firstName("Jane")
                .lastName("Smith")
                .dateOfBirth(testDate)
                .city("New York")
                .street("Main St")
                .houseNumber("123")
                .studentClass(classEntity)
                .build();

        course = Course.builder()
                .courseId(1L)
                .courseName("Mathematics")
                .teacher(teacher)
                .build();

        timetable = Timetable.builder()
                .timetableId(1L)
                .clas(classEntity)
                .course(course)
                .dayOfWeek(1)
                .startTime(LocalTime.of(8, 0))
                .endTime(LocalTime.of(9, 30))
                .classroomNumber("101")
                .build();
    }

    @Test
    void getAllClasses_Success() {
        // Given
        when(classRepository.findAll()).thenReturn(List.of(classEntity));

        // When
        var response = classesService.getAllClasses();

        // Then
        assertNotNull(response);
        assertEquals("Success", response.getStatus());
        assertEquals(1, response.getClasses().size());
        assertEquals(classEntity.getClassName(), response.getClasses().get(0).getClassName());
        verify(classRepository).findAll();
    }

    @Test
    void getClassByClassID_Success() {
        // Given
        when(classRepository.findById(1L)).thenReturn(Optional.of(classEntity));

        // When
        var response = classesService.getClassByClassID(1L);

        // Then
        assertNotNull(response);
        assertEquals("Success", response.getStatus());
        assertEquals(classEntity.getClassName(), response.getTheClass().getClassName());
        verify(classRepository).findById(1L);
    }

    @Test
    void getClassByClassID_NotFound() {
        // Given
        when(classRepository.findById(1L)).thenReturn(Optional.empty());

        // When/Then
        assertThrows(NoSuchEntityException.class, () -> classesService.getClassByClassID(1L));
        verify(classRepository).findById(1L);
    }

    @Test
    void getTimetableOfClass_Success() {
        // Given
        when(classRepository.findById(1L)).thenReturn(Optional.of(classEntity));
        when(timetableRepository.findTimetablesByClas_ClassId(1L)).thenReturn(List.of(timetable));

        // When
        var response = classesService.getTimetableOfClass(1L);

        // Then
        assertNotNull(response);
        assertEquals("Success", response.getStatus());
        assertTrue(response.getTimetable().containsKey(DayOfWeek.MONDAY));
        verify(classRepository).findById(1L);
        verify(timetableRepository).findTimetablesByClas_ClassId(1L);
    }

    @Test
    void getStudentsOfClass_Success() {
        // Given
        when(classRepository.findById(1L)).thenReturn(Optional.of(classEntity));
        when(studentRepository.findStudentsByStudentClass_ClassId(1L)).thenReturn(List.of(student));

        // When
        var response = classesService.getStudentsOfClass(1L);

        // Then
        assertNotNull(response);
        assertEquals("Success", response.getStatus());
        assertEquals(1, response.getStudents().size());
        assertEquals(student.getFirstName(), response.getStudents().get(0).getFirstName());
        verify(classRepository).findById(1L);
        verify(studentRepository).findStudentsByStudentClass_ClassId(1L);
    }

    @Test
    void createNewClass_Success() {
        // Given
        CreateClassRequest request = new CreateClassRequest();
        request.setClassName("New Class");
        request.setStartYear(2024);
        request.setTeacherID(1L);

        when(teacherRepository.findById(1L)).thenReturn(Optional.of(teacher));
        when(classRepository.save(any(Class.class))).thenReturn(classEntity);

        // When
        var response = classesService.createNewClass(request);

        // Then
        assertNotNull(response);
        assertEquals("Success", response.getStatus());
        verify(teacherRepository).findById(1L);
        verify(classRepository).save(any(Class.class));
    }


    @Test
    void deleteClass_Success() {
        // Given
        when(classRepository.findById(1L)).thenReturn(Optional.of(classEntity));
        when(studentRepository.findStudentsByStudentClass_ClassId(1L)).thenReturn(List.of(student));
        doNothing().when(attendanceRepository).deleteAllByStudent_StudentClass_ClassId(1L);
        doNothing().when(noteRepository).deleteAllByStudent_StudentClass_ClassId(1L);
        doNothing().when(timetableRepository).deleteAllByClas_ClassId(1L);
        when(studentRepository.saveAll(any())).thenReturn(List.of(student));
        doNothing().when(classRepository).delete(any(Class.class));

        // When
        var response = classesService.deleteClass(1L);

        // Then
        assertNotNull(response);
        assertEquals("Success", response.getStatus());
        verify(classRepository).findById(1L);
        verify(attendanceRepository).deleteAllByStudent_StudentClass_ClassId(1L);
        verify(noteRepository).deleteAllByStudent_StudentClass_ClassId(1L);
        verify(timetableRepository).deleteAllByClas_ClassId(1L);
        verify(studentRepository).saveAll(any());
        verify(classRepository).delete(any(Class.class));
    }

    @Test
    void addTeacherToClass_Success() {
        // Given
        when(classRepository.findById(1L)).thenReturn(Optional.of(classEntity));
        when(teacherRepository.findById(1L)).thenReturn(Optional.of(teacher));
        when(classRepository.save(any(Class.class))).thenReturn(classEntity);

        // When
        var response = classesService.addTeacherToClass(1L, 1L);

        // Then
        assertNotNull(response);
        assertEquals("Success", response.getStatus());
        verify(classRepository).findById(1L);
        verify(teacherRepository).findById(1L);
        verify(classRepository).save(any(Class.class));
    }
}
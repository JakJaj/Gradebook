package com.jj.Gradebook.service.grades;

import com.jj.Gradebook.controller.request.grades.CreateGradeRequest;
import com.jj.Gradebook.controller.request.grades.UpdateGradeDetailsRequest;
import com.jj.Gradebook.dao.CoursesRepository;
import com.jj.Gradebook.dao.GradeRepository;
import com.jj.Gradebook.dao.StudentRepository;
import com.jj.Gradebook.dao.TimetableRepository;
import com.jj.Gradebook.entity.*;
import com.jj.Gradebook.entity.Class;
import com.jj.Gradebook.exceptions.DateFormatException;
import com.jj.Gradebook.exceptions.NoSuchEntityException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GradesServiceTest {

    @Mock
    private GradeRepository gradeRepository;
    @Mock
    private StudentRepository studentRepository;
    @Mock
    private CoursesRepository coursesRepository;
    @Mock
    private TimetableRepository timetableRepository;

    @InjectMocks
    private GradesService gradesService;

    private SimpleDateFormat dateFormat;
    private Student student;
    private Course course;
    private Grade grade;
    private Class classEntity;
    private Timetable timetable;
    private Date testDate;
    private String testDateStr;

    @BeforeEach
    void setUp() throws ParseException {
        dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        testDateStr = "01-01-2024";
        testDate = dateFormat.parse(testDateStr);

        classEntity = Class.builder()
                .classId(1L)
                .className("Class A")
                .build();

        student = Student.builder()
                .studentId(1L)
                .firstName("John")
                .lastName("Doe")
                .studentClass(classEntity)
                .build();

        course = Course.builder()
                .courseId(1L)
                .courseName("Mathematics")
                .build();

        grade = Grade.builder()
                .gradeId(1L)
                .student(student)
                .course(course)
                .mark(4)
                .magnitude(1)
                .description("Test grade")
                .date(testDate)
                .build();

        timetable = Timetable.builder()
                .timetableId(1L)
                .clas(classEntity)
                .course(course)
                .dayOfWeek(1)
                .startTime(LocalTime.of(8, 0))
                .endTime(LocalTime.of(9, 30))
                .build();
    }

    @Test
    void getAllStudentsGrades_Success() {
        // Given
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        when(gradeRepository.findGradesByStudent_StudentId(1L)).thenReturn(List.of(grade));
        when(timetableRepository.findTimetablesByClas_ClassId(1L)).thenReturn(List.of(timetable));

        // When
        var response = gradesService.getAllStudentsGrades(1L);

        // Then
        assertNotNull(response);
        assertEquals("Success", response.getStatus());
        assertTrue(response.getGrades().containsKey("Mathematics"));
        assertEquals(1, response.getGrades().get("Mathematics").size());
        verify(studentRepository).findById(1L);
        verify(gradeRepository).findGradesByStudent_StudentId(1L);
        verify(timetableRepository).findTimetablesByClas_ClassId(1L);
    }

    @Test
    void getAllStudentsGrades_StudentNotFound() {
        // Given
        when(studentRepository.findById(1L)).thenReturn(Optional.empty());

        // When/Then
        assertThrows(NoSuchEntityException.class, () -> gradesService.getAllStudentsGrades(1L));
        verify(studentRepository).findById(1L);
    }

    @Test
    void createNewGrade_Success() {
        // Given
        CreateGradeRequest request = new CreateGradeRequest();
        request.setCourseID(1L);
        request.setMark(4);
        request.setMagnitude(1);
        request.setDescription("New grade");
        request.setDate(testDateStr);

        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        when(coursesRepository.findById(1L)).thenReturn(Optional.of(course));
        when(gradeRepository.save(any(Grade.class))).thenReturn(grade);

        // When
        var response = gradesService.createNewGrade(request, 1L);

        // Then
        assertNotNull(response);
        assertEquals("Success", response.getStatus());
        assertEquals(grade.getGradeId(), response.getGradeID());
        verify(studentRepository).findById(1L);
        verify(coursesRepository).findById(1L);
        verify(gradeRepository).save(any(Grade.class));
    }

    @Test
    void createNewGrade_InvalidDateFormat() {
        // Given
        CreateGradeRequest request = new CreateGradeRequest();
        request.setCourseID(1L);
        request.setDate("invalid-date");

        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        when(coursesRepository.findById(1L)).thenReturn(Optional.of(course));

        // When/Then
        assertThrows(DateFormatException.class, () -> gradesService.createNewGrade(request, 1L));
    }

    @Test
    void updateGradeDetails_Success() {
        // Given
        UpdateGradeDetailsRequest request = new UpdateGradeDetailsRequest();
        request.setGradeID(1L);
        request.setMark(5);
        request.setMagnitude(1);
        request.setDescription("Updated grade");
        request.setDate(testDateStr);

        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        when(gradeRepository.findById(1L)).thenReturn(Optional.of(grade));
        when(gradeRepository.save(any(Grade.class))).thenReturn(grade);

        // When
        var response = gradesService.updateGradeDetails(1L, request);

        // Then
        assertNotNull(response);
        assertEquals("Success", response.getStatus());
        assertNotNull(response.getGrade());
        verify(studentRepository).findById(1L);
        verify(gradeRepository).findById(1L);
        verify(gradeRepository).save(any(Grade.class));
    }

    @Test
    void updateGradeDetails_GradeNotBelongToStudent() {
        // Given
        UpdateGradeDetailsRequest request = new UpdateGradeDetailsRequest();
        request.setGradeID(1L);

        Student otherStudent = Student.builder()
                .studentId(2L)
                .build();
        Grade otherGrade = Grade.builder()
                .gradeId(1L)
                .student(otherStudent)
                .build();

        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        when(gradeRepository.findById(1L)).thenReturn(Optional.of(otherGrade));

        // When/Then
        assertThrows(NoSuchEntityException.class, () -> gradesService.updateGradeDetails(1L, request));
    }

    @Test
    void deleteGrade_GradeNotBelongToStudent() {
        // Given
        Student otherStudent = Student.builder()
                .studentId(2L)
                .build();
        Grade otherGrade = Grade.builder()
                .gradeId(1L)
                .student(otherStudent)
                .build();

        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        when(gradeRepository.findById(1L)).thenReturn(Optional.of(otherGrade));

        // When/Then
        assertThrows(NoSuchEntityException.class, () -> gradesService.deleteGrade(1L, 1L));
    }
}
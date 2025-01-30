package com.jj.Gradebook.service.attendances;

import com.jj.Gradebook.controller.request.attendances.CreateAttendanceRequest;
import com.jj.Gradebook.controller.request.attendances.UpdateAttendanceDetailsRequest;
import com.jj.Gradebook.controller.response.attendance.AttendanceResponse;
import com.jj.Gradebook.controller.response.attendance.ClassAttendanceResponse;
import com.jj.Gradebook.controller.response.attendance.StudentAttendanceListResponse;
import com.jj.Gradebook.controller.response.students.StudentAttendancesResponse;
import com.jj.Gradebook.dao.*;
import com.jj.Gradebook.dto.AttendanceDTO;
import com.jj.Gradebook.dto.CourseDTO;
import com.jj.Gradebook.dto.TeacherDTO;
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
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AttendanceServiceTest {

    @InjectMocks
    private AttendancesService attendancesService;

    @Mock
    private AttendanceRepository attendanceRepository;

    @Mock
    private ClassRepository classRepository;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private CoursesRepository coursesRepository;

    @Mock
    private TimetableRepository timetableRepository;

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    private Student student;
    private Course course;
    private Attendance attendance;

    @BeforeEach
    void setUp() throws ParseException {
        student = new Student();
        student.setStudentId(1L);
        student.setFirstName("John");
        student.setLastName("Doe");
        student.setDateOfBirth(dateFormat.parse("01-01-2000"));

        course = new Course();
        course.setCourseId(1L);
        course.setCourseName("Math");

        attendance = new Attendance();
        attendance.setAttendanceId(1L);
        attendance.setStudent(student);
        attendance.setCourse(course);
        attendance.setStatus("Present");
        attendance.setDateTime(dateFormat.parse("15-01-2024"));
    }

    @Test
    void testGetAttendanceOfClass() {
        Class studentClass = new Class();
        studentClass.setClassId(1L);
        when(classRepository.findById(1L)).thenReturn(Optional.of(studentClass));
        when(attendanceRepository.findAttendancesByStudent_StudentClass_ClassId(1L)).thenReturn(List.of(attendance));
        when(studentRepository.findStudentsByStudentClass_ClassId(1L)).thenReturn(List.of(student));

        ClassAttendanceResponse response = attendancesService.getAttendanceOfClass(1L);
        assertNotNull(response);
        assertEquals("Success", response.getStatus());
    }


    @Test
    void testCreateNewAttendance() throws ParseException {
        // Mock Teacher
        Teacher teacher = new Teacher();
        teacher.setTeacherId(100L);
        teacher.setFirstName("John");
        teacher.setLastName("Doe");

        // Mock Class
        Class studentClass = new Class();
        studentClass.setClassId(10L);
        studentClass.setClassName("1A");
        studentClass.setTeacher(teacher);

        // Mock Student
        Student student = new Student();
        student.setStudentId(1L);
        student.setStudentClass(studentClass); // Ensure studentClass is set

        // Mock Course
        Course course = new Course();
        course.setCourseId(1L);
        course.setCourseName("Math");
        course.setTeacher(teacher); // Ensure teacher is set

        // Mock Request
        CreateAttendanceRequest request = new CreateAttendanceRequest();
        request.setCourseID(1L);
        request.setStatus("Present");
        request.setDate("10-01-2025");

        // Mock Repository Calls
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        when(coursesRepository.findById(1L)).thenReturn(Optional.of(course));
        when(attendanceRepository.save(any())).thenAnswer(invocation -> {
            Attendance savedAttendance = invocation.getArgument(0);
            savedAttendance.setAttendanceId(99L); // Mock ID assignment
            return savedAttendance;
        });

        // Execute
        StudentAttendancesResponse response = attendancesService.createNewAttendance(1L, request);

        // Assertions
        assertNotNull(response);
        assertEquals("Success", response.getStatus());
        assertEquals(99L, response.getAttendanceID());
    }


    @Test
    void testUpdateAttendanceDetails() throws ParseException {
        // Mock Teacher
        Teacher teacher = new Teacher();
        teacher.setTeacherId(100L);
        teacher.setFirstName("John");
        teacher.setLastName("Doe");

        // Mock Course
        Course course = new Course();
        course.setCourseId(1L);
        course.setCourseName("Math");
        course.setTeacher(teacher); // Ensure teacher is set

        // Mock Student
        Student student = new Student();
        student.setStudentId(1L);

        // Mock Attendance
        Attendance attendance = new Attendance();
        attendance.setAttendanceId(50L);
        attendance.setStatus("Absent");
        attendance.setStudent(student);
        attendance.setCourse(course); // Ensure course is set

        // Mock Request
        UpdateAttendanceDetailsRequest request = new UpdateAttendanceDetailsRequest();
        request.setAttendanceID(50L);
        request.setStatus("Present");
        request.setDate("12-01-2025");

        // Mock Repository Calls
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        when(attendanceRepository.findById(50L)).thenReturn(Optional.of(attendance));
        when(attendanceRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        // Execute
        AttendanceResponse response = attendancesService.updateAttendanceDetails(1L, request);

        // Assertions
        assertNotNull(response);
        assertEquals("Success", response.getStatus());
        assertEquals("Present", response.getAttendance().getStatus());
    }

}

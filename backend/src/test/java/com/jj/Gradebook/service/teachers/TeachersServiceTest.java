package com.jj.Gradebook.service.teachers;

import com.jj.Gradebook.controller.request.teachers.UpdateTeacherDetailsRequest;
import com.jj.Gradebook.controller.response.BaseResponse;
import com.jj.Gradebook.controller.response.teachers.TeacherResponse;
import com.jj.Gradebook.controller.response.teachers.TeachersResponse;
import com.jj.Gradebook.dao.*;
import com.jj.Gradebook.dto.TeacherDTO;
import com.jj.Gradebook.entity.Class;
import com.jj.Gradebook.entity.Teacher;
import com.jj.Gradebook.entity.User;
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
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TeachersServiceTest {

    @Mock
    private TeacherRepository teacherRepository;
    @Mock
    private AnnouncementRepository announcementRepository;
    @Mock
    private ClassRepository classRepository;
    @Mock
    private CoursesRepository coursesRepository;
    @Mock
    private GradeRepository gradeRepository;
    @Mock
    private TimetableRepository timetableRepository;
    @Mock
    private AttendanceRepository attendanceRepository;
    @Mock
    private NoteRepository noteRepository;

    @InjectMocks
    private TeachersService teachersService;

    private SimpleDateFormat dateFormat;
    private Teacher testTeacher;
    private User testUser;
    private Date testDate;

    @BeforeEach
    void setUp() throws Exception {
        dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        testDate = dateFormat.parse("01-01-2000");

        testUser = User.builder()
                .email("test@test.com")
                .pesel("12345678901")
                .build();

        testTeacher = Teacher.builder()
                .teacherId(1L)
                .firstName("John")
                .lastName("Doe")
                .dateOfBirth(testDate)
                .dateOfEmployment(testDate)
                .user(testUser)
                .build();
    }

    @Test
    void getAllTeachers_ShouldReturnTeachersList() {
        // Arrange
        List<Teacher> teachers = Arrays.asList(testTeacher);
        when(teacherRepository.findAll()).thenReturn(teachers);

        // Act
        TeachersResponse response = teachersService.getAllTeachers();

        // Assert
        assertNotNull(response);
        assertEquals("Success", response.getStatus());
        assertEquals(1, response.getTeachers().size());
        TeacherDTO teacherDTO = response.getTeachers().get(0);
        assertEquals(testTeacher.getTeacherId(), teacherDTO.getTeacherID());
        assertEquals(testTeacher.getFirstName(), teacherDTO.getFirstName());
        assertEquals(testTeacher.getLastName(), teacherDTO.getLastName());
        assertEquals(testTeacher.getUser().getPesel(), teacherDTO.getPesel());
    }

    @Test
    void getTeacherByID_ShouldReturnTeacher_WhenTeacherExists() {
        // Arrange
        when(teacherRepository.findById(1L)).thenReturn(Optional.of(testTeacher));

        // Act
        TeacherResponse response = teachersService.getTeacherByID(1L);

        // Assert
        assertNotNull(response);
        assertEquals("Success", response.getStatus());
        assertEquals(testTeacher.getTeacherId(), response.getTeacher().getTeacherID());
        assertEquals(testTeacher.getFirstName(), response.getTeacher().getFirstName());
    }

    @Test
    void getTeacherByID_ShouldThrowException_WhenTeacherNotFound() {
        // Arrange
        when(teacherRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NoSuchEntityException.class, () -> teachersService.getTeacherByID(1L));
    }

    @Test
    void updateTeacherDetails_ShouldUpdateAndReturnTeacher() throws Exception {
        // Arrange
        TeacherDTO updateDTO = TeacherDTO.builder()
                .teacherID(1L)
                .firstName("Jane")
                .lastName("Smith")
                .dateOfBirth("01-01-2000")
                .dateOfEmployment("01-01-2000")
                .email("new@test.com")
                .pesel("98765432100")
                .build();

        UpdateTeacherDetailsRequest request = new UpdateTeacherDetailsRequest();
        request.setTeacher(updateDTO);

        when(teacherRepository.findById(1L)).thenReturn(Optional.of(testTeacher));
        when(teacherRepository.save(any(Teacher.class))).thenAnswer(i -> i.getArgument(0));

        // Act
        TeacherResponse response = teachersService.updateTeacherDetails(request);

        // Assert
        assertNotNull(response);
        assertEquals("Success", response.getStatus());
        assertEquals(updateDTO.getFirstName(), response.getTeacher().getFirstName());
        assertEquals(updateDTO.getLastName(), response.getTeacher().getLastName());
        assertEquals(updateDTO.getEmail(), response.getTeacher().getEmail());
    }

    @Test
    void updateTeacherDetails_ShouldThrowException_WhenInvalidDateFormat() {
        // Arrange
        TeacherDTO updateDTO = TeacherDTO.builder()
                .teacherID(1L)
                .dateOfBirth("invalid-date")
                .dateOfEmployment("invalid-date")
                .build();

        UpdateTeacherDetailsRequest request = new UpdateTeacherDetailsRequest();
        request.setTeacher(updateDTO);

        when(teacherRepository.findById(1L)).thenReturn(Optional.of(testTeacher));

        // Act & Assert
        assertThrows(DateFormatException.class, () -> teachersService.updateTeacherDetails(request));
    }

    @Test
    void deleteTeacher_ShouldDeleteTeacherAndRelatedData() {
        // Arrange
        when(teacherRepository.findById(1L)).thenReturn(Optional.of(testTeacher));
        List<Class> classes = Arrays.asList(new Class());
        when(classRepository.findClassesByTeacher_TeacherId(1L)).thenReturn(classes);

        // Act
        BaseResponse response = teachersService.deleteTeacher(1L);

        // Assert
        assertNotNull(response);
        assertEquals("Success", response.getStatus());

        // Verify all related data is deleted
        verify(classRepository).saveAll(classes);
        verify(gradeRepository).deleteGradesByCourse_Teacher_TeacherId(1L);
        verify(attendanceRepository).deleteAllByCourse_Teacher_TeacherId(1L);
        verify(noteRepository).deleteAllByCourse_Teacher_TeacherId(1L);
        verify(timetableRepository).deleteAllByCourse_Teacher_TeacherId(1L);
        verify(coursesRepository).deleteCoursesByTeacher_TeacherId(1L);
        verify(announcementRepository).deleteAllByTeacher_TeacherId(1L);
        verify(teacherRepository).delete(testTeacher);
    }

    @Test
    void deleteTeacher_ShouldThrowException_WhenTeacherNotFound() {
        // Arrange
        when(teacherRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NoSuchEntityException.class, () -> teachersService.deleteTeacher(1L));
    }
}
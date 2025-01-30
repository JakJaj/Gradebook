package com.jj.Gradebook.service.timetable;

import com.jj.Gradebook.config.JwtService;
import com.jj.Gradebook.controller.request.timetables.CreateTimetableEntry;
import com.jj.Gradebook.controller.request.timetables.CreateTimetableRequest;
import com.jj.Gradebook.controller.response.BaseResponse;
import com.jj.Gradebook.controller.response.classes.TimetableResponse;
import com.jj.Gradebook.dao.*;
import com.jj.Gradebook.entity.*;
import com.jj.Gradebook.entity.Class;
import com.jj.Gradebook.exceptions.NoSuchEntityException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class TimetablesServiceTest {

    @Mock
    private TimetableRepository timetableRepository;

    @Mock
    private JwtService jwtService;

    @Mock
    private TeacherRepository teacherRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private ParentRepository parentRepository;

    @Mock
    private CoursesRepository coursesRepository;

    @Mock
    private ClassRepository classRepository;

    @Mock
    private AttendanceRepository attendanceRepository;

    @Mock
    private NoteRepository noteRepository;

    @InjectMocks
    private TimetablesService timetablesService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getUsersTimetable_throwsExceptionForInvalidUser() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        NoSuchEntityException exception = assertThrows(NoSuchEntityException.class, () -> timetablesService.getUsersTimetable(1L));

        assertEquals("No user with id - 1", exception.getMessage());
    }


    @Test
    void deleteTimetableEntry_deletesTimetableEntry() {
        Timetable timetable = new Timetable();
        timetable.setTimetableId(1L);
        Class theClass = new Class();
        theClass.setClassId(1L);
        timetable.setClas(theClass);

        when(timetableRepository.findById(1L)).thenReturn(Optional.of(timetable));

        BaseResponse response = timetablesService.deleteTimetableEntry(1L);

        assertEquals("Success", response.getStatus());
        assertEquals("Successfully deleted timetable entry with id - 1", response.getMessage());
        verify(timetableRepository, times(1)).delete(timetable);
    }

    @Test
    void deleteTimetableEntry_throwsExceptionForInvalidTimetableId() {
        when(timetableRepository.findById(1L)).thenReturn(Optional.empty());

        NoSuchEntityException exception = assertThrows(NoSuchEntityException.class, () -> timetablesService.deleteTimetableEntry(1L));

        assertEquals("No timetable entry with id - 1", exception.getMessage());
    }
}

package com.jj.Gradebook.service.announcement;

import com.jj.Gradebook.controller.request.announcements.CreateAnnouncementRequest;
import com.jj.Gradebook.controller.request.announcements.UpdateAnnouncementDetailsRequest;
import com.jj.Gradebook.controller.response.BaseResponse;
import com.jj.Gradebook.controller.response.announcements.AnnouncementResponse;
import com.jj.Gradebook.controller.response.announcements.AnnouncementsResponse;
import com.jj.Gradebook.dao.AnnouncementRepository;
import com.jj.Gradebook.dao.TeacherRepository;
import com.jj.Gradebook.dto.AnnouncementDTO;
import com.jj.Gradebook.dto.TeacherDTO;
import com.jj.Gradebook.entity.Announcements;
import com.jj.Gradebook.entity.Teacher;
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
import java.util.Collections;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AnnouncementsServiceTest {

    @Mock
    private AnnouncementRepository announcementRepository;

    @Mock
    private TeacherRepository teacherRepository;

    @InjectMocks
    private AnnouncementsService announcementsService;

    private SimpleDateFormat dateFormat;

    @BeforeEach
    void setUp() {
        dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    }

    @Test
    void testGetAllAnnouncements() {
        // Arrange
        Teacher teacher = new Teacher();
        teacher.setTeacherId(1L);
        teacher.setFirstName("John");
        teacher.setLastName("Doe");
        teacher.setDateOfBirth(new Date());
        teacher.setDateOfEmployment(new Date());

        Announcements announcement = new Announcements();
        announcement.setAnnouncementId(1L);
        announcement.setTitle("Test Title");
        announcement.setContent("Test Content");
        announcement.setDateTime(new Date());
        announcement.setTeacher(teacher);

        when(announcementRepository.findAll()).thenReturn(Collections.singletonList(announcement));

        // Act
        AnnouncementsResponse response = announcementsService.getAllAnnouncements();

        // Assert
        assertNotNull(response);
        assertEquals("Success", response.getStatus());
        assertEquals("Successfully returning all announcements", response.getMessage());
        assertEquals(1, response.getAnnouncements().size());

        AnnouncementDTO announcementDTO = response.getAnnouncements().get(0);
        assertEquals(1L, announcementDTO.getAnnouncementID());
        assertEquals("Test Title", announcementDTO.getTitle());
        assertEquals("Test Content", announcementDTO.getContent());
        assertEquals(1L, announcementDTO.getTeacher().getTeacherID());
        assertEquals("John", announcementDTO.getTeacher().getFirstName());
        assertEquals("Doe", announcementDTO.getTeacher().getLastName());

        verify(announcementRepository, times(1)).findAll();
    }

    @Test
    void testCreateNewAnnouncement() throws ParseException {
        // Arrange
        CreateAnnouncementRequest request = new CreateAnnouncementRequest();
        request.setTitle("New Title");
        request.setContent("New Content");
        request.setDate("01-01-2023");
        request.setTeacherID(1L);

        Teacher teacher = new Teacher();
        teacher.setTeacherId(1L);
        teacher.setFirstName("John");
        teacher.setLastName("Doe");
        teacher.setDateOfBirth(new Date());
        teacher.setDateOfEmployment(new Date());

        Announcements savedAnnouncement = new Announcements();
        savedAnnouncement.setAnnouncementId(1L);
        savedAnnouncement.setTitle("New Title");
        savedAnnouncement.setContent("New Content");
        savedAnnouncement.setDateTime(dateFormat.parse("01-01-2023"));
        savedAnnouncement.setTeacher(teacher);

        when(teacherRepository.findById(1L)).thenReturn(Optional.of(teacher));
        when(announcementRepository.save(any(Announcements.class))).thenReturn(savedAnnouncement);

        // Act
        AnnouncementResponse response = announcementsService.createNewAnnouncement(request);

        // Assert
        assertNotNull(response);
        assertEquals("Success", response.getStatus());
        assertEquals("Successfully created new announcement", response.getMessage());

        AnnouncementDTO announcementDTO = response.getAnnouncement();
        assertEquals(1L, announcementDTO.getAnnouncementID());
        assertEquals("New Title", announcementDTO.getTitle());
        assertEquals("New Content", announcementDTO.getContent());
        assertEquals("01-01-2023", announcementDTO.getDate());
        assertEquals(1L, announcementDTO.getTeacher().getTeacherID());
        assertEquals("John", announcementDTO.getTeacher().getFirstName());
        assertEquals("Doe", announcementDTO.getTeacher().getLastName());

        verify(teacherRepository, times(1)).findById(1L);
        verify(announcementRepository, times(1)).save(any(Announcements.class));
    }

    @Test
    void testCreateNewAnnouncement_TeacherNotFound() {
        // Arrange
        CreateAnnouncementRequest request = new CreateAnnouncementRequest();
        request.setTeacherID(1L);

        when(teacherRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NoSuchEntityException.class, () -> announcementsService.createNewAnnouncement(request));

        verify(teacherRepository, times(1)).findById(1L);
        verify(announcementRepository, never()).save(any(Announcements.class));
    }

    @Test
    void testCreateNewAnnouncement_InvalidDateFormat() {
        // Arrange
        CreateAnnouncementRequest request = new CreateAnnouncementRequest();
        request.setTitle("New Title");
        request.setContent("New Content");
        request.setDate("invalid-date");
        request.setTeacherID(1L);

        Teacher teacher = new Teacher();
        teacher.setTeacherId(1L);

        when(teacherRepository.findById(1L)).thenReturn(Optional.of(teacher));

        // Act & Assert
        assertThrows(DateFormatException.class, () -> announcementsService.createNewAnnouncement(request));

        verify(teacherRepository, times(1)).findById(1L);
        verify(announcementRepository, never()).save(any(Announcements.class));
    }

    @Test
    void testUpdateAnnouncementDetail() throws ParseException {
        // Arrange
        UpdateAnnouncementDetailsRequest request = new UpdateAnnouncementDetailsRequest();
        request.setAnnouncementID(1L);
        request.setTitle("Updated Title");
        request.setContent("Updated Content");
        request.setDate("02-01-2023");

        Teacher teacher = new Teacher();
        teacher.setTeacherId(1L);
        teacher.setFirstName("John");
        teacher.setLastName("Doe");
        teacher.setDateOfBirth(new Date());
        teacher.setDateOfEmployment(new Date());

        Announcements existingAnnouncement = new Announcements();
        existingAnnouncement.setAnnouncementId(1L);
        existingAnnouncement.setTitle("Old Title");
        existingAnnouncement.setContent("Old Content");
        existingAnnouncement.setDateTime(dateFormat.parse("01-01-2023"));
        existingAnnouncement.setTeacher(teacher);

        Announcements updatedAnnouncement = new Announcements();
        updatedAnnouncement.setAnnouncementId(1L);
        updatedAnnouncement.setTitle("Updated Title");
        updatedAnnouncement.setContent("Updated Content");
        updatedAnnouncement.setDateTime(dateFormat.parse("02-01-2023"));
        updatedAnnouncement.setTeacher(teacher);

        when(announcementRepository.findById(1L)).thenReturn(Optional.of(existingAnnouncement));
        when(announcementRepository.save(any(Announcements.class))).thenReturn(updatedAnnouncement);

        // Act
        AnnouncementResponse response = announcementsService.updateAnnouncementDetail(request);

        // Assert
        assertNotNull(response);
        assertEquals("Success", response.getStatus());
        assertEquals("Successfully updated announcement with id - 1", response.getMessage());

        AnnouncementDTO announcementDTO = response.getAnnouncement();
        assertEquals(1L, announcementDTO.getAnnouncementID());
        assertEquals("Updated Title", announcementDTO.getTitle());
        assertEquals("Updated Content", announcementDTO.getContent());
        assertEquals("02-01-2023", announcementDTO.getDate());
        assertEquals(1L, announcementDTO.getTeacher().getTeacherID());
        assertEquals("John", announcementDTO.getTeacher().getFirstName());
        assertEquals("Doe", announcementDTO.getTeacher().getLastName());

        verify(announcementRepository, times(1)).findById(1L);
        verify(announcementRepository, times(1)).save(any(Announcements.class));
    }

    @Test
    void testUpdateAnnouncementDetail_AnnouncementNotFound() {
        // Arrange
        UpdateAnnouncementDetailsRequest request = new UpdateAnnouncementDetailsRequest();
        request.setAnnouncementID(1L);

        when(announcementRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NoSuchEntityException.class, () -> announcementsService.updateAnnouncementDetail(request));

        verify(announcementRepository, times(1)).findById(1L);
        verify(announcementRepository, never()).save(any(Announcements.class));
    }

    @Test
    void testUpdateAnnouncementDetail_InvalidDateFormat() {
        // Arrange
        UpdateAnnouncementDetailsRequest request = new UpdateAnnouncementDetailsRequest();
        request.setAnnouncementID(1L);
        request.setTitle("Updated Title");
        request.setContent("Updated Content");
        request.setDate("invalid-date");

        Teacher teacher = new Teacher();
        teacher.setTeacherId(1L);

        Announcements existingAnnouncement = new Announcements();
        existingAnnouncement.setAnnouncementId(1L);
        existingAnnouncement.setTeacher(teacher);

        when(announcementRepository.findById(1L)).thenReturn(Optional.of(existingAnnouncement));

        // Act & Assert
        assertThrows(DateFormatException.class, () -> announcementsService.updateAnnouncementDetail(request));

        verify(announcementRepository, times(1)).findById(1L);
        verify(announcementRepository, never()).save(any(Announcements.class));
    }

    @Test
    void testDeleteAnnouncement() {
        // Arrange
        Announcements announcement = new Announcements();
        announcement.setAnnouncementId(1L);

        when(announcementRepository.findById(1L)).thenReturn(Optional.of(announcement));

        // Act
        BaseResponse response = announcementsService.deleteAnnouncement(1L);

        // Assert
        assertNotNull(response);
        assertEquals("Success", response.getStatus());
        assertEquals("Successfully deleted announcement with id - 1", response.getMessage());

        verify(announcementRepository, times(1)).findById(1L);
        verify(announcementRepository, times(1)).delete(announcement);
    }

    @Test
    void testDeleteAnnouncement_AnnouncementNotFound() {
        // Arrange
        when(announcementRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NoSuchEntityException.class, () -> announcementsService.deleteAnnouncement(1L));

        verify(announcementRepository, times(1)).findById(1L);
        verify(announcementRepository, never()).delete(any(Announcements.class));
    }
}
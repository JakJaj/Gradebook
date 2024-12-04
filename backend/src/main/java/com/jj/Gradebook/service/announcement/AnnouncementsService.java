package com.jj.Gradebook.service.announcement;

import com.jj.Gradebook.controller.response.announcements.AnnouncementsResponse;
import com.jj.Gradebook.dao.AnnouncementRepository;
import com.jj.Gradebook.dto.AnnouncementDTO;
import com.jj.Gradebook.dto.TeacherDTO;
import com.jj.Gradebook.entity.Announcements;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AnnouncementsService {

    private final AnnouncementRepository announcementRepository;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    public AnnouncementsResponse getAllAnnouncements() {

        List<Announcements> announcements = announcementRepository.findAll();

        List<AnnouncementDTO> announcementDTOList = announcements.stream()
                .map(announcement -> AnnouncementDTO.builder()
                        .announcementID(announcement.getAnnouncementId())
                        .title(announcement.getTitle())
                        .content(announcement.getContent())
                        .date(dateFormat.format(announcement.getDateTime()))
                        .teacher(TeacherDTO.builder()
                                .teacherID(announcement.getTeacher().getTeacherId())
                                .firstName(announcement.getTeacher().getFirstName())
                                .lastName(announcement.getTeacher().getLastName())
                                .dateOfBirth(dateFormat.format(announcement.getTeacher().getDateOfBirth()))
                                .dateOfEmployment(dateFormat.format(announcement.getTeacher().getDateOfEmployment()))
                                .build())
                        .build())
                .toList();

        return AnnouncementsResponse.builder()
                .status("Success")
                .message("Successfully returning all announcements")
                .announcements(announcementDTOList)
                .build();
    }
}

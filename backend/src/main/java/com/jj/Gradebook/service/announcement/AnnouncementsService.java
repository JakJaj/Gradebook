package com.jj.Gradebook.service.announcement;

import com.jj.Gradebook.controller.request.announcements.CreateAnnouncementRequest;
import com.jj.Gradebook.controller.request.announcements.UpdateAnnouncementDetailsRequest;
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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AnnouncementsService {

    private final AnnouncementRepository announcementRepository;
    private final TeacherRepository teacherRepository;

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

    public AnnouncementResponse createNewAnnouncement(CreateAnnouncementRequest request) {
        Teacher teacher = teacherRepository.findById(request.getTeacherID()).orElseThrow(() -> new NoSuchEntityException(String.format("No teacher with id - %d",request.getTeacherID())));

        try {
            Announcements announcement = announcementRepository.save(Announcements.builder()
                    .title(request.getTitle())
                    .content(request.getContent())
                    .dateTime(dateFormat.parse(request.getDate()))
                    .teacher(teacher)
                    .build());

            return AnnouncementResponse.builder()
                    .status("Success")
                    .message("Successfully created new announcement")
                    .announcement(AnnouncementDTO.builder()
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
                    .build();
        }catch (ParseException ex){
            throw new DateFormatException("Wrong date format");
        }

    }

    public AnnouncementResponse updateAnnouncementDetail(UpdateAnnouncementDetailsRequest request) {
        Announcements announcements = announcementRepository.findById(request.getAnnouncementID()).orElseThrow(() -> new NoSuchEntityException(String.format("No announcement with id - %d",request.getAnnouncementID())));

        try {
            Announcements savedAnnouncements = announcementRepository.save(Announcements.builder()
                    .announcementId(request.getAnnouncementID())
                    .title(request.getTitle())
                    .content(request.getContent())
                    .dateTime(dateFormat.parse(request.getDate()))
                    .teacher(announcements.getTeacher())
                    .build());

            return AnnouncementResponse.builder()
                    .status("Success")
                    .message(String.format("Successfully updated announcement with id - %d", savedAnnouncements.getAnnouncementId()))
                    .announcement(AnnouncementDTO.builder()
                            .announcementID(savedAnnouncements.getAnnouncementId())
                            .title(savedAnnouncements.getTitle())
                            .content(savedAnnouncements.getContent())
                            .date(dateFormat.format(savedAnnouncements.getDateTime()))
                            .teacher(TeacherDTO.builder()
                                    .teacherID(savedAnnouncements.getTeacher().getTeacherId())
                                    .firstName(savedAnnouncements.getTeacher().getFirstName())
                                    .lastName(savedAnnouncements.getTeacher().getLastName())
                                    .dateOfBirth(dateFormat.format(savedAnnouncements.getTeacher().getDateOfBirth()))
                                    .dateOfEmployment(dateFormat.format(savedAnnouncements.getTeacher().getDateOfEmployment()))
                                    .build())
                            .build())
                    .build();

        }catch (ParseException ex){
            throw new DateFormatException("Wrong date format");
        }
    }
}

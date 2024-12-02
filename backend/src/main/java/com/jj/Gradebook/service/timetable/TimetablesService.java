package com.jj.Gradebook.service.timetable;

import com.jj.Gradebook.config.JwtService;
import com.jj.Gradebook.controller.response.classes.TimetableResponse;
import com.jj.Gradebook.dao.*;
import com.jj.Gradebook.dto.TimetableEntryDTO;
import com.jj.Gradebook.entity.*;
import com.jj.Gradebook.exceptions.NoSuchEntityException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TimetablesService {


    private final TimetableRepository timetableRepository;
    private final JwtService jwtService;
    private final TeacherRepository teacherRepository;
    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final ParentRepository parentRepository;


    public TimetableResponse getUsersTimetable(Long userID) {

        User user = userRepository.findById(userID).orElseThrow(() -> new NoSuchEntityException(String.format("No user with id - %d", userID)));

        List<Timetable> timetableList;
        switch (user.getRole()){
            case TEACHER -> {
                Teacher teacher = teacherRepository.findByUser_UserId(user.getUserId()).orElseThrow(() -> new NoSuchEntityException("This should also never happen"));
                timetableList = timetableRepository.findTimetablesByCourse_Teacher_TeacherId(teacher.getTeacherId());
            }
            case STUDENT -> {
                Student student = studentRepository.findByUser_UserId(user.getUserId()).orElseThrow(() -> new NoSuchEntityException("This should also never happen"));
                timetableList = timetableRepository.findTimetablesByClas_ClassId(student.getStudentClass().getClassId());
            }
            default -> throw new NoSuchEntityException(String.format("No timetable for user with id - %d and role - %s ", userID, user.getRole()));

        }

        HashMap<DayOfWeek, List<TimetableEntryDTO>> timetableEntry = new HashMap<>();

        for (int i = 1; i <= 7; i++){
            timetableEntry.put(DayOfWeek.of(i), new ArrayList<>());
        }

        for (Timetable timetable: timetableList){
            int currentDayOfTheWeek = timetable.getDayOfWeek();


            timetableEntry.get(DayOfWeek.of(currentDayOfTheWeek)).add(TimetableEntryDTO.builder()
                    .timetableID(timetable.getTimetableId())
                    .courseName(timetable.getCourse().getCourseName())
                    .classID(timetable.getClas().getClassId())
                    .startTime(timetable.getStartTime().toString())
                    .endTime(timetable.getEndTime().toString())
                    .classroom(timetable.getClassroomNumber())
                    .teacherName(timetable.getCourse().getTeacher().getFirstName() + " " + timetable.getCourse().getTeacher().getLastName())
                    .build());
        }

        return TimetableResponse.builder()
                .status("Success")
                .message(String.format("Successfully returning timetable for %s with userID - %d", user.getRole().toString().toLowerCase(), user.getUserId()))
                .timetable(timetableEntry)
                .build();

    }
}

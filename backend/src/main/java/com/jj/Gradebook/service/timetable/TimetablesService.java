package com.jj.Gradebook.service.timetable;

import com.jj.Gradebook.config.JwtService;
import com.jj.Gradebook.controller.request.timetables.CreateTimetableEntry;
import com.jj.Gradebook.controller.request.timetables.CreateTimetableRequest;
import com.jj.Gradebook.controller.response.classes.TimetableResponse;
import com.jj.Gradebook.dao.*;
import com.jj.Gradebook.dto.TimetableEntryDTO;
import com.jj.Gradebook.entity.*;
import com.jj.Gradebook.entity.Class;
import com.jj.Gradebook.exceptions.NoSuchEntityException;
import jakarta.transaction.Transactional;
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
    private final CoursesRepository coursesRepository;
    private final ClassRepository classRepository;
    private final AttendanceRepository attendanceRepository;
    private final NoteRepository noteRepository;

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

        HashMap<DayOfWeek, List<TimetableEntryDTO>> timetableEntry = createHashMapOfTimetable(timetableList);

        return TimetableResponse.builder()
                .status("Success")
                .message(String.format("Successfully returning timetable for %s with userID - %d", user.getRole().toString().toLowerCase(), user.getUserId()))
                .timetable(timetableEntry)
                .build();
    }

    @Transactional
    public TimetableResponse createNewTimetableEntry(CreateTimetableRequest request) {
        System.out.println(request);

        Class theClass = classRepository.findById(request.getTimetables().get(0).getClassID()).orElseThrow(() -> new NoSuchEntityException(String.format("No class with id - %d", request.getTimetables().get(0).getClassID())));

        attendanceRepository.deleteAllByStudent_StudentClass_ClassId(theClass.getClassId());
        noteRepository.deleteAllByStudent_StudentClass_ClassId(theClass.getClassId());

        timetableRepository.deleteAllByClas_ClassId(theClass.getClassId());


        List<Timetable> timetableListToSave = new ArrayList<>();

        for (CreateTimetableEntry timetableEntry: request.getTimetables()){

            System.out.println(timetableEntry);

            Course course =  coursesRepository.findById(timetableEntry.getCourseID()).orElseThrow(() -> new NoSuchEntityException(String.format("No course - %d", timetableEntry.getCourseID())));

            Timetable timetable = Timetable.builder()
                    .course(course)
                    .clas(theClass)
                    .startTime(timetableEntry.getStartTime())
                    .endTime(timetableEntry.getEndTime())
                    .classroomNumber(timetableEntry.getClassroomNumber())
                    .dayOfWeek(timetableEntry.getDayOfWeek())
                    .build();

            timetableListToSave.add(timetable);
        }

        List<Timetable> createdTimetables = timetableRepository.saveAll(timetableListToSave);

        HashMap<DayOfWeek, List<TimetableEntryDTO>> timetableEntry = createHashMapOfTimetable(createdTimetables);

        return TimetableResponse.builder()
                .status("Success")
                .message("Succesfully created new timetable")
                .timetable(timetableEntry)
                .build();
    }

    private HashMap<DayOfWeek, List<TimetableEntryDTO>> createHashMapOfTimetable(List<Timetable> createdTimetables) {
        HashMap<DayOfWeek, List<TimetableEntryDTO>> timetableEntry = new HashMap<>();

        for (int i = 1; i <= 7; i++){
            timetableEntry.put(DayOfWeek.of(i), new ArrayList<>());
        }

        for (Timetable timetable: createdTimetables){
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
        return timetableEntry;
    }
}

package com.jj.Gradebook.service.timetable;

import com.jj.Gradebook.config.JwtService;
import com.jj.Gradebook.controller.request.timetables.CreateTimetableEntry;
import com.jj.Gradebook.controller.request.timetables.CreateTimetableRequest;
import com.jj.Gradebook.controller.response.BaseResponse;
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
        List<Timetable> timetableListToSave = new ArrayList<>();

        for (CreateTimetableEntry timetableEntry : request.getTimetables()) {
            Course course = coursesRepository.findById(timetableEntry.getCourseID())
                    .orElseThrow(() -> new NoSuchEntityException(String.format("No course with id - %d", timetableEntry.getCourseID())));

            Class theClass = classRepository.findById(timetableEntry.getClassID())
                    .orElseThrow(() -> new NoSuchEntityException(String.format("No class with id - %d", timetableEntry.getClassID())));

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

        timetableRepository.saveAll(timetableListToSave);

        return TimetableResponse.builder()
                .status("Success")
                .message("Successfully created new timetable entries")
                .build();
    }

    @Transactional
    public BaseResponse deleteTimetableEntry(Long timetableId) {
        Timetable timetable = timetableRepository.findById(timetableId)
                .orElseThrow(() -> new NoSuchEntityException(String.format("No timetable entry with id - %d", timetableId)));

        Class theClass = timetable.getClas();
        attendanceRepository.deleteAllByStudent_StudentClass_ClassId(theClass.getClassId());
        noteRepository.deleteAllByStudent_StudentClass_ClassId(theClass.getClassId());

        timetableRepository.delete(timetable);

        return BaseResponse.builder()
                .status("Success")
                .message(String.format("Successfully deleted timetable entry with id - %d", timetableId))
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
                    .courseID(timetable.getCourse().getCourseId())
                    .classID(timetable.getClas().getClassId())
                    .startTime(timetable.getStartTime().toString())
                    .endTime(timetable.getEndTime().toString())
                    .classroom(timetable.getClassroomNumber())
                    .classID(timetable.getClas().getClassId())
                    .teacherName(timetable.getCourse().getTeacher().getFirstName() + " " + timetable.getCourse().getTeacher().getLastName())
                    .build());
        }
        return timetableEntry;
    }

    public TimetableResponse getClassTimetable(Long classID) {
        Class theClass = classRepository.findById(classID).orElseThrow(() -> new NoSuchEntityException(String.format("No class with id - %d", classID)));

        List<Timetable> timetableList = timetableRepository.findTimetablesByClas_ClassId(theClass.getClassId());

        HashMap<DayOfWeek, List<TimetableEntryDTO>> timetableEntry = createHashMapOfTimetable(timetableList);

        return TimetableResponse.builder()
                .status("Success")
                .message(String.format("Successfully returning timetable for class with id - %d", classID))
                .timetable(timetableEntry)
                .build();
    }

    public TimetableResponse getTeacherTimetable(Long teacherID) {
        Teacher teacher = teacherRepository.findById(teacherID).orElseThrow(() -> new NoSuchEntityException(String.format("No teacher with id - %d", teacherID)));

        List<Timetable> timetableList = timetableRepository.findTimetablesByCourse_Teacher_TeacherId(teacher.getTeacherId());

        HashMap<DayOfWeek, List<TimetableEntryDTO>> timetableEntry = createHashMapOfTimetable(timetableList);

        return TimetableResponse.builder()
                .status("Success")
                .message(String.format("Successfully returning timetable for teacher with id - %d", teacherID))
                .timetable(timetableEntry)
                .build();

    }
}

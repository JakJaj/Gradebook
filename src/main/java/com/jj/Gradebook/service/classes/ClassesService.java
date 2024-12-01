package com.jj.Gradebook.service.classes;

import com.jj.Gradebook.controller.response.classes.ClassResponse;
import com.jj.Gradebook.controller.response.classes.ClassesResponse;
import com.jj.Gradebook.controller.response.classes.TimetableResponse;
import com.jj.Gradebook.dao.ClassRepository;
import com.jj.Gradebook.dao.CoursesRepository;
import com.jj.Gradebook.dao.TeacherRepository;
import com.jj.Gradebook.dao.TimetableRepository;
import com.jj.Gradebook.dto.ClassDTO;
import com.jj.Gradebook.dto.TeacherDTO;
import com.jj.Gradebook.dto.TimetableEntryDTO;
import com.jj.Gradebook.entity.Class;
import com.jj.Gradebook.entity.Teacher;
import com.jj.Gradebook.entity.Timetable;
import com.jj.Gradebook.exceptions.NoSuchEntityException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClassesService {

    private final ClassRepository classRepository;
    private final TimetableRepository timetableRepository;
    private final CoursesRepository coursesRepository;
    private final TeacherRepository teacherRepository;

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    private final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
    public ClassesResponse getAllClasses(){

        List<Class> classes = classRepository.findAll();

        List<ClassDTO> classDTOList = classes.stream()
                .map(aClass -> ClassDTO.builder()
                        .classID(aClass.getClassId())
                        .className(aClass.getClassName())
                        .startYear(aClass.getStartYear().getValue())
                        .tutor(TeacherDTO.builder()
                                .teacherID(aClass.getTeacher().getTeacherId())
                                .firstName(aClass.getTeacher().getFirstName())
                                .lastName(aClass.getTeacher().getLastName())
                                .dateOfBirth(dateFormat.format(aClass.getTeacher().getDateOfBirth()))
                                .dateOfEmployment(dateFormat.format(aClass.getTeacher().getDateOfEmployment()))
                                .build())
                        .build())
                .toList();

        return ClassesResponse.builder()
                .status("Success")
                .message("Successfully returning list of classes")
                .classes(classDTOList)
                .build();
    }

    public ClassResponse getClassByClassID(Long classID) {

        Class theClass = classRepository.findById(classID).orElseThrow(() -> new NoSuchEntityException(String.format("No class with id - %d", classID)));

        return ClassResponse.builder()
                .status("Success")
                .message(String.format("Succesfully return class with id - %d", classID))
                .theClass(ClassDTO.builder()
                        .classID(theClass.getClassId())
                        .className(theClass.getClassName())
                        .startYear(theClass.getStartYear().getValue())
                        .tutor(TeacherDTO.builder()
                                .teacherID(theClass.getTeacher().getTeacherId())
                                .firstName(theClass.getTeacher().getFirstName())
                                .lastName(theClass.getTeacher().getLastName())
                                .dateOfBirth(dateFormat.format(theClass.getTeacher().getDateOfBirth()))
                                .dateOfEmployment(dateFormat.format(theClass.getTeacher().getDateOfEmployment()))
                                .build())
                        .build())
                .build();
    }

    public TimetableResponse getTimetableOfClass(Long clasID) {

        Class theClass = classRepository.findById(clasID).orElseThrow(() -> new NoSuchEntityException(String.format("No class with id - %d",clasID)));

        List<Timetable> timetableList = timetableRepository.findTimetablesByClas_ClassId(theClass.getClassId());


        HashMap<DayOfWeek, List<TimetableEntryDTO>> timetableEntry = new HashMap<>();

        for (int i = 1; i <= 7; i++){
            timetableEntry.put(DayOfWeek.of(i), new ArrayList<>());
        }

        for (Timetable timetable: timetableList){
            int currentDayOfTheWeek = timetable.getDayOfWeek();


            timetableEntry.get(DayOfWeek.of(currentDayOfTheWeek)).add(TimetableEntryDTO.builder()
                    .timetableID(timetable.getTimetableId())
                    .courseName(timetable.getCourse().getCourseName())
                    .classID(theClass.getClassId())
                    .startTime(timetable.getStartTime().toString())
                    .endTime(timetable.getEndTime().toString())
                    .classroom(timetable.getClassroomNumber())
                    .teacherName(timetable.getCourse().getTeacher().getFirstName() + " " + timetable.getCourse().getTeacher().getLastName())
                    .build());
        }

        return TimetableResponse.builder()
                .status("Success")
                .message(String.format("Successfully returning timetable for class with id - %d",clasID))
                .timetable(timetableEntry)
                .build();
    }
}

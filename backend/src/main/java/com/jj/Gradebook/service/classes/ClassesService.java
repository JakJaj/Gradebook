package com.jj.Gradebook.service.classes;

import com.jj.Gradebook.controller.request.classes.CreateClassRequest;
import com.jj.Gradebook.controller.request.classes.UpdateClassDetailsRequest;
import com.jj.Gradebook.controller.response.BaseResponse;
import com.jj.Gradebook.controller.response.classes.ClassResponse;
import com.jj.Gradebook.controller.response.classes.ClassesResponse;
import com.jj.Gradebook.controller.response.classes.TimetableResponse;
import com.jj.Gradebook.controller.response.students.StudentsResponse;
import com.jj.Gradebook.dao.*;
import com.jj.Gradebook.dto.ClassDTO;
import com.jj.Gradebook.dto.StudentDTO;
import com.jj.Gradebook.dto.TeacherDTO;
import com.jj.Gradebook.dto.TimetableEntryDTO;
import com.jj.Gradebook.entity.Class;
import com.jj.Gradebook.entity.Student;
import com.jj.Gradebook.entity.Teacher;
import com.jj.Gradebook.entity.Timetable;
import com.jj.Gradebook.exceptions.NoSuchEntityException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.Year;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClassesService {

    private final ClassRepository classRepository;
    private final TimetableRepository timetableRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final AttendanceRepository attendanceRepository;
    private final NoteRepository noteRepository;

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

    public TimetableResponse getTimetableOfClass(Long classID) {

        Class theClass = classRepository.findById(classID).orElseThrow(() -> new NoSuchEntityException(String.format("No class with id - %d",classID)));

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
                .message(String.format("Successfully returning timetable for class with id - %d",classID))
                .timetable(timetableEntry)
                .build();
    }

    public StudentsResponse getStudentsOfClass(Long classID) {

        Class theClass = classRepository.findById(classID).orElseThrow(() -> new NoSuchEntityException(String.format("No class with id - %d",classID)));

        List<Student> students = studentRepository.findStudentsByStudentClass_ClassId(classID);

        List<StudentDTO> studentDTOList = students.stream()
                .map(student -> StudentDTO.builder()
                        .studentID(student.getStudentId())
                        .classID(theClass.getClassId())
                        .firstName(student.getFirstName())
                        .lastName(student.getLastName())
                        .dateOfBirth(dateFormat.format(student.getDateOfBirth()))
                        .city(student.getCity())
                        .street(student.getStreet())
                        .houseNumber(student.getHouseNumber())
                        .build())
                .toList();

        return StudentsResponse.builder()
                .status("Success")
                .message(String.format("Returning list of students from class %s" , theClass.getClassName()))
                .students(studentDTOList)
                .build();
    }

    public ClassResponse createNewClass(CreateClassRequest request) {

        Teacher teacher = teacherRepository.findById(request.getTeacherID()).orElseThrow(() -> new NoSuchEntityException(String.format("No teacher with id - %d", request.getTeacherID())));
        Class theClass = classRepository.save(Class.builder()
                .className(request.getClassName())
                .startYear(Year.of(request.getStartYear()))
                .teacher(teacher)
                .build());

        return ClassResponse.builder()
                .status("Success")
                .message(String.format("Successfully created new class with id - %d", theClass.getClassId()))
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

    public ClassResponse updateClassDetails(UpdateClassDetailsRequest request) {
        Class theClass = classRepository.findById(request.getTheClass().getClassID()).orElseThrow(() -> new NoSuchEntityException(String.format("No class with id - %d", request.getTheClass().getClassID())));
        Teacher teacher = teacherRepository.findById(request.getTheClass().getTutorID()).orElseThrow(() -> new NoSuchEntityException(String.format("No teacher with id - %d", request.getTheClass().getTutorID())));

        Class savedClass = classRepository.save(Class.builder()
                .classId(theClass.getClassId())
                .className(request.getTheClass().getClassName())
                .startYear(Year.of(request.getTheClass().getStartYear()))
                .teacher(teacher)
                .build());

        return ClassResponse.builder()
                .status("Success")
                .message(String.format("Successfully updated class with id - %d", savedClass.getClassId()))
                .theClass(ClassDTO.builder()
                        .classID(savedClass.getClassId())
                        .className(savedClass.getClassName())
                        .startYear(savedClass.getStartYear().getValue())
                        .tutor(TeacherDTO.builder()
                                .teacherID(savedClass.getTeacher().getTeacherId())
                                .firstName(savedClass.getTeacher().getFirstName())
                                .lastName(savedClass.getTeacher().getLastName())
                                .dateOfBirth(dateFormat.format(savedClass.getTeacher().getDateOfBirth()))
                                .dateOfEmployment(dateFormat.format(savedClass.getTeacher().getDateOfEmployment()))
                                .build())
                        .build())
                .build();
    }

    @Transactional
    public BaseResponse deleteClass(Long classID) {
        Class theClass = classRepository.findById(classID).orElseThrow(() -> new NoSuchEntityException(String.format("No class with id - %d", classID)));

        attendanceRepository.deleteAllByStudent_StudentClass_ClassId(classID);
        noteRepository.deleteAllByStudent_StudentClass_ClassId(classID);
        timetableRepository.deleteAllByClas_ClassId(classID);

        List<Student> students = studentRepository.findStudentsByStudentClass_ClassId(classID);
        for (Student student: students){
            student.setStudentClass(null);
        }
        studentRepository.saveAll(students);
        
        classRepository.delete(theClass);

        return BaseResponse.builder()
                .status("Success")
                .message(String.format("Successfully deleted class with id - %d", classID))
                .build();
    }
}

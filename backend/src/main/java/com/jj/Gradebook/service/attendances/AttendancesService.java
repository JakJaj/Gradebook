package com.jj.Gradebook.service.attendances;

import com.jj.Gradebook.controller.request.attendances.CreateAttendanceRequest;
import com.jj.Gradebook.controller.request.attendances.UpdateAttendanceDetailsRequest;
import com.jj.Gradebook.controller.response.attendance.AttendanceResponse;
import com.jj.Gradebook.controller.response.attendance.ClassAttendanceResponse;
import com.jj.Gradebook.controller.response.attendance.StudentAttendanceListResponse;
import com.jj.Gradebook.controller.response.students.StudentAttendancesResponse;
import com.jj.Gradebook.dao.*;
import com.jj.Gradebook.dto.*;
import com.jj.Gradebook.entity.*;
import com.jj.Gradebook.entity.Class;
import com.jj.Gradebook.exceptions.DateFormatException;
import com.jj.Gradebook.exceptions.NoSuchEntityException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendancesService {

    private final AttendanceRepository attendanceRepository;
    private final ClassRepository classRepository;
    private final StudentRepository studentRepository;
    private final CoursesRepository coursesRepository;
    private final TimetableRepository timetableRepository;


    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    public ClassAttendanceResponse getAttendanceOfClass(Long classID) {

        Class theClass = classRepository.findById(classID).orElseThrow(() -> new NoSuchEntityException(String.format("No class with id - %d", classID)));
        List<Attendance> attendanceList = attendanceRepository.findAttendancesByStudent_StudentClass_ClassId(theClass.getClassId());

        List<Student> studentsList = studentRepository.findStudentsByStudentClass_ClassId(classID);

        HashMap<Long, List<AttendanceDTO>> studentsAttendance = new HashMap<>();

        for (Student student: studentsList){
            studentsAttendance.put(student.getStudentId(), new ArrayList<>());
        }

        for (Attendance attendance: attendanceList){
            studentsAttendance.get(attendance.getStudent().getStudentId()).add(
                    AttendanceDTO.builder()
                            .attendanceID(attendance.getAttendanceId())
                            .status(attendance.getStatus())
                            .course(null)
                            .studentID(attendance.getStudent().getStudentId())
                            .date(dateFormat.format(attendance.getDateTime()))
                            .build()
            );
        }
        return ClassAttendanceResponse.builder()
                .status("Success")
                .message(String.format("Successfully returning attendance of class with id - %d", theClass.getClassId()))
                .studentsAttendance(studentsAttendance)
                .build();
    }

    public StudentAttendanceListResponse getStudentsAttendances(Long studentID) {
        Student student = studentRepository.findById(studentID).orElseThrow(() -> new NoSuchEntityException(String.format("No student with id - %d", studentID)));
        List<Course> coursesOfStudent = timetableRepository.findTimetablesByClas_ClassId(student.getStudentClass().getClassId()).stream().map(Timetable::getCourse).distinct().toList();

        HashMap<String, List<AttendanceDTO>> studentsAttendance = new HashMap<>();

        for (Course course : coursesOfStudent){
            studentsAttendance.put(course.getCourseName(), new ArrayList<>());
        }
        List<Attendance> attendanceList = attendanceRepository.findAttendancesByStudent_StudentId(studentID);

        for (Attendance attendance: attendanceList){
            if(studentsAttendance.get(attendance.getCourse().getCourseName()) != null)
                studentsAttendance.get(attendance.getCourse().getCourseName()).add(
                    AttendanceDTO.builder()
                            .attendanceID(attendance.getAttendanceId())
                            .status(attendance.getStatus())
                            .course(CourseDTO.builder()
                                    .courseID(attendance.getCourse().getCourseId())
                                    .courseName(attendance.getCourse().getCourseName())
                                    .tutor(TeacherDTO.builder()
                                            .teacherID(attendance.getCourse().getTeacher().getTeacherId())
                                            .firstName(attendance.getCourse().getTeacher().getFirstName())
                                            .lastName(attendance.getCourse().getTeacher().getLastName())
                                            .build())
                                    .build())
                            .studentID(attendance.getStudent().getStudentId())
                            .date(dateFormat.format(attendance.getDateTime()))
                            .build()
            );
        }

        return StudentAttendanceListResponse.builder()
                .status("Success")
                .message(String.format("Successfully returning attendance of student with id - %d", studentID))
                .attendances(studentsAttendance)
                .build();
    }

    public StudentAttendancesResponse createNewAttendance(Long studentID, CreateAttendanceRequest request) {

        Student student = studentRepository.findById(studentID).orElseThrow(() -> new NoSuchEntityException(String.format("No user with id - %d", studentID)));
        Course course = coursesRepository.findById(request.getCourseID()).orElseThrow(() -> new NoSuchEntityException(String.format("No course with id - %d", request.getCourseID())));

        try {
            Attendance attendance = attendanceRepository.save(Attendance.builder()
                    .status(request.getStatus())
                    .student(student)
                    .course(course)
                    .dateTime(dateFormat.parse(request.getDate()))
                    .build());


            return StudentAttendancesResponse.builder()
                    .status("Success")
                    .message("Successfully created new attendance entry")
                    .attendanceID(attendance.getAttendanceId())
                    .attendances(List.of(AttendanceDTO.builder()
                            .attendanceID(attendance.getAttendanceId())
                            .status(attendance.getStatus())
                            .date(dateFormat.format(attendance.getDateTime()))
                            .studentID(student.getStudentId())
                                    .course(CourseDTO.builder()
                                            .courseID(attendance.getCourse().getCourseId())
                                            .courseName(attendance.getCourse().getCourseName())
                                            .tutor(TeacherDTO.builder()
                                                    .teacherID(attendance.getCourse().getTeacher().getTeacherId())
                                                    .firstName(attendance.getCourse().getTeacher().getFirstName())
                                                    .lastName(attendance.getCourse().getTeacher().getLastName())
                                                    .build())
                                            .build())
                            .build()))
                    .build();
        }catch (ParseException ex){
            throw new DateFormatException("Wrong date format");
        }
    }

    public AttendanceResponse updateAttendanceDetails(Long studentID, UpdateAttendanceDetailsRequest request) {
        Student student = studentRepository.findById(studentID).orElseThrow(() -> new NoSuchEntityException(String.format("No student with id - %d", studentID)));
        Attendance attendance = attendanceRepository.findById(request.getAttendanceID()).orElseThrow(() -> new NoSuchEntityException(String.format("No attendance with id - %d", request.getAttendanceID())));
        if (!attendance.getStudent().getStudentId().equals(studentID)) throw new NoSuchEntityException("Selected attendance isn't a attendance of picked student");

        try {

            Attendance savedAttendance = attendanceRepository.save(Attendance.builder()
                    .attendanceId(attendance.getAttendanceId())
                    .dateTime(dateFormat.parse(request.getDate()))
                    .status(request.getStatus())
                    .student(student)
                            .course(attendance.getCourse())
                    .build());

            return AttendanceResponse.builder()
                    .status("Success")
                    .message(String.format("Successfully updated attendance with id - %d", savedAttendance.getAttendanceId()))
                    .attendance(AttendanceDTO.builder()
                            .attendanceID(savedAttendance.getAttendanceId())
                            .status(savedAttendance.getStatus())
                            .date(dateFormat.format(savedAttendance.getDateTime()))
                            .studentID(student.getStudentId())
                            .course(CourseDTO.builder()
                                    .courseID(savedAttendance.getCourse().getCourseId())
                                    .courseName(savedAttendance.getCourse().getCourseName())
                                    .tutor(TeacherDTO.builder()
                                            .teacherID(savedAttendance.getCourse().getTeacher().getTeacherId())
                                            .firstName(savedAttendance.getCourse().getTeacher().getFirstName())
                                            .lastName(savedAttendance.getCourse().getTeacher().getLastName())
                                            .build())
                                    .build())
                            .build())
                    .build();
        }catch (ParseException ex){
            throw new DateFormatException("Wrong date format");
        }
    }

    @Transactional
    public StudentAttendanceListResponse deleteAttendance(Long studentID, Long attendanceID) {
        studentRepository.findById(studentID).orElseThrow(() -> new NoSuchEntityException(String.format("No student with id - %d", studentID)));
        Attendance attendance = attendanceRepository.findById(attendanceID).orElseThrow(() -> new NoSuchEntityException(String.format("No attendance with id - %d", attendanceID)));
        if (!attendance.getStudent().getStudentId().equals(studentID)) throw new NoSuchEntityException("Selected attendance isn't a attendance of picked student");

        attendanceRepository.delete(attendance);
        return getStudentsAttendances(studentID);
    }
}

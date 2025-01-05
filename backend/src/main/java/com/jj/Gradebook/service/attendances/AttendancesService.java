package com.jj.Gradebook.service.attendances;

import com.jj.Gradebook.controller.request.attendances.CreateAttendanceRequest;
import com.jj.Gradebook.controller.request.attendances.UpdateAttendanceDetailsRequest;
import com.jj.Gradebook.controller.response.attendance.AttendanceResponse;
import com.jj.Gradebook.controller.response.attendance.ClassAttendanceResponse;
import com.jj.Gradebook.controller.response.students.StudentAttendancesResponse;
import com.jj.Gradebook.dao.AttendanceRepository;
import com.jj.Gradebook.dao.ClassRepository;
import com.jj.Gradebook.dao.StudentRepository;
import com.jj.Gradebook.dao.TimetableRepository;
import com.jj.Gradebook.dto.AttendanceDTO;
import com.jj.Gradebook.dto.TimetableEntryDTO;
import com.jj.Gradebook.entity.Attendance;
import com.jj.Gradebook.entity.Class;
import com.jj.Gradebook.entity.Student;
import com.jj.Gradebook.entity.Timetable;
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
                            .timetable(null)
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

    public StudentAttendancesResponse getStudentsAttendances(Long studentID) {
        Student student = studentRepository.findById(studentID).orElseThrow(() -> new NoSuchEntityException(String.format("No student with id - %d", studentID)));
        List<Attendance> attendanceList = attendanceRepository.findAttendancesByStudent_StudentId(studentID);

        List<AttendanceDTO> attendanceDTOList = attendanceList.stream()
                .map(attendance -> AttendanceDTO.builder()
                        .attendanceID(attendance.getAttendanceId())
                        .status(attendance.getStatus())
                        .timetable(TimetableEntryDTO.builder()
                                .timetableID(attendance.getTimetable().getTimetableId())
                                .courseID(attendance.getTimetable().getCourse().getCourseId())
                                .classID(student.getStudentClass().getClassId())
                                .startTime(attendance.getTimetable().getStartTime().toString())
                                .endTime(attendance.getTimetable().getEndTime().toString())
                                .classroom(attendance.getTimetable().getClassroomNumber())
                                .teacherName(attendance.getTimetable().getCourse().getTeacher().getFirstName() + " " + attendance.getTimetable().getCourse().getTeacher().getLastName())
                                .build())
                        .studentID(studentID)
                        .date(dateFormat.format(attendance.getDateTime()))
                        .build())
                .toList();

        return StudentAttendancesResponse.builder()
                .status("Success")
                .message(String.format("Successfully returning attendance of student with id - %d", studentID))
                .attendances(attendanceDTOList)
                .build();
    }

    public StudentAttendancesResponse createNewAttendance(Long studentID, CreateAttendanceRequest request) {

        Student student = studentRepository.findById(studentID).orElseThrow(() -> new NoSuchEntityException(String.format("No user with id - %d", studentID)));
        Timetable timetable = timetableRepository.findById(request.getTimetableID()).orElseThrow(() -> new NoSuchEntityException(String.format("No timetable with id - %d", request.getTimetableID())));

        try {
            Attendance attendance = attendanceRepository.save(Attendance.builder()
                    .status(request.getStatus())
                    .student(student)
                    .timetable(timetable)
                    .dateTime(dateFormat.parse(request.getDate()))
                    .build());


            return StudentAttendancesResponse.builder()
                    .status("Success")
                    .message("Successfully created new attendance entry")
                    .attendances(List.of(AttendanceDTO.builder()
                            .attendanceID(attendance.getAttendanceId())
                            .status(attendance.getStatus())
                            .date(dateFormat.format(attendance.getDateTime()))
                            .studentID(student.getStudentId())
                            .timetable(TimetableEntryDTO.builder()
                                    .timetableID(attendance.getTimetable().getTimetableId())
                                    .courseID(attendance.getTimetable().getCourse().getCourseId())
                                    .classID(attendance.getTimetable().getClas().getClassId())
                                    .startTime(attendance.getTimetable().getStartTime().toString())
                                    .endTime(attendance.getTimetable().getEndTime().toString())
                                    .classroom(attendance.getTimetable().getClassroomNumber())
                                    .teacherName(attendance.getTimetable().getCourse().getTeacher().getFirstName() + " " + attendance.getTimetable().getCourse().getTeacher().getLastName())
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
                    .timetable(attendance.getTimetable())
                    .build());

            return AttendanceResponse.builder()
                    .status("Success")
                    .message(String.format("Successfully updated attendance with id - %d", savedAttendance.getAttendanceId()))
                    .attendance(AttendanceDTO.builder()
                            .attendanceID(savedAttendance.getAttendanceId())
                            .status(savedAttendance.getStatus())
                            .date(dateFormat.format(savedAttendance.getDateTime()))
                            .studentID(student.getStudentId())
                            .timetable(TimetableEntryDTO.builder()
                                    .timetableID(savedAttendance.getTimetable().getTimetableId())
                                    .courseID(savedAttendance.getTimetable().getCourse().getCourseId())
                                    .classID(savedAttendance.getTimetable().getClas().getClassId())
                                    .startTime(savedAttendance.getTimetable().getStartTime().toString())
                                    .endTime(savedAttendance.getTimetable().getEndTime().toString())
                                    .classroom(savedAttendance.getTimetable().getClassroomNumber())
                                    .teacherName(savedAttendance.getTimetable().getCourse().getTeacher().getFirstName() + " " + savedAttendance.getTimetable().getCourse().getTeacher().getLastName())
                                    .build())
                            .build())
                    .build();
        }catch (ParseException ex){
            throw new DateFormatException("Wrong date format");
        }
    }

    @Transactional
    public StudentAttendancesResponse deleteAttendance(Long studentID, Long attendanceID) {
        studentRepository.findById(studentID).orElseThrow(() -> new NoSuchEntityException(String.format("No student with id - %d", studentID)));
        Attendance attendance = attendanceRepository.findById(attendanceID).orElseThrow(() -> new NoSuchEntityException(String.format("No attendance with id - %d", attendanceID)));
        if (!attendance.getStudent().getStudentId().equals(studentID)) throw new NoSuchEntityException("Selected attendance isn't a attendance of picked student");

        attendanceRepository.delete(attendance);
        return getStudentsAttendances(studentID);
    }
}

package com.jj.Gradebook.service.attendances;

import com.jj.Gradebook.controller.response.attendance.ClassAttendanceResponse;
import com.jj.Gradebook.dao.AttendanceRepository;
import com.jj.Gradebook.dao.ClassRepository;
import com.jj.Gradebook.dao.StudentRepository;
import com.jj.Gradebook.dto.AttendanceDTO;
import com.jj.Gradebook.entity.Attendance;
import com.jj.Gradebook.entity.Class;
import com.jj.Gradebook.entity.Student;
import com.jj.Gradebook.exceptions.NoSuchEntityException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
                            .timetableID(attendance.getTimetable().getTimetableId())
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
}

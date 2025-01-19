package com.jj.Gradebook.dao;

import com.jj.Gradebook.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    List<Attendance> findAttendancesByStudent_StudentClass_ClassId(Long classID);
    List<Attendance> findAttendancesByStudent_StudentId(Long studentID);
    void deleteAllByStudent_StudentClass_ClassId(Long clasID);

    void deleteAttendancesByStudent_StudentId(Long studentID);
    void deleteAllByCourse_Teacher_TeacherId(Long teacherID);

}

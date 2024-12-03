package com.jj.Gradebook.dao;

import com.jj.Gradebook.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    List<Attendance> findAttendancesByStudent_StudentClass_ClassId(Long classID);
}

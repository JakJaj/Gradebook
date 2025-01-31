package com.jj.Gradebook.dao;

import com.jj.Gradebook.entity.Course;
import com.jj.Gradebook.entity.Timetable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TimetableRepository extends JpaRepository<Timetable, Long> {
    List<Timetable> findTimetablesByClas_ClassId(Long classID);
    List<Timetable> findTimetablesByCourse_Teacher_TeacherId(Long teacherID);
    void deleteAllByClas_ClassId(Long classID);
    void deleteAllByCourse_Teacher_TeacherId(Long teacherID);

}

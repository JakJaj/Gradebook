package com.jj.Gradebook.service.grade;

import com.jj.Gradebook.dto.GradeDTO;
import com.jj.Gradebook.dto.StudentDTO;
import com.jj.Gradebook.entity.Grade;
import com.jj.Gradebook.exceptions.EntityNotFoundException;

import java.util.HashMap;
import java.util.List;

public interface GradeService {
    Grade save(Grade grade);
    void deleteById(Long id);
    HashMap<String, List<GradeDTO>> getGradesGroupedByCourseByStudentId(Long studentId);

    HashMap<String, HashMap<Long, List<GradeDTO>>> getStudnetsGradesGroupedByCoursesByClassIDAndCourseID(Long classID, Long courseID);

    GradeDTO getGradeByGradeID(Long gradeID) throws EntityNotFoundException;

    GradeDTO addGrade(Long courseID, Long studentID, GradeDTO grade) throws EntityNotFoundException;
}

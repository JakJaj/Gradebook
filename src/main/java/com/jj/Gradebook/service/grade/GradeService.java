package com.jj.Gradebook.service.grade;

import com.jj.Gradebook.dto.GradeDTO;
import com.jj.Gradebook.entity.Grade;

import java.util.HashMap;
import java.util.List;

public interface GradeService {
    Grade save(Grade grade);
    void deleteById(Long id);
    HashMap<String, List<GradeDTO>> getGradesGroupedByCourseByStudentId(Long studentId);
}

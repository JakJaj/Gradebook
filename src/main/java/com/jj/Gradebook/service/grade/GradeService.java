package com.jj.Gradebook.service.grade;

import com.jj.Gradebook.entity.Grade;

import java.util.List;

public interface GradeService {
    List<Grade> findAll();
    Grade findById(int id);
    Grade save(Grade grade);
    void deleteById(int id);
}

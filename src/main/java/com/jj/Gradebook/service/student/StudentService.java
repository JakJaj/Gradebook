package com.jj.Gradebook.service.student;

import com.jj.Gradebook.entity.Student;

import java.util.List;

public interface StudentService {
    List<Student> findAll();
    Student findById(int id);
    Student save(Student student);
    void deleteById(int id);
}

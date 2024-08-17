package com.jj.Gradebook.service.student;

import com.jj.Gradebook.entity.Student;
import com.jj.Gradebook.exceptions.EntityAlreadyExistException;
import com.jj.Gradebook.exceptions.EntityNotFoundException;

import java.util.List;

public interface StudentService {
    List<Student> findAll();
    Student findById(int id) throws EntityNotFoundException;
    Student findByPesel(String pesel) throws EntityNotFoundException;
    Student save(Student student) throws EntityAlreadyExistException;
    void deleteById(int id) throws EntityNotFoundException;
}

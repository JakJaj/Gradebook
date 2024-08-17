package com.jj.Gradebook.service.teacher;

import com.jj.Gradebook.entity.Teacher;
import com.jj.Gradebook.exceptions.EntityAlreadyExistException;
import com.jj.Gradebook.exceptions.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TeacherService {
    List<Teacher> findAll();
    Teacher findById(int id) throws EntityNotFoundException;
    Teacher findByPesel(String pesel) throws EntityNotFoundException;
    Teacher save(Teacher teacher) throws EntityAlreadyExistException;
    void deleteById(int id);
}

package com.jj.Gradebook.service.teacher;

import com.jj.Gradebook.entity.Teacher;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TeacherService {
    List<Teacher> findAll();
    Teacher findById(int id);
    Teacher findByPesel(String pesel);
    Teacher save(Teacher teacher);
    void deleteById(int id);
}

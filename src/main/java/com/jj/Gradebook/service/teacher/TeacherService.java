package com.jj.Gradebook.service.teacher;

import com.jj.Gradebook.dto.TeacherDTO;
import com.jj.Gradebook.entity.Teacher;
import com.jj.Gradebook.exceptions.EntityAlreadyExistException;
import com.jj.Gradebook.exceptions.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TeacherService {
    List<TeacherDTO> findAll();
    TeacherDTO findById(int id) throws EntityNotFoundException;
    TeacherDTO findByPesel(String pesel) throws EntityNotFoundException;
    TeacherDTO save(Teacher teacher) throws EntityAlreadyExistException;
    void deleteById(int id) throws EntityNotFoundException;
}

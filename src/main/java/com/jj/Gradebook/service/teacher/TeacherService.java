package com.jj.Gradebook.service.teacher;

import com.jj.Gradebook.dto.TeacherDTO;
import com.jj.Gradebook.entity.Teacher;
import com.jj.Gradebook.exceptions.EntityAlreadyExistException;
import com.jj.Gradebook.exceptions.EntityListEmptyException;
import com.jj.Gradebook.exceptions.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TeacherService {
    List<TeacherDTO> findAll() throws EntityListEmptyException;
    TeacherDTO findById(Long id) throws EntityNotFoundException;
    TeacherDTO findByPesel(String pesel) throws EntityNotFoundException;
    TeacherDTO save(Teacher teacher) throws EntityAlreadyExistException;
    void deleteById(Long id) throws EntityNotFoundException;
}

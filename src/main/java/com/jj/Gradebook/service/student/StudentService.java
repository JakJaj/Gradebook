package com.jj.Gradebook.service.student;

import com.jj.Gradebook.dto.StudentDTO;
import com.jj.Gradebook.entity.Student;
import com.jj.Gradebook.exceptions.EntityAlreadyExistException;
import com.jj.Gradebook.exceptions.EntityListEmptyException;
import com.jj.Gradebook.exceptions.EntityNotFoundException;

import java.util.List;

public interface StudentService {
    List<StudentDTO> findAll() throws EntityListEmptyException;
    StudentDTO findById(Long id) throws EntityNotFoundException;
    StudentDTO findByPesel(String pesel) throws EntityNotFoundException;
    StudentDTO save(Student student) throws EntityAlreadyExistException;
    void deleteById(Long id) throws EntityNotFoundException;
}

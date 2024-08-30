package com.jj.Gradebook.service.classes;

import java.util.List;

import com.jj.Gradebook.dto.ClassDTO;
import com.jj.Gradebook.entity.Class;
import com.jj.Gradebook.exceptions.EntityAlreadyExistException;
import com.jj.Gradebook.exceptions.EntityListEmptyException;
import com.jj.Gradebook.exceptions.EntityNotFoundException;

public interface ClassService {
    List<ClassDTO> findAll() throws EntityListEmptyException;
    ClassDTO findById(Long id) throws EntityNotFoundException;
    ClassDTO save(Class classes) throws EntityAlreadyExistException;
    void deleteById(Long id) throws EntityNotFoundException;
}

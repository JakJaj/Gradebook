package com.jj.Gradebook.service.parent;

import com.jj.Gradebook.dto.ParentDTO;
import com.jj.Gradebook.entity.Parent;
import com.jj.Gradebook.exceptions.EntityAlreadyExistException;
import com.jj.Gradebook.exceptions.EntityNotFoundException;

import java.util.List;

public interface ParentService {
    List<ParentDTO> findAll();
    ParentDTO findById(Long id) throws EntityNotFoundException;
    ParentDTO save(Parent parent) throws EntityAlreadyExistException;
    void deleteById(Long id) throws EntityNotFoundException;
}

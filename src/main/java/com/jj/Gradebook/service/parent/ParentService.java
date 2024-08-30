package com.jj.Gradebook.service.parent;

import com.jj.Gradebook.entity.Parent;
import com.jj.Gradebook.exceptions.EntityNotFoundException;

import java.util.List;

public interface ParentService {
    List<Parent> findAll();
    Parent findById(Long id) throws EntityNotFoundException;
    Parent save(Parent parent);
    void deleteById(Long id);
}

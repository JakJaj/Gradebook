package com.jj.Gradebook.service.classes;

import java.util.List;
import com.jj.Gradebook.entity.Class;
import com.jj.Gradebook.exceptions.EntityAlreadyExistException;
import com.jj.Gradebook.exceptions.EntityNotFoundException;

public interface ClassService {
    List<Class> findAll();
    Class findById(int id) throws EntityNotFoundException;
    Class save(Class classes) throws EntityAlreadyExistException;
    void deleteById(int id) throws EntityNotFoundException;
}

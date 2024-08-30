package com.jj.Gradebook.service.classes;

import com.jj.Gradebook.dao.ClassRepository;
import com.jj.Gradebook.entity.Class;
import com.jj.Gradebook.exceptions.EntityAlreadyExistException;
import com.jj.Gradebook.exceptions.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ClassServiceImpl implements ClassService{

    private ClassRepository classRepository;

    @Override
    public List<Class> findAll() {
        List<Class> data = classRepository.findAll();

        return data;
    }

    @Override
    public Class findById(int id) throws EntityNotFoundException {
        Optional<Class> result = classRepository.findById(id);

        Class theClass;
        if(result.isPresent()){
            theClass = result.get();
        }
        else {
            throw new EntityNotFoundException("No class with id - " + id);
        }
        return theClass;
    }

    @Override
    @Transactional
    public Class save(Class classes) throws EntityAlreadyExistException {
        Optional<Class> existingClass = classRepository.findClassByClassNameAndStartYear(classes.getClassName(),classes.getStartYear());

        if (existingClass.isEmpty()){
            return classRepository.save(classes);
        }
        else {
            throw new EntityAlreadyExistException("Class already exists!");
        }
    }

    @Override
    @Transactional
    public void deleteById(int id) throws EntityNotFoundException {
        Optional<Class> existingClass = classRepository.findById(id);
        if (existingClass.isPresent()){
            classRepository.deleteById(id);
        }
        else {
            throw new EntityNotFoundException("No class with id - " + id);
        }
    }
}

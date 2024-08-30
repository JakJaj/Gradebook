package com.jj.Gradebook.service.classes;

import com.jj.Gradebook.dao.ClassRepository;
import com.jj.Gradebook.dto.ClassDTO;
import com.jj.Gradebook.entity.Class;
import com.jj.Gradebook.exceptions.EntityAlreadyExistException;
import com.jj.Gradebook.exceptions.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ClassServiceImpl implements ClassService{

    private ClassRepository classRepository;

    @Override
    public List<ClassDTO> findAll() {
        List<Class> data = classRepository.findAll();
        List<ClassDTO> result = new ArrayList<>();

        for(Class classes : data){
            result.add(
                    new ClassDTO(
                            classes.getClassId(),
                            classes.getClassName(),
                            classes.getTeacher().getFirstName() + " " + classes.getTeacher().getLastName(),
                            classes.getStartYear().toString(),
                            (classes.isStatus() ? "Active" : "Inactive")
                    )
            );
        }
        return result;
    }

    @Override
    public ClassDTO findById(int id) throws EntityNotFoundException {
        Optional<Class> result = classRepository.findById(id);

        Class theClass;
        if(result.isPresent()){
            theClass = result.get();
            return new ClassDTO(
                    theClass.getClassId(),
                    theClass.getClassName(),
                    theClass.getTeacher().getFirstName() + " " + theClass.getTeacher().getLastName(),
                    theClass.getStartYear().toString(),
                    (theClass.isStatus() ? "Active" : "Inactive")
            );
        }
        else {
            throw new EntityNotFoundException("No class with id - " + id);
        }

    }

    @Override
    @Transactional
    public ClassDTO save(Class classes) throws EntityAlreadyExistException {
        Optional<Class> existingClass = classRepository.findClassByClassNameAndStartYear(classes.getClassName(),classes.getStartYear());

        if (existingClass.isEmpty()){
            Class savedClass = classRepository.save(classes);
            return new ClassDTO(
                    savedClass.getClassId(),
                    savedClass.getClassName(),
                    savedClass.getTeacher().getFirstName() + " " + savedClass.getTeacher().getLastName(),
                    savedClass.getStartYear().toString(),
                    (savedClass.isStatus() ? "Active" : "Inactive")
            );

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

package com.jj.Gradebook.service.classes;

import com.jj.Gradebook.dao.ClassRepository;
import com.jj.Gradebook.entity.Class;
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
        return classRepository.findAll();
    }

    @Override
    public Class findById(int id) {
        Optional<Class> result = classRepository.findById(id);

        Class theClass;
        if(result.isPresent()){
            theClass = result.get();
        }
        else { //TODO: FIND BETTER APPROACH
            throw new RuntimeException("No class with id - " + id);
        }
        return theClass;
    }

    @Override
    @Transactional
    public Class save(Class classes) {
        return classRepository.save(classes);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        classRepository.deleteById(id);
    }
}

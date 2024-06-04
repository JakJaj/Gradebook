package com.jj.Gradebook.service.teacher;

import com.jj.Gradebook.dao.TeacherRepository;
import com.jj.Gradebook.entity.Teacher;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherServiceImpl implements TeacherService{

    private final TeacherRepository teacherRepository;

    public TeacherServiceImpl(TeacherRepository teacherRepository){
        this.teacherRepository = teacherRepository;
    }

    @Override
    public List<Teacher> findAll() {
        return teacherRepository.findAll();
    }

    @Override
    public Teacher findById(int id) {
        Optional<Teacher> result = teacherRepository.findById(id);

        Teacher teacher = null;
        if(result.isPresent()){
            teacher = result.get();
        }
        else{ //TODO:THINK ABOUT A BETTER APPROACH
            throw new RuntimeException("No teacher with id - " + id);
        }
        return teacher;
    }

    @Override
    @Transactional
    public Teacher save(Teacher teacher) {
        return null;
    }

    @Override
    @Transactional
    public void deleteById(int id) {

    }
}

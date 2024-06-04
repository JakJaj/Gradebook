package com.jj.Gradebook.service.student;

import com.jj.Gradebook.dao.StudentRepository;
import com.jj.Gradebook.entity.Student;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService{

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student findById(int id) {
        Optional<Student> result = studentRepository.findById(id);

        Student student = null;
        if(result.isPresent()){
            student = result.get();
        }
        else{ //TODO: FIND BETTER APPROACH
            throw new RuntimeException("No student with id - " + id);
        }
        return student;
    }

    @Override
    @Transactional
    public Student save(Student student) {
        return studentRepository.save(student);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        studentRepository.deleteById(id);
    }
}

package com.jj.Gradebook.service.student;

import com.jj.Gradebook.dao.StudentRepository;
import com.jj.Gradebook.entity.Student;
import com.jj.Gradebook.exceptions.EntityAlreadyExistException;
import com.jj.Gradebook.exceptions.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService{

    private final StudentRepository studentRepository;

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student findById(int id) throws EntityNotFoundException {
        Optional<Student> result = studentRepository.findById(id);

        if(result.isPresent()){
            return result.get();
        }
        else {
            throw new EntityNotFoundException("No student with id - " + id);
        }

    }

    @Override
    public Student findByPesel(String pesel) throws EntityNotFoundException {
        Optional<Student> result = studentRepository.findStudentByUserPesel(pesel);
        if (result.isPresent()){
            return result.get();
        }
        else {
            throw new EntityNotFoundException("No student with pesel - " + pesel);
        }
    }

    @Override
    @Transactional
    public Student save(Student student) throws EntityAlreadyExistException {
        Optional<Student> existingStudent = studentRepository.findStudentByUserPesel(student.getUser().getPesel());
        if (existingStudent.isEmpty()){
            return studentRepository.save(student);
        }
        else {
            throw new EntityAlreadyExistException("Student already exists!");
        }
    }

    @Override
    @Transactional
    public void deleteById(int id) throws EntityNotFoundException {
        Optional<Student> existingStudent = studentRepository.findById(id);
        if (existingStudent.isPresent()){
            studentRepository.deleteById(id);
        }
        else {
            throw new EntityNotFoundException("No student with id - " + id);
        }
    }
}

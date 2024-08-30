package com.jj.Gradebook.service.student;

import com.jj.Gradebook.dao.StudentRepository;
import com.jj.Gradebook.dto.StudentDTO;
import com.jj.Gradebook.entity.Student;
import com.jj.Gradebook.exceptions.EntityAlreadyExistException;
import com.jj.Gradebook.exceptions.EntityListEmptyException;
import com.jj.Gradebook.exceptions.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    public List<StudentDTO> findAll() throws EntityListEmptyException {
        List<Student> data = studentRepository.findAll();
        List<StudentDTO> result = new ArrayList<>();

        if (data.isEmpty()){
            throw new EntityListEmptyException("List of students is empty!");
        }

        for (Student student : data) {
            result.add(
                    new StudentDTO(
                            student.getStudentId(),
                            student.getFirstName(),
                            student.getLastName(),
                            student.getUser().getPesel(),
                            student.getUser().getEmail(),
                            new SimpleDateFormat("dd.MM.yyyy").format(student.getDateOfBirth().getTime()),
                            student.getCity() + " " + student.getStreet() + " " + student.getHouseNumber(),
                            student.getStudentClass().getClassName(),
                            (student.getUser().isEnabled() ? "Active" : "Inactive")
                    ));

        }
        return result;
    }

    @Override
    public StudentDTO findById(Long id) throws EntityNotFoundException {
        Optional<Student> result = studentRepository.findById(id);
        Student student;
        if (result.isPresent()) {
            student = result.get();
            return new StudentDTO(
                    student.getStudentId(),
                    student.getFirstName(),
                    student.getLastName(),
                    student.getUser().getPesel(),
                    student.getUser().getEmail(),
                    new SimpleDateFormat("dd.MM.yyyy").format(student.getDateOfBirth().getTime()),
                    student.getCity() + " " + student.getStreet() + " " + student.getHouseNumber(),
                    student.getStudentClass().getClassName(),
                    (student.getUser().isEnabled() ? "Active" : "Inactive")
            );
        } else {
            throw new EntityNotFoundException("No student with id - " + id);
        }

    }

    @Override
    public StudentDTO findByPesel(String pesel) throws EntityNotFoundException {
        Optional<Student> result = studentRepository.findStudentByUser_Pesel(pesel);
        if (result.isPresent()) {
            Student student = result.get();
            return new StudentDTO(
                    student.getStudentId(),
                    student.getFirstName(),
                    student.getLastName(),
                    student.getUser().getPesel(),
                    student.getUser().getEmail(),
                    new SimpleDateFormat("dd.MM.yyyy").format(student.getDateOfBirth().getTime()),
                    student.getCity() + " " + student.getStreet() + " " + student.getHouseNumber(),
                    student.getStudentClass().getClassName(),
                    (student.getUser().isEnabled() ? "Active" : "Inactive")
            );
        } else {
            throw new EntityNotFoundException("No student with pesel - " + pesel);
        }
    }

    @Override
    @Transactional
    public StudentDTO save(Student student) throws EntityAlreadyExistException {
        Optional<Student> existingStudent = studentRepository.findStudentByUser_Pesel(student.getUser().getPesel());
        if (existingStudent.isEmpty()) {
            Student savedStudent = studentRepository.save(student);
            return new StudentDTO(
                    savedStudent.getStudentId(),
                    savedStudent.getFirstName(),
                    savedStudent.getLastName(),
                    savedStudent.getUser().getPesel(),
                    savedStudent.getUser().getEmail(),
                    new SimpleDateFormat("dd.MM.yyyy").format(savedStudent.getDateOfBirth().getTime()),
                    savedStudent.getCity() + " " + savedStudent.getStreet() + " " + savedStudent.getHouseNumber(),
                    savedStudent.getStudentClass().getClassName(),
                    (savedStudent.getUser().isEnabled() ? "Active" : "Inactive")
            );
        } else {
            throw new EntityAlreadyExistException("Student already exists!");
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) throws EntityNotFoundException {
        Optional<Student> existingStudent = studentRepository.findById(id);
        if (existingStudent.isPresent()) {
            studentRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("No student with id - " + id);
        }
    }
}

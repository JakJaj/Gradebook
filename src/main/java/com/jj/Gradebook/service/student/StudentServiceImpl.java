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

/**
 * Class that is an implementation of a service that is being used to get / set / delete a students data from a students database table
 */
@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    /**
     * Business logic used for getting all of existing students from a database
     * @return list of all instances of StudentDTOs that exists in a database
     * @throws EntityListEmptyException exception that will be thrown when a database table is empty
     */
    @Override
    public List<StudentDTO> findAll() throws EntityListEmptyException {
        List<Student> data = studentRepository.findAll();
        List<StudentDTO> result = new ArrayList<>();

        if (data.isEmpty()){
            throw new EntityListEmptyException("List of students is empty!");
        }

        for (Student student : data) {
            result.add(getStudentDTO(student));
        }
        return result;
    }

    /**
     *  Business logic used for finding a specific student by provided student id
     * @param id id of a student that will be used to get a student from a database
     * @return instance of StudentDTO that was received from a database
     * @throws EntityNotFoundException exception that will be thrown when provided id won't match any id in database
     */
    @Override
    public StudentDTO findById(Long id) throws EntityNotFoundException {
        Optional<Student> result = studentRepository.findById(id);

        if (result.isPresent()) {
            return getStudentDTO(result.get());
        } else {
            throw new EntityNotFoundException("No student with id - " + id);
        }

    }

    /**
     * Business logic used to find a specific student by provided pesel number
     * @param pesel pesel of a student that will be used to get a student from a database
     * @return instance of StudentDTO that was received from database
     * @throws EntityNotFoundException exception that will be thrown when provided id won't match any id in database
     */
    @Override
    public StudentDTO findByPesel(String pesel) throws EntityNotFoundException {
        Optional<Student> result = studentRepository.findStudentByUser_Pesel(pesel);
        if (result.isPresent()) {
            return getStudentDTO(result.get());
        } else {
            throw new EntityNotFoundException("No student with pesel - " + pesel);
        }
    }

    /**
     * Business logic used for creating a new student in the database
     * @param student student data that needs to be placed in a database
     * @return data of a created student instance
     * @throws EntityAlreadyExistException exception that will be thrown when a provided student's pesel already exists in a database
     */
    @Override
    @Transactional
    public StudentDTO save(Student student) throws EntityAlreadyExistException {
        Optional<Student> existingStudent = studentRepository.findStudentByUser_Pesel(student.getUser().getPesel());
        if (existingStudent.isEmpty()) {
            Student savedStudent = studentRepository.save(student);
            return getStudentDTO(savedStudent);
        } else {
            throw new EntityAlreadyExistException("Student already exists!");
        }
    }

    /**
     * Business logic used for deleting a student from a database by provided id
     * @param id id of a student that is meant to be deleted from a database
     * @throws EntityNotFoundException exception that will be thrown when provided id won't match any id in database
     **/
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

    /**
     *  Template generator of StudentDTO from Student
     *  @param student the student instance with all the information about student with student's personal data included
     *  @return       courseDTO which includes just a data that can be seen by an end users
     **/
    private StudentDTO getStudentDTO(Student student) {
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
    }
}

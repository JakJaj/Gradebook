package com.jj.Gradebook.service.teacher;

import com.jj.Gradebook.dao.TeacherRepository;
import com.jj.Gradebook.dto.TeacherDTO;
import com.jj.Gradebook.entity.Teacher;
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
 * Class that is an implementation of a service that is being used to get / set / delete a teachers data from a teachers database table
 */
@Service
@AllArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;

    /**
     * Business logic used for getting all of existing teachers from a database
     * @return list of all instances of TeacherDTOs that exists in a database
     * @throws EntityListEmptyException exception that will be thrown when a database table is empty
     */
    @Override
    public List<TeacherDTO> findAll() throws EntityListEmptyException {
        List<TeacherDTO> result = new ArrayList<>();
        List<Teacher> data = teacherRepository.findAll();

        if (data.isEmpty()){
            throw new EntityListEmptyException("List of teachers is empty");
        }

        for (Teacher teacher : data) {
            result.add(getTeacherDTO(teacher));
        }

        return result;
    }

    /**
     *  Business logic used for finding a specific teacher by provided teacher id
     * @param id id of a teacher that will be used to get a teacher from a database
     * @return instance of TeacherDTO that was received from a database
     * @throws EntityNotFoundException exception that will be thrown when provided id won't match any id in database
     */
    @Override
    public TeacherDTO findById(Long id) throws EntityNotFoundException {
        Optional<Teacher> result = teacherRepository.findById(id);

        if (result.isPresent()) {
            return getTeacherDTO(result.get());
        }
        else {
            throw new EntityNotFoundException("No teacher with id - " + id);
        }

    }

    /**
     * Business logic used to find a specific teacher by provided pesel number
     * @param pesel pesel of a teacher that will be used to get a teacher from a database
     * @return instance of TeacherDTO that was received from database
     * @throws EntityNotFoundException exception that will be thrown when provided id won't match any id in database
     */
    @Override
    public TeacherDTO findByPesel(String pesel) throws EntityNotFoundException {
        Optional<Teacher> result = teacherRepository.findTeacherByUser_Pesel(pesel);
        if (result.isPresent()) {
            return getTeacherDTO(result.get());
        } else {
            throw new EntityNotFoundException("No teacher with pesel - " + pesel);
        }
    }


    /**
     * Business logic used for creating a new parent in the database
     * @param teacher parent data that needs to be placed in a database
     * @return data of a created parent instance
     * @throws EntityAlreadyExistException exception that will be thrown when a provided parent's pesel already exists in a database
     */
    @Override
    @Transactional
    public TeacherDTO save(Teacher teacher) throws EntityAlreadyExistException {
        Optional<Teacher> existingTeacher = teacherRepository.findTeacherByUser_Pesel(teacher.getUser().getPesel());

        if (existingTeacher.isEmpty()) {
            Teacher savedTeacher = teacherRepository.save(teacher);
            return getTeacherDTO(savedTeacher);
        } else {
            throw new EntityAlreadyExistException("Teacher with this pesel already exists");
        }

    }

    /**
     * Business logic used for deleting a parent from a database by provided id
     * @param id id of a parent that is meant to be deleted from a database
     * @throws EntityNotFoundException exception that will be thrown when provided id won't match any id in database
     **/
    @Override
    @Transactional
    public void deleteById(Long id) throws EntityNotFoundException {
        Optional<Teacher> existingTeacher = teacherRepository.findById(id);
        if (existingTeacher.isPresent()) {
            teacherRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("No teacher with id - " + id);
        }
    }

    /**
     *  Template generator of TeacherDTO from Teacher
     *  @param teacher the teacher instance with all the information about teacher with teacher's personal data included
     *  @return       teacherDTO which includes just a data that can be seen by an end users
     **/
    private TeacherDTO getTeacherDTO(Teacher teacher){
        return new TeacherDTO(
                teacher.getTeacherId(),
                teacher.getFirstName(),
                teacher.getLastName(),
                teacher.getUser().getPesel(),
                teacher.getUser().getEmail(),
                new SimpleDateFormat("dd.MM.yyyy").format(teacher.getDateOfBirth().getTime()),
                new SimpleDateFormat("dd.MM.yyyy").format(teacher.getDateOfEmployment().getTime()),
                (teacher.getUser().isEnabled() ? "Active" : "Inactive")
        );
    }
}

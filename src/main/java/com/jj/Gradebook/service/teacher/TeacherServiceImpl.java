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

@Service
@AllArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;

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

    @Override
    public TeacherDTO findByPesel(String pesel) throws EntityNotFoundException {
        Optional<Teacher> result = teacherRepository.findTeacherByUser_Pesel(pesel);
        if (result.isPresent()) {
            return getTeacherDTO(result.get());
        } else {
            throw new EntityNotFoundException("No teacher with pesel - " + pesel);
        }
    }


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

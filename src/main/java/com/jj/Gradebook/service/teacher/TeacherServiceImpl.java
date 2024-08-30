package com.jj.Gradebook.service.teacher;

import com.jj.Gradebook.dao.TeacherRepository;
import com.jj.Gradebook.dto.TeacherDTO;
import com.jj.Gradebook.entity.Teacher;
import com.jj.Gradebook.exceptions.EntityAlreadyExistException;
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
    public List<TeacherDTO> findAll() {
        List<TeacherDTO> result = new ArrayList<>();
        List<Teacher> data = teacherRepository.findAll();

        for (Teacher teacher : data) {
            result.add(
                    new TeacherDTO(
                            teacher.getTeacherId(),
                            teacher.getFirstName(),
                            teacher.getLastName(),
                            teacher.getUser().getPesel(),
                            teacher.getUser().getEmail(),
                            new SimpleDateFormat("dd.MM.yyyy").format(teacher.getDateOfBirth().getTime()),
                            new SimpleDateFormat("dd.MM.yyyy").format(teacher.getDateOfEmployment().getTime()),
                            (teacher.getUser().isEnabled() ? "Active" : "Inactive")
                    )
            );
        }

        return result;
    }

    @Override
    public TeacherDTO findById(Long id) throws EntityNotFoundException {
        Optional<Teacher> result = teacherRepository.findById(id);

        if (result.isPresent()) {
            Teacher teacher = result.get();
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
        } else {
            throw new EntityNotFoundException("No teacher with id - " + id);
        }

    }

    @Override
    public TeacherDTO findByPesel(String pesel) throws EntityNotFoundException {
        Optional<Teacher> result = teacherRepository.findTeacherByUserPesel(pesel);
        if (result.isPresent()) {
            Teacher teacher = result.get();
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
        } else {
            throw new EntityNotFoundException("No teacher with pesel - " + pesel);
        }
    }


    @Override
    @Transactional
    public TeacherDTO save(Teacher teacher) throws EntityAlreadyExistException {
        Optional<Teacher> existingTeacher = teacherRepository.findTeacherByUserPesel(teacher.getUser().getPesel());

        if (existingTeacher.isEmpty()) {
            Teacher savedTeacher = teacherRepository.save(teacher);
            return new TeacherDTO(
                    savedTeacher.getTeacherId(),
                    savedTeacher.getFirstName(),
                    savedTeacher.getLastName(),
                    savedTeacher.getUser().getPesel(),
                    savedTeacher.getUser().getEmail(),
                    new SimpleDateFormat("dd.MM.yyyy").format(savedTeacher.getDateOfBirth().getTime()),
                    new SimpleDateFormat("dd.MM.yyyy").format(savedTeacher.getDateOfEmployment().getTime()),
                    (savedTeacher.getUser().isEnabled() ? "Active" : "Inactive")
            );
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
}

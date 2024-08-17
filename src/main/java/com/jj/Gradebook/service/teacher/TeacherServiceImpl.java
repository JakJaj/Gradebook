package com.jj.Gradebook.service.teacher;

import com.jj.Gradebook.dao.TeacherRepository;
import com.jj.Gradebook.entity.Teacher;
import com.jj.Gradebook.exceptions.EntityAlreadyExistException;
import com.jj.Gradebook.exceptions.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TeacherServiceImpl implements TeacherService{

    private final TeacherRepository teacherRepository;

    @Override
    public List<Teacher> findAll() {
        return teacherRepository.findAll();
    }

    @Override
    public Teacher findById(int id) throws EntityNotFoundException {
        Optional<Teacher> result = teacherRepository.findById(id);

        if(result.isPresent()){
            return  result.get();
        }
        else{
            throw new EntityNotFoundException("No teacher with id - " + id);
        }

    }

    @Override
    public Teacher findByPesel(String pesel) throws EntityNotFoundException {
        Optional<Teacher> result = Optional.ofNullable(teacherRepository.findTeacherByUserPesel(pesel));
        if(result.isPresent()){
            return result.get();
        }
        else {
            throw new EntityNotFoundException("No teacher with pesel - " + pesel);
        }
    }


    @Override
    @Transactional
    public Teacher save(Teacher teacher) throws EntityAlreadyExistException {
        Optional<Teacher> existingTeacher = Optional.ofNullable(teacherRepository.findTeacherByUserPesel(teacher.getUser().getPesel()));

        if (existingTeacher.isEmpty()){
            return teacherRepository.save(teacher);
        }
        else {
            throw new EntityAlreadyExistException("Teacher already exist");
        }

    }

    @Override
    @Transactional
    public void deleteById(int id) {
        teacherRepository.deleteById(id);
    }
}

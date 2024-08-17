package com.jj.Gradebook.service.teacher;

import com.jj.Gradebook.dao.TeacherRepository;
import com.jj.Gradebook.entity.Teacher;
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

        Teacher teacher = null;
        if(result.isPresent()){
            teacher = result.get();
        }
        else{
            throw new EntityNotFoundException("No teacher with id - " + id);
        }
        return teacher;
    }

    @Override
    public Teacher findByPesel(String pesel) {
        return teacherRepository.findTeacherByUserPesel(pesel);
    }


    @Override
    @Transactional
    public Teacher save(Teacher teacher) {

        return teacherRepository.save(teacher);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        teacherRepository.deleteById(id);
    }
}

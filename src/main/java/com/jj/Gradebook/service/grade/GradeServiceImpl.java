package com.jj.Gradebook.service.grade;

import com.jj.Gradebook.dao.GradeRepository;
import com.jj.Gradebook.entity.Grade;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GradeServiceImpl implements GradeService{

    private GradeRepository gradeRepository;

    @Override
    public List<Grade> findAll() {
        return gradeRepository.findAll();
    }

    @Override
    public Grade findById(int id) {
        Optional<Grade> result = gradeRepository.findById(id);

        Grade grade;
        if (result.isPresent()){
            grade = result.get();
        }
        else{
            throw new RuntimeException("No grade with id - " + id);
        }

        return grade;
    }

    @Override
    @Transactional
    public Grade save(Grade grade) {
        return gradeRepository.save(grade);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        gradeRepository.deleteById(id);
    }
}

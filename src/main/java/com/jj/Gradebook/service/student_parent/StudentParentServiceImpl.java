package com.jj.Gradebook.service.student_parent;

import com.jj.Gradebook.dao.Student_ParentRepository;
import com.jj.Gradebook.entity.Student_Parent.StudentParent;
import com.jj.Gradebook.entity.Student_Parent.StudentParentId;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StudentParentServiceImpl implements StudentParentService{
    private Student_ParentRepository studentParentRepository;

    @Override
    public List<StudentParent> findAll() {
        return studentParentRepository.findAll();
    }

    @Override
    public StudentParent findById(StudentParentId studentParentId) {
        Optional<StudentParent> result = studentParentRepository.findById(studentParentId);

        StudentParent studentParent;
        if(result.isPresent()){
            studentParent = result.get();
        }
        else { //TODO: FIND BETTER APPROACH
            throw new RuntimeException("No student-parent with id - " + studentParentId);
        }
        return studentParent;
    }

    @Override
    @Transactional
    public StudentParent save(StudentParent studentParent) {
        return studentParentRepository.save(studentParent);
    }

    @Override
    @Transactional
    public void deleteById(StudentParentId studentParentId) {
        studentParentRepository.deleteById(studentParentId);
    }
}

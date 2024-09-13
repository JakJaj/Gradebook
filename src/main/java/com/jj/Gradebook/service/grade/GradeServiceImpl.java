package com.jj.Gradebook.service.grade;

import com.jj.Gradebook.dao.GradeRepository;
import com.jj.Gradebook.dto.GradeDTO;
import com.jj.Gradebook.entity.Grade;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class GradeServiceImpl implements GradeService{

    private GradeRepository gradeRepository;

    @Override
    public HashMap<String, List<GradeDTO>> getGradesGroupedByCourseByStudentId(Long studentId) {

        Optional<List<Grade>> grades = gradeRepository.findAllByStudent_StudentId(studentId);
        HashMap<String, List<GradeDTO>> data = new HashMap<>();

        if (grades.isPresent()){
            for (Grade grade: grades.get()){
                if (!data.containsKey(grade.getCourse().getCourseName())){
                    data.put(grade.getCourse().getCourseName(), new ArrayList<>(List.of(
                            new GradeDTO(
                                    grade.getGradeId(),
                                    grade.getMark(),
                                    grade.getDescription(),
                                    grade.getMagnitude()
                    ))));
                }
                else{
                    String courseName = grade.getCourse().getCourseName();
                    List<GradeDTO> currentGrades = data.get(courseName);
                    currentGrades.add(new GradeDTO(
                            grade.getGradeId(),
                            grade.getMark(),
                            grade.getDescription(),
                            grade.getMagnitude()
                    ));
                    data.remove(courseName);
                    data.put(courseName, currentGrades);

                }
            }
        }

        System.out.println(data);
        return data;
    }

    @Override
    @Transactional
    public Grade save(Grade grade) {
        return gradeRepository.save(grade);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        gradeRepository.deleteById(id);
    }


}

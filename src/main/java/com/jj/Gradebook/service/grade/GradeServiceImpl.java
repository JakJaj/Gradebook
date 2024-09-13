package com.jj.Gradebook.service.grade;

import com.jj.Gradebook.dao.GradeRepository;
import com.jj.Gradebook.dto.GradeDTO;
import com.jj.Gradebook.dto.StudentDTO;
import com.jj.Gradebook.entity.Grade;
import com.jj.Gradebook.entity.Student;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
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

        return data;
    }

    @Override
    public HashMap<String, HashMap<Long, List<GradeDTO>>> getStudnetsGradesGroupedByCoursesByClassIDAndCourseID(Long classID, Long courseID) {
        Optional<List<Grade>> grades = gradeRepository.findAllByCourseCourseIdAndStudent_StudentClass_ClassId(courseID, classID);
        HashMap<String, HashMap<Long, List<GradeDTO>>> data = new HashMap<>();
        if (grades.isPresent()){
            List<Grade> iter = grades.get();
            String className = iter.get(0).getStudent().getStudentClass().getClassName();
            data.put(className, new HashMap<>());

            for (Grade grade: iter){
                Student student = grade.getStudent();


                if (!data.get(className).containsKey(student.getStudentId())){
                    data.get(className).put(student.getStudentId(), new ArrayList<>(List.of(
                            new GradeDTO(
                                    grade.getGradeId(),
                                    grade.getMark(),
                                    grade.getDescription(),
                                    grade.getMagnitude()
                            ))));
                }
                else {
                    List<GradeDTO> currentGrades = data.get(className).get(student.getStudentId());
                    currentGrades.add(new GradeDTO(
                            grade.getGradeId(),
                            grade.getMark(),
                            grade.getDescription(),
                            grade.getMagnitude()
                    ));
                }
            }
        }
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

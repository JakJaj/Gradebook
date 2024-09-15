package com.jj.Gradebook.service.grade;

import com.jj.Gradebook.dao.CourseRepository;
import com.jj.Gradebook.dao.GradeRepository;
import com.jj.Gradebook.dao.StudentRepository;
import com.jj.Gradebook.dto.GradeDTO;
import com.jj.Gradebook.dto.StudentDTO;
import com.jj.Gradebook.entity.Course;
import com.jj.Gradebook.entity.Grade;
import com.jj.Gradebook.entity.Student;
import com.jj.Gradebook.exceptions.EntityNotFoundException;
import com.jj.Gradebook.service.course.CourseServiceImpl;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
@AllArgsConstructor
public class GradeServiceImpl implements GradeService{

    private GradeRepository gradeRepository;
    private CourseRepository courseRepository;
    private StudentRepository studentRepository;

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
    public GradeDTO getGradeByGradeID(Long gradeID) throws EntityNotFoundException {
        Optional<Grade> existingGrade = gradeRepository.findById(gradeID);
        if (existingGrade.isPresent()){
            Grade grade = existingGrade.get();
            return new GradeDTO(
                    grade.getGradeId(),
                    grade.getMark(),
                    grade.getDescription(),
                    grade.getMagnitude()
            );
        }
        throw new EntityNotFoundException("No grade with id - " + gradeID);
    }

    @Override
    public GradeDTO addGrade(Long courseID, Long studentID, GradeDTO grade) throws EntityNotFoundException {
        Optional<Course> course = courseRepository.findById(courseID);
        Optional<Student> student = studentRepository.findById(studentID);

        if (course.isPresent() && student.isPresent()){
            Grade result = new Grade(course.get(), grade.getMark(), student.get(), grade.getDescription(), grade.getMagnitude());
            Grade returnedGrade = gradeRepository.save(result);
        } else if (course.isEmpty()) {
            throw new EntityNotFoundException("No courses with id - " + courseID);
        } else {
            throw new EntityNotFoundException("No student with id - " + studentID);
        }
        return new GradeDTO(
                grade.getGradeId(),
                grade.getMark(),
                grade.getDescription(),
                grade.getMagnitude()
        );
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

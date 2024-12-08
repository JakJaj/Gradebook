package com.jj.Gradebook.service.grades;

import com.jj.Gradebook.controller.request.grades.CreateGradeRequest;
import com.jj.Gradebook.controller.response.students.StudentGradesResponse;
import com.jj.Gradebook.dao.CoursesRepository;
import com.jj.Gradebook.dao.GradeRepository;
import com.jj.Gradebook.dao.StudentRepository;
import com.jj.Gradebook.dto.GradeDTO;
import com.jj.Gradebook.entity.Course;
import com.jj.Gradebook.entity.Grade;
import com.jj.Gradebook.entity.Student;
import com.jj.Gradebook.exceptions.DateFormatException;
import com.jj.Gradebook.exceptions.NoSuchEntityException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@RequiredArgsConstructor
public class GradesService {

    private final GradeRepository gradeRepository;
    private final StudentRepository studentRepository;
    private final CoursesRepository coursesRepository;

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    public StudentGradesResponse getAllStudentsGrades(Long studentID) {

        Student student = studentRepository.findById(studentID).orElseThrow(() -> new NoSuchEntityException(String.format("No student with id - %d", studentID)));
        List<Grade> grades = gradeRepository.findGradesByStudent_StudentId(studentID);

        HashMap<String, List<GradeDTO>> studentsGrades = new HashMap<>();

        for (Grade grade: grades){
            if (!studentsGrades.containsKey(grade.getCourse().getCourseName())){
                studentsGrades.put(grade.getCourse().getCourseId() + " - " + grade.getCourse().getCourseName(), new ArrayList<>());
            }

            studentsGrades.get(grade.getCourse().getCourseId() + " - " + grade.getCourse().getCourseName()).add(GradeDTO.builder()
                    .gradeID(grade.getGradeId())
                    .studentID(student.getStudentId())
                    .mark(grade.getMark())
                    .magnitude(grade.getMagnitude())
                    .description(grade.getDescription())
                    .date(dateFormat.format(grade.getDate()))
                    .build());
        }

        return StudentGradesResponse.builder()
                .status("Success")
                .message(String.format("Successfully returning list of student with id - %d grades", studentID))
                .grades(studentsGrades)
                .build();
    }

    public StudentGradesResponse createNewGrade(CreateGradeRequest request, Long studentID) {

        Student student = studentRepository.findById(studentID).orElseThrow(() -> new NoSuchEntityException(String.format("No student with id - %d", studentID)));
        Course course = coursesRepository.findById(request.getCourseID()).orElseThrow(() -> new NoSuchEntityException(String.format("No course with id - %d",request.getCourseID())));
        try {
            Grade grade = Grade.builder()
                    .course(course)
                    .mark(request.getMark())
                    .magnitude(request.getMagnitude())
                    .description(request.getDescription())
                    .student(student)
                    .date(dateFormat.parse(request.getDate()))
                    .build();

            Grade savedGrade = gradeRepository.save(grade);

            HashMap<String, List<GradeDTO>> grades = new HashMap<>();

            grades.put(grade.getCourse().getCourseName(), new ArrayList<>());

            grades.get(grade.getCourse().getCourseName()).add(GradeDTO.builder()
                    .gradeID(grade.getGradeId())
                    .studentID(grade.getStudent().getStudentId())
                    .mark(grade.getMark())
                    .magnitude(grade.getMagnitude())
                    .description(grade.getDescription())
                    .date(dateFormat.format(grade.getDate()))
                    .build());

            return StudentGradesResponse.builder()
                    .status("Success")
                    .message(String.format("Successfully created new grade with id - %d", savedGrade.getGradeId()))
                    .grades(grades)
                    .build();

        } catch (ParseException ex){
            throw new DateFormatException("Wrong date format");
        }


    }
}

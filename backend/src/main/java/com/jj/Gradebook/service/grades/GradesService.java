package com.jj.Gradebook.service.grades;

import com.jj.Gradebook.controller.request.grades.CreateGradeRequest;
import com.jj.Gradebook.controller.request.grades.UpdateGradeDetailsRequest;
import com.jj.Gradebook.controller.response.grades.GradeResponse;
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

    public GradeResponse updateGradeDetails(Long studentID, UpdateGradeDetailsRequest request) {
        Student student = studentRepository.findById(studentID).orElseThrow(() -> new NoSuchEntityException(String.format("No student with id - %d",studentID)));
        Grade grade = gradeRepository.findById(request.getGradeID()).orElseThrow(() -> new NoSuchEntityException(String.format("No grade with id - %d", request.getGradeID())));

        if (!grade.getStudent().getStudentId().equals(studentID)) throw new NoSuchEntityException("Selected grade isn't a grade of picked student");

        try {
            Grade savedGrade = gradeRepository.save(Grade.builder()
                    .gradeId(request.getGradeID())
                    .course(grade.getCourse())
                    .mark(request.getMark())
                    .magnitude(request.getMagnitude())
                    .description(request.getDescription())
                    .date(dateFormat.parse(request.getDate()))
                    .student(student)
                    .build());

            return GradeResponse.builder()
                    .status("Success")
                    .message(String.format("Successfully updated grade with id - %d", grade.getGradeId()))
                    .grade(GradeDTO.builder()
                            .gradeID(savedGrade.getGradeId())
                            .studentID(savedGrade.getStudent().getStudentId())
                            .mark(savedGrade.getMark())
                            .magnitude(savedGrade.getMagnitude())
                            .description(savedGrade.getDescription())
                            .date(dateFormat.format(savedGrade.getDate()))
                            .build())
                    .build();

        }catch (ParseException ex){
            throw new DateFormatException("Wrong date format");
        }
    }
}

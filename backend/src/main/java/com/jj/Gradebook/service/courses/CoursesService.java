package com.jj.Gradebook.service.courses;

import com.jj.Gradebook.controller.courses.CourseGradesResponse;
import com.jj.Gradebook.controller.response.courses.CourseResponse;
import com.jj.Gradebook.controller.response.courses.CoursesResponse;
import com.jj.Gradebook.dao.*;
import com.jj.Gradebook.dto.CourseDTO;
import com.jj.Gradebook.dto.GradeDTO;
import com.jj.Gradebook.dto.TeacherDTO;
import com.jj.Gradebook.entity.*;
import com.jj.Gradebook.entity.Class;
import com.jj.Gradebook.exceptions.NoSuchEntityException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CoursesService {
    private final CoursesRepository coursesRepository;
    private final TeacherRepository teacherRepository;
    private final GradeRepository gradeRepository;
    private final ClassRepository classRepository;
    private final StudentRepository studentRepository;


    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    public CoursesResponse getAllCourses(){

        List<Course> courses = coursesRepository.findAll();

        List<CourseDTO> courseDTOList = courses.stream()
                .map(course -> CourseDTO.builder()
                        .courseID(course.getCourseId())
                        .courseName(course.getCourseName())
                        .description(course.getDescription())
                        .tutor(TeacherDTO.builder()
                                .teacherID(course.getTeacher().getTeacherId())
                                .firstName(course.getTeacher().getFirstName())
                                .lastName(course.getTeacher().getLastName())
                                .dateOfBirth(dateFormat.format(course.getTeacher().getDateOfBirth()))
                                .dateOfEmployment(dateFormat.format(course.getTeacher().getDateOfEmployment()))
                                .build())
                        .build()
                ).toList();

        return CoursesResponse.builder()
                .status("Success")
                .message("Successfully returning list of courses")
                .courses(courseDTOList)
                .build();
    }

    public CourseResponse getCourseByCourseID(Long courseID) {

        Course course = coursesRepository.findById(courseID).orElseThrow(() -> new NoSuchEntityException(String.format("No course with id - %d", courseID)));

        return CourseResponse.builder()
                .status("Success")
                .message(String.format("Succesfully returning course with id - %d", courseID))
                .course(CourseDTO.builder()
                        .courseID(course.getCourseId())
                        .courseName(course.getCourseName())
                        .description(course.getDescription())
                        .tutor(TeacherDTO.builder()
                                .teacherID(course.getTeacher().getTeacherId())
                                .firstName(course.getTeacher().getFirstName())
                                .lastName(course.getTeacher().getLastName())
                                .dateOfBirth(dateFormat.format(course.getTeacher().getDateOfBirth()))
                                .dateOfEmployment(dateFormat.format(course.getTeacher().getDateOfEmployment()))
                                .build())
                        .build())
                .build();
    }

    public CoursesResponse getCoursesOfTeacher(Long teacherID) {


        Teacher teacher = teacherRepository.findById(teacherID).orElseThrow(() -> new NoSuchEntityException(String.format("No teacher with id - %d", teacherID)));
        List<Course> courses = coursesRepository.findCourseByTeacher_TeacherId(teacherID);

        List<CourseDTO> courseDTOList = courses.stream()
                .map(course -> CourseDTO.builder()
                        .courseID(course.getCourseId())
                        .courseName(course.getCourseName())
                        .description(course.getDescription())
                        .tutor(TeacherDTO.builder()
                                .teacherID(course.getTeacher().getTeacherId())
                                .firstName(course.getTeacher().getFirstName())
                                .lastName(course.getTeacher().getLastName())
                                .dateOfBirth(dateFormat.format(course.getTeacher().getDateOfBirth()))
                                .dateOfEmployment(dateFormat.format(course.getTeacher().getDateOfEmployment()))
                                .build())
                        .build())
                .toList();

        return CoursesResponse.builder()
                .status("Success")
                .message("Successfully returning list of teacher's courses")
                .courses(courseDTOList)
                .build();
    }

    public CourseGradesResponse getGradesInCourseOfClass(Long courseID, Long classID) {

        Class theClass = classRepository.findById(classID).orElseThrow(() -> new NoSuchEntityException(String.format("No class with id - %d", classID)));
        List<Grade> grades = gradeRepository.findGradesByStudent_StudentClass_ClassId(classID);

        HashMap<Long, List<GradeDTO>> gradesOfStudents = new HashMap<>();

        List<Student> studentsOfClass = studentRepository.findStudentsByStudentClass_ClassId(theClass.getClassId());
        for (Student student: studentsOfClass) {
            gradesOfStudents.put(student.getStudentId(), new ArrayList<>());
        }

        for (Grade grade: grades){
            if (grade.getCourse().getCourseId().equals(courseID)) {
                gradesOfStudents.get(grade.getStudent().getStudentId()).add(
                        GradeDTO.builder()
                                .gradeID(grade.getGradeId())
                                .studentID(grade.getStudent().getStudentId())
                                .mark(grade.getMark())
                                .magnitude(grade.getMagnitude())
                                .description(grade.getDescription())
                                .date(dateFormat.format(grade.getDate()))
                                .build());
            }
            }

        return CourseGradesResponse.builder()
                .status("Success")
                .message(String.format("Successfully returning list of each student grades for course: %d",courseID))
                .studentsGrades(gradesOfStudents)
                .build();
    }
}

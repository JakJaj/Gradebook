package com.jj.Gradebook.service.courses;

import com.jj.Gradebook.controller.response.courses.CourseResponse;
import com.jj.Gradebook.controller.response.courses.CoursesResponse;
import com.jj.Gradebook.dao.CoursesRepository;
import com.jj.Gradebook.dao.TeacherRepository;
import com.jj.Gradebook.dto.CourseDTO;
import com.jj.Gradebook.dto.TeacherDTO;
import com.jj.Gradebook.entity.Course;
import com.jj.Gradebook.entity.Teacher;
import com.jj.Gradebook.exceptions.NoSuchEntityException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CoursesService {
    private final CoursesRepository coursesRepository;
    private final TeacherRepository teacherRepository;
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
}

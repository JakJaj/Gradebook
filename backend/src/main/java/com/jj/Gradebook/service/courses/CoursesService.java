package com.jj.Gradebook.service.courses;

import com.jj.Gradebook.controller.courses.CourseAttendanceResponse;
import com.jj.Gradebook.controller.courses.CourseGradesResponse;
import com.jj.Gradebook.controller.request.courses.AddNewCourseRequest;
import com.jj.Gradebook.controller.request.courses.UpdateCourseRequest;
import com.jj.Gradebook.controller.response.BaseResponse;
import com.jj.Gradebook.controller.response.courses.CourseResponse;
import com.jj.Gradebook.controller.response.courses.CoursesResponse;
import com.jj.Gradebook.dao.*;
import com.jj.Gradebook.dto.*;
import com.jj.Gradebook.entity.*;
import com.jj.Gradebook.entity.Class;
import com.jj.Gradebook.exceptions.NoSuchEntityException;
import jakarta.transaction.Transactional;
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
    private final AttendanceRepository attendanceRepository;

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

    public CourseResponse addNewCourse(AddNewCourseRequest request) {
        System.out.println(request);
        Teacher teacher = teacherRepository.findById(request.getTeacherID()).orElseThrow(() -> new NoSuchEntityException(String.format("No teacher with id - %d", request.getTeacherID())));

        Course course = coursesRepository.save(Course.builder()
                .courseName(request.getCourseName())
                .description(request.getCourseDescription())
                .teacher(teacher)
                .build());

        return CourseResponse.builder()
                .status("Success")
                .message("Successfully added new course")
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

    @Transactional
    public CourseResponse updateCourse(UpdateCourseRequest request) {
        System.out.println(request);
        Course course = coursesRepository.findById(request.getCourseID()).orElseThrow(() -> new NoSuchEntityException(String.format("No course with id - %d", request.getCourseID())));
        Teacher teacher = teacherRepository.findById(request.getTeacherID()).orElseThrow(() -> new NoSuchEntityException(String.format("No teacher with id - %d", request.getTeacherID())));

        Course updatedCourse = coursesRepository.save(Course.builder()
                .courseId(course.getCourseId())
                .courseName(request.getCourseName())
                .description(request.getCourseDescription())
                .teacher(teacher)
                .build());

        return CourseResponse.builder()
                .status("Success")
                .message(String.format("Successfully updated course with id - %d", updatedCourse.getCourseId()))
                .course(CourseDTO.builder()
                        .courseID(updatedCourse.getCourseId())
                        .courseName(updatedCourse.getCourseName())
                        .description(updatedCourse.getDescription())
                        .tutor(TeacherDTO.builder()
                                .teacherID(updatedCourse.getTeacher().getTeacherId())
                                .firstName(updatedCourse.getTeacher().getFirstName())
                                .lastName(updatedCourse.getTeacher().getLastName())
                                .dateOfBirth(dateFormat.format(updatedCourse.getTeacher().getDateOfBirth()))
                                .dateOfEmployment(dateFormat.format(updatedCourse.getTeacher().getDateOfEmployment()))
                                .build())
                        .build())
                .build();
    }

    @Transactional
    public BaseResponse deleteCourse(Long courseID) {
        Course course = coursesRepository.findById(courseID).orElseThrow(() -> new NoSuchEntityException(String.format("No course with id - %d", courseID)));

        coursesRepository.delete(course);

        return CourseResponse.builder()
                .status("Success")
                .message(String.format("Successfully deleted course with id - %d", courseID))
                .build();
    }

    public CourseAttendanceResponse getAttendanceInCourseOfStudent(Long courseID, Long classID) {
        Class theClass = classRepository.findById(classID).orElseThrow(() -> new NoSuchEntityException(String.format("No class with id - %d", classID)));
        List<Attendance> attendances = attendanceRepository.findAttendancesByStudent_StudentClass_ClassId(classID);

        HashMap<Long, List<AttendanceDTO>> attendancesOfStudents = new HashMap<>();

        List<Student> studentsOfClass = studentRepository.findStudentsByStudentClass_ClassId(theClass.getClassId());
        for (Student student: studentsOfClass) {
            attendancesOfStudents.put(student.getStudentId(), new ArrayList<>());
        }

        for (Attendance attendance: attendances){
            if (attendance.getTimetable().getCourse().getCourseId().equals(courseID)) {
                attendancesOfStudents.get(attendance.getStudent().getStudentId()).add(
                        AttendanceDTO.builder()
                                .attendanceID(attendance.getAttendanceId())
                                .studentID(attendance.getStudent().getStudentId())
                                .date(dateFormat.format(attendance.getDateTime()))
                                .status(attendance.getStatus())
                                .timetable(TimetableEntryDTO.builder()
                                        .timetableID(attendance.getTimetable().getTimetableId())
                                        .courseID(attendance.getTimetable().getCourse().getCourseId())
                                        .build())
                                .build());
            }
        }

        return CourseAttendanceResponse.builder()
                .status("Success")
                .message(String.format("Successfully returning list of each student attendance for course: %d",courseID))
                .studentsAttendance(attendancesOfStudents)
                .build();
    }
}

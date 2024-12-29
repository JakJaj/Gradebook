package com.jj.Gradebook.service.teachers;

import com.jj.Gradebook.controller.request.teachers.UpdateTeacherDetailsRequest;
import com.jj.Gradebook.controller.response.BaseResponse;
import com.jj.Gradebook.controller.response.teachers.TeacherResponse;
import com.jj.Gradebook.controller.response.teachers.TeachersResponse;
import com.jj.Gradebook.dao.*;
import com.jj.Gradebook.dto.TeacherDTO;
import com.jj.Gradebook.entity.Class;
import com.jj.Gradebook.entity.Teacher;
import com.jj.Gradebook.entity.Timetable;
import com.jj.Gradebook.exceptions.DateFormatException;
import com.jj.Gradebook.exceptions.NoSuchEntityException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeachersService {
    private final TeacherRepository teacherRepository;
    private final AnnouncementRepository announcementRepository;
    private final ClassRepository classRepository;
    private final CoursesRepository coursesRepository;
    private final GradeRepository gradeRepository;
    private final TimetableRepository timetableRepository;
    private final AttendanceRepository attendanceRepository;
    private final NoteRepository noteRepository;

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    public TeachersResponse getAllTeachers(){
        List<Teacher> teachers = teacherRepository.findAll();

        List<TeacherDTO> teacherDTOList = teachers.stream()
                .map(teacher -> TeacherDTO.builder()
                        .teacherID(teacher.getTeacherId())
                        .firstName(teacher.getFirstName())
                        .lastName(teacher.getLastName())
                        .dateOfBirth(dateFormat.format(teacher.getDateOfBirth()))
                        .dateOfEmployment(dateFormat.format(teacher.getDateOfEmployment()))
                        .pesel(teacher.getUser().getPesel())
                        .email(teacher.getUser().getEmail())
                        .build())
                .toList();

        return TeachersResponse.builder()
                .status("Success")
                .message("Successfully returning list of teachers")
                .teachers(teacherDTOList)
                .build();
    }

    public TeacherResponse getTeacherByID(Long teacherID) {

        Teacher teacher = teacherRepository.findById(teacherID).orElseThrow(() -> new NoSuchEntityException(String.format("No teacher with id - %d", teacherID)));

        return TeacherResponse.builder()
                .status("Success")
                .message(String.format("Successfully returning teacher with id - %d", teacherID))
                .teacher(TeacherDTO.builder()
                        .teacherID(teacher.getTeacherId())
                        .firstName(teacher.getFirstName())
                        .lastName(teacher.getLastName())
                        .dateOfEmployment(dateFormat.format(teacher.getDateOfEmployment()))
                        .dateOfBirth(dateFormat.format(teacher.getDateOfBirth()))
                        .pesel(teacher.getUser().getPesel())
                        .email(teacher.getUser().getEmail())
                        .build())
                .build();
    }

    public TeacherResponse updateTeacherDetails(UpdateTeacherDetailsRequest request) {

        Teacher teacher = teacherRepository.findById(request.getTeacher().getTeacherID()).orElseThrow(() -> new NoSuchEntityException(String.format("No teacher with id - %d", request.getTeacher().getTeacherID())));

        try {


            Teacher savedTeacher = teacherRepository.save(Teacher.builder()
                    .teacherId(teacher.getTeacherId())
                    .firstName(request.getTeacher().getFirstName())
                    .lastName(request.getTeacher().getLastName())
                    .dateOfBirth(dateFormat.parse(request.getTeacher().getDateOfBirth()))
                    .dateOfEmployment(dateFormat.parse(request.getTeacher().getDateOfEmployment()))
                    .user(teacher.getUser())
                    .build());

            return TeacherResponse.builder()
                    .status("Success")
                    .message(String.format("Successfully updated teacher details with id - %d", savedTeacher.getTeacherId()))
                    .teacher(TeacherDTO.builder()
                            .teacherID(savedTeacher.getTeacherId())
                            .firstName(savedTeacher.getFirstName())
                            .lastName(savedTeacher.getLastName())
                            .dateOfEmployment(dateFormat.format(savedTeacher.getDateOfEmployment()))
                            .dateOfBirth(dateFormat.format(savedTeacher.getDateOfBirth()))
                            .pesel(savedTeacher.getUser().getPesel())
                            .email(savedTeacher.getUser().getEmail())
                            .build())
                    .build();
        }catch (ParseException ex){
            throw new DateFormatException("Wrong date format");
        }
    }

    @Transactional
    public BaseResponse deleteTeacher(Long teacherID) {
        Teacher teacher = teacherRepository.findById(teacherID).orElseThrow(() -> new NoSuchEntityException(String.format("No teacher with id - %d", teacherID)));

        List<Class> classes = classRepository.findClassesByTeacher_TeacherId(teacherID);
        for (Class aClass : classes) {
            aClass.setTeacher(null);
        }

        classRepository.saveAll(classes);
        gradeRepository.deleteGradesByCourse_Teacher_TeacherId(teacherID);
        attendanceRepository.deleteAllByTimetable_Course_Teacher_TeacherId(teacherID);
        noteRepository.deleteAllByTimetable_Course_Teacher_TeacherId(teacherID);
        timetableRepository.deleteAllByCourse_Teacher_TeacherId(teacherID);


        coursesRepository.deleteCoursesByTeacher_TeacherId(teacherID);
        announcementRepository.deleteAllByTeacher_TeacherId(teacherID);

        teacherRepository.delete(teacher);

        return TeacherResponse.builder()
                .status("Success")
                .message(String.format("Successfully deleted teacher with id - %d", teacherID))
                .build();
    }
}

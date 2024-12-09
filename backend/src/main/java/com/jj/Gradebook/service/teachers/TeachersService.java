package com.jj.Gradebook.service.teachers;

import com.jj.Gradebook.controller.request.teachers.UpdateTeacherDetailsRequest;
import com.jj.Gradebook.controller.response.teachers.TeacherResponse;
import com.jj.Gradebook.controller.response.teachers.TeachersResponse;
import com.jj.Gradebook.dao.TeacherRepository;
import com.jj.Gradebook.dto.TeacherDTO;
import com.jj.Gradebook.entity.Teacher;
import com.jj.Gradebook.exceptions.DateFormatException;
import com.jj.Gradebook.exceptions.NoSuchEntityException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeachersService {
    private final TeacherRepository teacherRepository;
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
                            .build())
                    .build();
        }catch (ParseException ex){
            throw new DateFormatException("Wrong date format");
        }
    }
}

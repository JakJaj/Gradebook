package com.jj.Gradebook.service.teachers;

import com.jj.Gradebook.controller.response.teachers.TeachersResponse;
import com.jj.Gradebook.dao.TeacherRepository;
import com.jj.Gradebook.dto.TeacherDTO;
import com.jj.Gradebook.entity.Teacher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeachersService {
    private final TeacherRepository teacherRepository;

    public TeachersResponse getAllTeachers(){

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
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
}

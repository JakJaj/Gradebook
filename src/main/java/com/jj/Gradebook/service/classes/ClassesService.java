package com.jj.Gradebook.service.classes;

import com.jj.Gradebook.controller.response.classes.ClassesResponse;
import com.jj.Gradebook.dao.ClassRepository;
import com.jj.Gradebook.dto.ClassDTO;
import com.jj.Gradebook.dto.TeacherDTO;
import com.jj.Gradebook.entity.Class;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClassesService {

    private final ClassRepository classRepository;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    public ClassesResponse getAllClasses(){

        List<Class> classes = classRepository.findAll();

        List<ClassDTO> classDTOList = classes.stream()
                .map(aClass -> ClassDTO.builder()
                        .classID(aClass.getClassId())
                        .className(aClass.getClassName())
                        .startYear(aClass.getStartYear().getValue())
                        .tutor(TeacherDTO.builder()
                                .teacherID(aClass.getTeacher().getTeacherId())
                                .firstName(aClass.getTeacher().getFirstName())
                                .lastName(aClass.getTeacher().getLastName())
                                .dateOfBirth(dateFormat.format(aClass.getTeacher().getDateOfBirth()))
                                .dateOfEmployment(dateFormat.format(aClass.getTeacher().getDateOfEmployment()))
                                .build())
                        .build())
                .toList();

        return ClassesResponse.builder()
                .status("Success")
                .message("Successfully returning list of classes")
                .classes(classDTOList)
                .build();
    }
}

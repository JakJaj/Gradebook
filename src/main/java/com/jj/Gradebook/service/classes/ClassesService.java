package com.jj.Gradebook.service.classes;

import com.jj.Gradebook.controller.response.classes.ClassResponse;
import com.jj.Gradebook.controller.response.classes.ClassesResponse;
import com.jj.Gradebook.dao.ClassRepository;
import com.jj.Gradebook.dto.ClassDTO;
import com.jj.Gradebook.dto.TeacherDTO;
import com.jj.Gradebook.entity.Class;
import com.jj.Gradebook.exceptions.NoSuchEntityException;
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

    public ClassResponse getClassByClassID(Long classID) {

        Class theClass = classRepository.findById(classID).orElseThrow(() -> new NoSuchEntityException(String.format("No class with id - %d", classID)));

        return ClassResponse.builder()
                .status("Success")
                .message(String.format("Succesfully return class with id - %d", classID))
                .theClass(ClassDTO.builder()
                        .classID(theClass.getClassId())
                        .className(theClass.getClassName())
                        .startYear(theClass.getStartYear().getValue())
                        .tutor(TeacherDTO.builder()
                                .teacherID(theClass.getTeacher().getTeacherId())
                                .firstName(theClass.getTeacher().getFirstName())
                                .lastName(theClass.getTeacher().getLastName())
                                .dateOfBirth(dateFormat.format(theClass.getTeacher().getDateOfBirth()))
                                .dateOfEmployment(dateFormat.format(theClass.getTeacher().getDateOfEmployment()))
                                .build())
                        .build())
                .build();
    }
}

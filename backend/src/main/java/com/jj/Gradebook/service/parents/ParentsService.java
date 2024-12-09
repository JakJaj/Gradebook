package com.jj.Gradebook.service.parents;

import com.jj.Gradebook.controller.request.parents.AddNewStudentsToParentRequest;
import com.jj.Gradebook.controller.response.parents.ParentResponse;
import com.jj.Gradebook.controller.response.parents.ParentsResponse;
import com.jj.Gradebook.controller.response.students.StudentsResponse;
import com.jj.Gradebook.dao.ParentRepository;
import com.jj.Gradebook.dao.StudentParentRepository;
import com.jj.Gradebook.dao.StudentRepository;
import com.jj.Gradebook.dto.ParentDTO;
import com.jj.Gradebook.dto.StudentDTO;
import com.jj.Gradebook.entity.Parent;
import com.jj.Gradebook.entity.Student;
import com.jj.Gradebook.entity.Student_Parent.StudentParent;
import com.jj.Gradebook.entity.Student_Parent.StudentParentId;
import com.jj.Gradebook.exceptions.NoSuchEntityException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ParentsService {

    private final ParentRepository parentRepository;
    private final StudentRepository studentRepository;
    private final StudentParentRepository studentParentRepository;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    public ParentsResponse getAllParents(){

        List<Parent> parents = parentRepository.findAll();

        List<ParentDTO> parentDTOList = parents.stream()
                .map(parent -> ParentDTO.builder()
                        .parentID(parent.getParentId())
                        .firstName(parent.getFirstName())
                        .lastName(parent.getLastName())
                        .build())
                .toList();

        return ParentsResponse.builder()
                .status("Success")
                .message("Successfully return list of parents")
                .parents(parentDTOList)
                .build();
    }

    public ParentResponse getParentByID(Long parentID) {

        Parent parent = parentRepository.findById(parentID).orElseThrow(() -> new NoSuchEntityException(String.format("No parent with id - %d", parentID)));

        return ParentResponse.builder()
                .status("Success")
                .message(String.format("Successfully return parent with id - %d", parentID))
                .parent(ParentDTO.builder()
                        .parentID(parent.getParentId())
                        .firstName(parent.getFirstName())
                        .lastName(parent.getLastName())
                        .build())
                .build();
    }

    public StudentsResponse getStudentsOfParent(Long parentID) {
        Parent parent = parentRepository.findById(parentID).orElseThrow(() -> new NoSuchEntityException(String.format("No parent with id - %d", parentID)));

        Set<StudentParent> studentParentSet = parent.getStudentParents();
        List<StudentDTO> students = new ArrayList<>();

        for (StudentParent studentParent : studentParentSet){

            students.add(StudentDTO.builder()
                    .studentID(studentParent.getStudent().getStudentId())
                    .classID(studentParent.getStudent().getStudentClass().getClassId())
                    .firstName(studentParent.getStudent().getFirstName())
                    .lastName(studentParent.getStudent().getLastName())
                    .dateOfBirth(dateFormat.format(studentParent.getStudent().getDateOfBirth()))
                    .city(studentParent.getStudent().getCity())
                    .street(studentParent.getStudent().getStreet())
                    .houseNumber(studentParent.getStudent().getHouseNumber())
                    .build()
            );
        }

        return StudentsResponse.builder()
                .status("Success")
                .message(String.format("Successfully returning a list of students for parent with id - %d", parentID))
                .students(students)
                .build();
    }

    public StudentsResponse addNewStudentsToParentRequest(Long parentID, AddNewStudentsToParentRequest request) {
        Parent parent = parentRepository.findById(parentID).orElseThrow(() -> new NoSuchEntityException(String.format("No parent with id - %d",parentID)));

        List<StudentParent> existingStudentParent = studentParentRepository.getStudentParentByParent_ParentId(parent.getParentId());

        Set<Long> existingStudentIds = existingStudentParent.stream()
                .map(studentParent -> studentParent.getStudent().getStudentId())
                .collect(Collectors.toSet());

        List<Student> students = request.getStudentsIDs().stream().distinct()
                .map(student -> studentRepository.findById(student).orElseThrow(() -> new NoSuchEntityException(String.format("No student with id - %d", student))))
                .filter(student -> !existingStudentIds.contains(student.getStudentId()))
                .toList();

        if (students.isEmpty()) throw new NoSuchEntityException("No new students");

        List<StudentParent> studentParentsList = students.stream()
                .map(student -> StudentParent.builder()
                        .id(new StudentParentId(student.getStudentId(), parent.getParentId()))
                        .student(student)
                        .parent(parent)
                        .build())
                        .toList();

        studentParentsList.forEach(sp -> System.out.println(String.format("Saving StudentParent: studentID=%d, parentID=%d", sp.getStudent().getStudentId(), sp.getParent().getParentId())));

        studentParentRepository.saveAll(studentParentsList);

        return StudentsResponse.builder()
                .status("Success")
                .message("Successfully crated new parent - student relationship")
                .students(studentParentsList.stream()
                        .map(student -> StudentDTO.builder()
                                        .studentID(student.getStudent().getStudentId())
                                        .classID(student.getStudent().getStudentClass().getClassId())
                                        .firstName(student.getStudent().getFirstName())
                                        .lastName(student.getStudent().getLastName())
                                        .dateOfBirth(dateFormat.format(student.getStudent().getDateOfBirth()))
                                        .city(student.getStudent().getCity())
                                        .street(student.getStudent().getStreet())
                                        .houseNumber(student.getStudent().getHouseNumber())
                                        .build()
                                ).toList())
                .build();
    }
}

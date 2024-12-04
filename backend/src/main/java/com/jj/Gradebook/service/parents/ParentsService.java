package com.jj.Gradebook.service.parents;

import com.jj.Gradebook.controller.response.parents.ParentResponse;
import com.jj.Gradebook.controller.response.parents.ParentsResponse;
import com.jj.Gradebook.controller.response.students.StudentsResponse;
import com.jj.Gradebook.dao.ParentRepository;
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

@Service
@RequiredArgsConstructor
public class ParentsService {

    private final ParentRepository parentRepository;
    private final StudentRepository studentRepository;
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
}

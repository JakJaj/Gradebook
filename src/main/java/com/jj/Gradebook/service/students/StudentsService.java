package com.jj.Gradebook.service.students;

import com.jj.Gradebook.controller.response.students.StudentResponse;
import com.jj.Gradebook.controller.response.students.StudentsResponse;
import com.jj.Gradebook.dao.StudentRepository;
import com.jj.Gradebook.dto.StudentDTO;
import com.jj.Gradebook.entity.Student;
import com.jj.Gradebook.exceptions.NoSuchUserException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentsService {

    private final StudentRepository studentRepository;

    public StudentsResponse getAllStudents() {
        List<Student> students = studentRepository.findAll();

        List<StudentDTO> studentDTOList = students.stream()
                .map(student -> StudentDTO.builder()
                        .studentID(student.getStudentId())
                        .firstName(student.getFirstName())
                        .lastName(student.getLastName())
                        .dateOfBirth(student.getDateOfBirth().toString())
                        .city(student.getCity())
                        .street(student.getStreet())
                        .houseNumber(student.getHouseNumber())
                        .build())
                .toList();

        return StudentsResponse.builder()
                .status("Success")
                .message("Returning list of students")
                .students(studentDTOList)
                .build();
    }

    public StudentResponse getStudentByID(Long studentID) {

        Student student = studentRepository.findById(studentID).orElseThrow(() -> new NoSuchUserException(String.format("No student with id - %d", studentID)));

        return StudentResponse.builder()
                .status("Success")
                .message(String.format("Successfully returning student with id - %d", studentID))
                .student(StudentDTO.builder()
                        .studentID(student.getStudentId())
                        .firstName(student.getFirstName())
                        .lastName(student.getLastName())
                        .city(student.getCity())
                        .street(student.getStreet())
                        .houseNumber(student.getHouseNumber())
                        .build())
                .build();

    }
}
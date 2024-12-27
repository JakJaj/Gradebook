package com.jj.Gradebook.service.students;

import com.jj.Gradebook.controller.request.students.UpdateStudentDetailsRequest;
import com.jj.Gradebook.controller.response.BaseResponse;
import com.jj.Gradebook.controller.response.parents.ParentsResponse;
import com.jj.Gradebook.controller.response.students.StudentResponse;
import com.jj.Gradebook.controller.response.students.StudentsResponse;
import com.jj.Gradebook.dao.*;
import com.jj.Gradebook.dto.ParentDTO;
import com.jj.Gradebook.dto.StudentDTO;
import com.jj.Gradebook.entity.Class;
import com.jj.Gradebook.entity.Student;
import com.jj.Gradebook.entity.Student_Parent.StudentParent;
import com.jj.Gradebook.exceptions.DateFormatException;
import com.jj.Gradebook.exceptions.NoSuchEntityException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class StudentsService {

    private final StudentRepository studentRepository;
    private final ClassRepository classRepository;
    private final GradeRepository gradeRepository;
    private final AttendanceRepository attendanceRepository;
    private final NoteRepository noteRepository;
    private final StudentParentRepository studentParentRepository;
    private final UserRepository userRepository;

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    public StudentsResponse getAllStudents() {
        List<Student> students = studentRepository.findAll();

        List<StudentDTO> studentDTOList = students.stream()
                .map(student -> StudentDTO.builder()
                        .studentID(student.getStudentId())
                        .firstName(student.getFirstName())
                        .lastName(student.getLastName())
                        .className(student.getStudentClass().getClassName())
                        .classID(student.getStudentClass().getClassId())
                        .dateOfBirth(dateFormat.format(student.getDateOfBirth()))
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

        Student student = studentRepository.findById(studentID).orElseThrow(() -> new NoSuchEntityException(String.format("No student with id - %d", studentID)));

        return StudentResponse.builder()
                .status("Success")
                .message(String.format("Successfully returning student with id - %d", studentID))
                .student(StudentDTO.builder()
                        .studentID(student.getStudentId())
                        .classID(student.getStudentClass().getClassId())
                        .className(student.getStudentClass().getClassName())
                        .firstName(student.getFirstName())
                        .lastName(student.getLastName())
                        .dateOfBirth(dateFormat.format(student.getDateOfBirth()))
                        .city(student.getCity())
                        .street(student.getStreet())
                        .houseNumber(student.getHouseNumber())
                        .build())
                .build();

    }

    public ParentsResponse getParentsOfStudents(Long studentID) {
        Student student = studentRepository.findById(studentID).orElseThrow(() -> new NoSuchEntityException(String.format("No student with id - %d", studentID)));

        Set<StudentParent> studentParentSet = student.getStudentParents();
        List<ParentDTO> parents = new ArrayList<>();

        for (StudentParent studentParent : studentParentSet){
            parents.add(ParentDTO.builder()
                    .parentID(studentParent.getParent().getParentId())
                    .firstName(studentParent.getParent().getFirstName())
                    .lastName(studentParent.getParent().getLastName())
                    .build());
        }

        return ParentsResponse.builder()
                .status("Success")
                .message("Successfully returning list of students parents")
                .parents(parents)
                .build();
    }

    @Transactional
    public StudentResponse updateStudentDetails(UpdateStudentDetailsRequest request) {
        Student student = studentRepository.findById(request.getStudent().getStudentID()).orElseThrow(() -> new NoSuchEntityException(String.format("No user with id - %d", request.getStudent().getStudentID())));
        Class theClass = classRepository.findById(request.getStudent().getClassID()).orElseThrow(() -> new NoSuchEntityException(String.format("No class with id - %d", request.getStudent().getClassID())));

        try {
            Student updatedStudent = studentRepository.save(Student.builder()
                    .studentId(student.getStudentId())
                    .firstName(request.getStudent().getFirstName())
                    .lastName(request.getStudent().getLastName())
                    .dateOfBirth(dateFormat.parse(request.getStudent().getDateOfBirth()))
                    .city(request.getStudent().getCity())
                    .street(request.getStudent().getStreet())
                    .houseNumber(request.getStudent().getHouseNumber())
                    .studentClass(theClass)
                    .user(student.getUser())
                    .build());

            return StudentResponse.builder()
                    .status("Success")
                    .message(String.format("Successfully updated student data with id - %d", request.getStudent().getStudentID()))
                    .student(StudentDTO.builder()
                            .studentID(updatedStudent.getStudentId())
                            .classID(updatedStudent.getStudentClass().getClassId())
                            .className(updatedStudent.getStudentClass().getClassName())
                            .firstName(updatedStudent.getFirstName())
                            .lastName(updatedStudent.getLastName())
                            .dateOfBirth(dateFormat.format(updatedStudent.getDateOfBirth()))
                            .city(updatedStudent.getCity())
                            .street(updatedStudent.getStreet())
                            .houseNumber(updatedStudent.getHouseNumber())
                            .build())
                    .build();
        }catch (ParseException exception){
            throw new DateFormatException("Wrong date format");
        }
    }

    @Transactional
    public BaseResponse deleteStudent(Long studentID) {
        Student student = studentRepository.findById(studentID).orElseThrow(() -> new NoSuchEntityException(String.format("No student with id - %d", studentID)));

        gradeRepository.deleteGradesByStudent_StudentId(studentID);
        attendanceRepository.deleteAttendancesByStudent_StudentId(studentID);
        noteRepository.deleteNotesByStudent_StudentId(studentID);
        studentParentRepository.deleteStudentParentsByStudent_StudentId(studentID);
        student.setStudentClass(null);
        System.out.println(student.getUser().getUserId());
        userRepository.deleteById(student.getUser().getUserId());

        Student savedStudent = studentRepository.save(student);

        studentRepository.delete(savedStudent);
        return BaseResponse.builder()
                .status("Success")
                .message(String.format("Successfully deleted student with id - %d", studentID))
                .build();
    }

    @Transactional
    public BaseResponse addStudentToClass(Long studentID, Long classID) {
        Student student = studentRepository.findById(studentID).orElseThrow(() -> new NoSuchEntityException(String.format("No student with id - %d", studentID)));
        Class theClass = classRepository.findById(classID).orElseThrow(() -> new NoSuchEntityException(String.format("No class with id - %d", classID)));

        student.setStudentClass(theClass);
        studentRepository.save(student);

        return BaseResponse.builder()
                .status("Success")
                .message(String.format("Successfully added student with id - %d to class with id - %d", studentID, classID))
                .build();
    }
}

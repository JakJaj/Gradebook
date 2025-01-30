package com.jj.Gradebook.service.parents;

import com.jj.Gradebook.controller.request.parents.AddNewStudentsToParentRequest;
import com.jj.Gradebook.controller.request.parents.DeleteStudentsToParentRequest;
import com.jj.Gradebook.controller.request.parents.UpdateParentDetailsRequest;
import com.jj.Gradebook.controller.response.BaseResponse;
import com.jj.Gradebook.controller.response.parents.ParentResponse;
import com.jj.Gradebook.controller.response.parents.ParentsResponse;
import com.jj.Gradebook.controller.response.students.StudentsResponse;
import com.jj.Gradebook.dao.ParentRepository;
import com.jj.Gradebook.dao.StudentParentRepository;
import com.jj.Gradebook.dao.StudentRepository;
import com.jj.Gradebook.dao.UserRepository;
import com.jj.Gradebook.dto.ParentDTO;
import com.jj.Gradebook.entity.*;
import com.jj.Gradebook.entity.Class;
import com.jj.Gradebook.entity.Student_Parent.StudentParent;
import com.jj.Gradebook.entity.Student_Parent.StudentParentId;
import com.jj.Gradebook.exceptions.NoSuchEntityException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ParentServiceTest {

    @Mock
    private ParentRepository parentRepository;
    @Mock
    private StudentRepository studentRepository;
    @Mock
    private StudentParentRepository studentParentRepository;
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ParentsService parentsService;

    private Parent parent;
    private Student student;
    private User user;
    private StudentParent studentParent;
    private Class studentClass;

    @BeforeEach
    void setUp() {
        user = User.builder()
                .userId(1L)
                .email("parent@test.com")
                .pesel("12345678901")
                .build();

        parent = Parent.builder()
                .parentId(1L)
                .firstName("John")
                .lastName("Doe")
                .user(user)
                .build();

        studentClass = Class.builder()
                .classId(1L)
                .className("1A")
                .build();

        student = Student.builder()
                .studentId(1L)
                .firstName("Jane")
                .lastName("Smith")
                .dateOfBirth(new Date())
                .city("Test City")
                .street("Test Street")
                .houseNumber("123")
                .studentClass(studentClass)
                .build();

        studentParent = StudentParent.builder()
                .id(new StudentParentId(student.getStudentId(), parent.getParentId()))
                .student(student)
                .parent(parent)
                .build();
    }

    @Test
    void getAllParents_Success() {
        // Given
        when(parentRepository.findAll()).thenReturn(Arrays.asList(parent));

        // When
        ParentsResponse response = parentsService.getAllParents();

        // Then
        assertNotNull(response);
        assertEquals("Success", response.getStatus());
        assertEquals(1, response.getParents().size());
        assertEquals(parent.getFirstName(), response.getParents().get(0).getFirstName());
        assertEquals(parent.getLastName(), response.getParents().get(0).getLastName());
    }

    @Test
    void getParentByID_Success() {
        // Given
        when(parentRepository.findById(1L)).thenReturn(Optional.of(parent));

        // When
        ParentResponse response = parentsService.getParentByID(1L);

        // Then
        assertNotNull(response);
        assertEquals("Success", response.getStatus());
        assertEquals(parent.getFirstName(), response.getParent().getFirstName());
        assertEquals(parent.getLastName(), response.getParent().getLastName());
    }

    @Test
    void getParentByID_NotFound() {
        // Given
        when(parentRepository.findById(1L)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(NoSuchEntityException.class, () -> parentsService.getParentByID(1L));
    }

    @Test
    void getStudentsOfParent_Success() {
        // Given
        Set<StudentParent> studentParents = new HashSet<>();
        studentParents.add(studentParent);
        parent.setStudentParents(studentParents);

        when(parentRepository.findById(1L)).thenReturn(Optional.of(parent));

        // When
        StudentsResponse response = parentsService.getStudentsOfParent(1L);

        // Then
        assertNotNull(response);
        assertEquals("Success", response.getStatus());
        assertEquals(1, response.getStudents().size());
        assertEquals(student.getFirstName(), response.getStudents().get(0).getFirstName());
    }

    @Test
    void addNewStudentsToParentRequest_Success() {
        // Given
        List<Long> studentIds = Arrays.asList(1L);
        AddNewStudentsToParentRequest request = new AddNewStudentsToParentRequest();
        request.setStudentsIDs(studentIds);

        when(parentRepository.findById(1L)).thenReturn(Optional.of(parent));
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        when(studentParentRepository.getStudentParentByParent_ParentId(1L)).thenReturn(new ArrayList<>());
        when(studentParentRepository.saveAll(any())).thenReturn(Arrays.asList(studentParent));

        // When
        StudentsResponse response = parentsService.addNewStudentsToParentRequest(1L, request);

        // Then
        assertNotNull(response);
        assertEquals("Success", response.getStatus());
        assertEquals(1, response.getStudents().size());
        verify(studentParentRepository).saveAll(any());
    }

    @Test
    void updateParentDetails_Success() {
        // Given
        ParentDTO parentDTO = ParentDTO.builder()
                .parentID(1L)
                .firstName("Updated")
                .lastName("Name")
                .email("updated@test.com")
                .pesel("98765432100")
                .build();

        UpdateParentDetailsRequest request = new UpdateParentDetailsRequest();
        request.setParent(parentDTO);

        when(parentRepository.findById(1L)).thenReturn(Optional.of(parent));
        when(parentRepository.save(any(Parent.class))).thenReturn(parent);

        // When
        ParentResponse response = parentsService.updateParentDetails(request);

        // Then
        assertNotNull(response);
        assertEquals("Success", response.getStatus());
        verify(parentRepository).save(any(Parent.class));
    }

    @Test
    void deleteStudentFromParent_Success() {
        // Given
        List<Long> studentIds = Arrays.asList(1L);
        DeleteStudentsToParentRequest request = new DeleteStudentsToParentRequest();
        request.setStudentsIDs(studentIds);

        when(parentRepository.findById(1L)).thenReturn(Optional.of(parent));
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        when(studentParentRepository.getStudentParentByStudent_StudentIdAndParent_ParentId(1L, 1L))
                .thenReturn(Optional.of(studentParent));

        // When
        BaseResponse response = parentsService.deleteStudentFromParent(1L, request);

        // Then
        assertNotNull(response);
        assertEquals("Success", response.getStatus());
        verify(studentParentRepository).delete(any(StudentParent.class));
    }

    @Test
    void deleteParent_Success() {
        // Given
        when(parentRepository.findById(1L)).thenReturn(Optional.of(parent));

        // When
        BaseResponse response = parentsService.deleteParent(1L);

        // Then
        assertNotNull(response);
        assertEquals("Success", response.getStatus());
        verify(studentParentRepository).deleteStudentParentByParent_ParentId(1L);
        verify(parentRepository).delete(parent);
        verify(userRepository).delete(user);
    }

    @Test
    void deleteParent_NotFound() {
        // Given
        when(parentRepository.findById(1L)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(NoSuchEntityException.class, () -> parentsService.deleteParent(1L));
        verify(studentParentRepository, never()).deleteStudentParentByParent_ParentId(any());
        verify(parentRepository, never()).delete(any());
        verify(userRepository, never()).delete(any());
    }
}
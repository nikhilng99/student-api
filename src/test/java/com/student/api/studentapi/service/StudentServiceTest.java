package com.student.api.studentapi.service;

import com.student.api.studentapi.dto.StudentDTO;
import com.student.api.studentapi.entity.Department;
import com.student.api.studentapi.entity.Student;
import com.student.api.studentapi.mapper.StudentMapper;
import com.student.api.studentapi.repository.DepartmentRepository;
import com.student.api.studentapi.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private DepartmentRepository departmentRepository;

    @Mock
    private StudentMapper studentMapper;

    @InjectMocks
    private StudentService studentService;

    @Test
    void shouldAddStudentWithDepartment() {
        StudentDTO dto = new StudentDTO(null, "Alice", "alice@test.com", 20, "A", 1);

        Student student = new Student();
        Department department = new Department();

        when(studentMapper.toEntity(dto)).thenReturn(student);
        when(departmentRepository.findById(1)).thenReturn(Optional.of(department));
        when(studentRepository.save(student)).thenReturn(student);
        when(studentMapper.toDTO(student)).thenReturn(dto);

        StudentDTO result = studentService.addStudent(dto);

        assertNotNull(result);
        verify(studentRepository).save(student);
    }
}